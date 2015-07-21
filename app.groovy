@Grab("spring-boot-starter-hornetq")
@Grab("hornetq-jms-server")
import org.hornetq.jms.server.config.impl.JMSQueueConfigurationImpl

@RestController
class MyWebApp {

  @Autowired
  JmsTemplate jmsTemplate

  @RequestMapping(value="/home")
  String method(){
    def messageCreater = {session ->
      session.createObjectMessage "Hola Mundo: ${new Date()}"
    } as MessageCreator
    jmsTemplate.send 'makingdevs', messageCreater
    "Hola Mundo ${new Date()}"
  }
}

@EnableJms
@Configuration
@Log
class Mensajeria {

  @Autowired
  String myString

  @JmsListener(destination = 'makingdevs')
  def receiver(String message){
    log.info myString
    log.info message
  }

  @Bean JMSQueueConfigurationImpl springBootQueue() {
    new  JMSQueueConfigurationImpl('spring-boot', null, false)
  }

}

beans {
  myString String, "Hola bean de spring"
}
