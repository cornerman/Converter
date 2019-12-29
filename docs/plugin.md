---
id: plugin
title: Getting started - SBT plugin
---

The plugin uses [scalajs-bundler](https://scalacenter.github.io/scalajs-bundler/)
to download NPM packages, and runs as an SBT source generator before compilation.

If you for some reason cannot use scalajs-bundler, there is a more general version [here](plugin-no-bundler.md).

# I just want to see how it works

Then check out the demo projects:
- [SlinkyTypedDemos](https://github.com/oyvindberg/SlinkyTypedDemos)
- `ScalaJsReactDemos` (we need help porting the slinky demos!)

# Setup

## Add to your `project/plugins.sbt`

```scala
  // for Scala.js 1.0.0 milestones
  addSbtPlugin("com.olvind" % "sbt-scalablytypedconverter" % "@VERSION@")

  // for Scala.js 0.6.x
  addSbtPlugin("com.olvind" % "sbt-scalablytypedconverter06" % "@VERSION@")
```

## Activate the plugin for a project in your `build.sbt`:

```scala
  project.enablePlugins(ScalablyTypedConverterPlugin)
```

## Setup your npm packages

```scala    
project.settings(
  Compile / npmDependencies ++= Seq(
    "react-router-dom" -> "5.1.2",
    "@types/react-router-dom" -> "5.1.2"
  )
)
```

Note that some packages contain first-party typescript type definitions, 
while for others like `react-router-dom` we need to get separate `@types` packages. 
These are originally from [DefinitelyTyped](https://github.com/DefinitelyTyped/DefinitelyTyped).  

Version numbers and `@types` packages are probably most conveniently found at [npmjs.com](https://npmjs.com).

## Choose your flavour (optional)

We have put a lot of effort into top-notch interop with two well-known 
Scala.js react wrapper libraries. If you want to use either make sure to choose the corresponding flavour. 

### Slinky

```scala
project.settings(
  Compile / tsoFlavour := Flavour.Slinky
)
```

### Scalajs-react

```scala
project.settings(
  Compile / tsoFlavour := Flavour.Japgolly
)
```

## Make it faster (Optional)

You'll notice that your build is now slow. 

This part will walk you through how to speed it up within the build.
Keep in mind that you can always pull the code generation and compilation up into a dependency 
 that you build independently if any of this becomes a showstopper. 

Your build is now slow because of three things, which we'll handle in turn.

### `npmInstall`

The first thing you should do is to configure scalajs-bundler to use yarn:
```scala
project.settings(
  useYarn := true
)
```
Yarn will need to be present on your system for this to work. You should also check in `yarn.lock`.
The main reason we do that is that `npm` uses seconds to verify that everything is current, and that will
 be done for **every** compile.

### `tsoImport`
ScalablyTypedConverter is not that fast yet. Surely there are low hanging fruits for speeding it up, 
 but we're following an old development [mantra](https://wiki.c2.com/?MakeItWorkMakeItRightMakeItFast). 
  
The most important way to speed it up is to make it do less work. Enter `tsoIgnore`.

So what can we ignore?

Let's take a few examples:

- `csstype` is a type-only library from DefinitelyTyped which describes all of react CSS with Typescript interfaces, enabling
 type-safe use. It is a dependency of `react`, and used throughout that ecosystem. 
 This is something you either want, or don't care about. If you don't, you can exclude it like this: 

```scala
project.settings(
  Compile / tsoIgnore += "csstype"
)
```

- Let's say you want to use an old version of `material-ui` which comes bundled with 5000 icons, modelled as react component classes.
You also don't need the icons. If that's the case, you can also exclude prefixes of module names with the same mechanism

```scala
project.settings(
  Compile / tsoIgnore ++= List("material-ui/svg-icons")
)
```

### Compiling all that generated code

There may be a **lot**. 

Ignoring libraries or modules will help, but our main tool here is called `tsoMinimize`.

By minimization we mean to remove everything from a library except what is referenced from somewhere else, so that
 things keep compiling.

The point is that you typically want everything from your main libraries, 
 but you care less about their (transitive) dependencies. 

Since you typically don't want to enumerate all transitive dependencies, the `Selection` helper type provided for this:

- `Selection.None` disables for all libraries (default)
- `Selection.NoneExcept(String*)` enables only given libraries 
- `Selection.All` enables for all libraries
- `Selection.AllExcept(String*)` enables only not given libraries (most useful) 

Using that, typical usage of `tsoMinimize` looks like this:

```scala
project.settings(
  Compile / tsoMinimize := Selection.AllExcept("axios", "material-ui", "mobx-react", "mobx")
)
``` 

Note that if you use a [react flavour](flavour.md) which generates a `components` package, all those
components are considered "entry points", and are not eligible for removal. 
In other words, if you use **only** react components from a library, it's fine to minimize that, too. 
 
## Customize the generated code
### `tsoEnableScalaJsDefined` 

As explained in the corresponding [Scala.js documentation page](http://www.scala-js.org/doc/interoperability/sjs-defined-js-classes.html),
`@ScalaJSDefined` traits are more convenient because they can be `new`ed, extended and so on.

However, there an imperfection somewhere in Scala.js and/or scalac with handling huge amounts of annotations 
 which makes compilation of some libraries *insanely* much slower.
`@material-ui/core` takes ~600 seconds to compile with `@ScalaJSDefined` traits, and ~35 without. 

This setting also uses `Selection`, so an example usage is:
```scala
project.settings(
  Compile / tsoEnableScalaJsDefined := Selection.All()
)
```

### `tsoStdLib`
This mirrors the `--lib` option to typescript, see 
[compiler options](https://www.typescriptlang.org/docs/handbook/compiler-options.html).

The main usage is to limit which DOM/Javascript APIs are available. 
For instance, a node application should not access the DOM at all, 
 and a browser application should only use things you have polyfilled.
      
```scala
project.settings(
  Compile / tsoStdlib := List("es6", "es2018.asyncgenerator")
)
```

Note that if you use `scala-js-dom` APIs they are naturally not affected.

Also note that if you use a [flavour](flavour.md) which translates types to `scala-js-dom` types, 
 only types found in the chosen stdlib will be translated.
 