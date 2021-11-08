organization := "org.scalablytyped"
name := "react-transition-group"
version := "2.0-e2ee5c"
scalaVersion := "2.13.5"
enablePlugins(ScalaJSPlugin)
libraryDependencies ++= Seq(
  "com.olvind" %%% "scalablytyped-runtime" % "2.4.2",
  "me.shadaj" %%% "slinky-web" % "0.6.7",
  "org.scalablytyped" %%% "react" % "0.0-unknown-c03336",
  "org.scalablytyped" %%% "std" % "0.0-unknown-0ec26b")
publishArtifact in packageDoc := false
scalacOptions ++= List("-encoding", "utf-8", "-feature", "-g:notailcalls", "-language:implicitConversions", "-language:higherKinds", "-language:existentials")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))