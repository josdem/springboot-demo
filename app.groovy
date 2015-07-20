@Grab("spring-boot-starter-hornetq")
@Grab("hornetq-jms-server")
import org.hornetq.jms.server.config.impl.JMSQueueConfigurationImpl

@RestController
class MyWebApp {

  @RequestMapping(value="/home")
  String method(){
    def messageCreator = {session ->
      session.createObjectMessage "Hola Mundo: ${new Date()}"
    } as MessageCreator
    jmsTemplate.send 'makingdevs', messageCreator
    "Hola Mundo ${new Date()}"
  }
}

@EnableJms
@Configuration
@Log
class Mensajeria {

  @JmsListener(destination = 'makingdevs')
  def receiver(String message){
    log.info message
  }

  @Bean JMSQueueConfigurationImpl springBootQueue() {
    new  JMSQueueConfigurationImpl('spring-boot', null, false)
  }

}
