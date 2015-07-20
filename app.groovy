@Grab("spring-boot-starter-hornetq")
@Grab("hornetq-jms-server")
import org.hornetq.jms.server.config.impl.JMSQueueConfigurationImpl

@RestController
@EnableJms
class MyWebApp {

  @RequestMapping(value="/")
  String method(){
    def messageCreator = {session ->
      session.createTextMessage "Hola Mundo: ${new Date()}"
    } as MessageCreator

    jmsTemplate.send 'makingdevs', messageCreator
    "Hola Mundo ${new Date()}"
  }
}

class Mensajeria {

  @Autowired
  JmsTemplate jmsTemplate

  @JmsListener(destination = 'makingdevs')
  def receiver(String message){
    println message
  }

}
