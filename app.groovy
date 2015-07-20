@RestController
class MyWebApp {

  @RequestMapping(value="/")
  String method(){
    "Hola Mundo: ${new Date()}"
  }
}
