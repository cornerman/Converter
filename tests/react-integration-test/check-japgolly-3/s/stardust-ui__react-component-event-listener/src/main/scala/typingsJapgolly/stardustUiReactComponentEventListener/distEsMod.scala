package typingsJapgolly.stardustUiReactComponentEventListener

import japgolly.scalajs.react.facade.React.RefHandle
import typingsJapgolly.stardustUiReactComponentEventListener.anon.Capture
import typingsJapgolly.stardustUiReactComponentEventListener.anon.Listener
import typingsJapgolly.stardustUiReactComponentEventListener.distEsTypesMod.EventListenerOptions
import typingsJapgolly.stardustUiReactComponentEventListener.distEsTypesMod.EventTypes
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object distEsMod {
  
  @JSImport("@stardust-ui/react-component-event-listener/dist/es", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  object EventListener {
    
    inline def apply[T /* <: EventTypes */](props: EventListenerOptions[T]): Any = ^.asInstanceOf[js.Dynamic].apply(props.asInstanceOf[js.Any]).asInstanceOf[Any]
    
    @JSImport("@stardust-ui/react-component-event-listener/dist/es", "EventListener")
    @js.native
    val ^ : js.Any = js.native
    
    object defaultProps {
      
      @JSImport("@stardust-ui/react-component-event-listener/dist/es", "EventListener.defaultProps")
      @js.native
      val ^ : js.Any = js.native
      
      @JSImport("@stardust-ui/react-component-event-listener/dist/es", "EventListener.defaultProps.capture")
      @js.native
      def capture: Boolean = js.native
      inline def capture_=(x: Boolean): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("capture")(x.asInstanceOf[js.Any])
    }
    
    @JSImport("@stardust-ui/react-component-event-listener/dist/es", "EventListener.displayName")
    @js.native
    def displayName: String = js.native
    inline def displayName_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("displayName")(x.asInstanceOf[js.Any])
    
    @JSImport("@stardust-ui/react-component-event-listener/dist/es", "EventListener.propTypes")
    @js.native
    def propTypes: Capture | Listener = js.native
    inline def propTypes_=(x: Capture | Listener): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("propTypes")(x.asInstanceOf[js.Any])
  }
  
  @JSImport("@stardust-ui/react-component-event-listener/dist/es", "documentRef")
  @js.native
  val documentRef: RefHandle[
    /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Node */ Any
  ] = js.native
  
  inline def useEventListener[T /* <: /* import warning: LimitUnionLength.leaveTypeRef Was union type with length 91, starting with typingsJapgolly.stardustUiReactComponentEventListener.stardustUiReactComponentEventListenerStrings.waiting, typingsJapgolly.stardustUiReactComponentEventListener.stardustUiReactComponentEventListenerStrings.error, typingsJapgolly.stardustUiReactComponentEventListener.stardustUiReactComponentEventListenerStrings.abort */ Any */](options: EventListenerOptions[T]): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("useEventListener")(options.asInstanceOf[js.Any]).asInstanceOf[Unit]
  
  @JSImport("@stardust-ui/react-component-event-listener/dist/es", "windowRef")
  @js.native
  val windowRef: RefHandle[
    /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Node */ Any
  ] = js.native
}
