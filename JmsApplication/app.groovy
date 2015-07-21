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
      session.createObjectMessage "Hola inmundo en JMS ${new Date()}"
    } as MessageCreator
    jmsTemplate.send 'makingdevs', messageCreater
    "Hola Inmundo ${new Date()}"
  }
}

@EnableJms
@Configuration
@Log
class Mensajeria {

  @Autowired
  String myString

  @JmsListener(destination = 'makingdevs')
  def recibidorZ(String message){
    log.info myString
    log.info message
  }

  @Bean JMSQueueConfigurationImpl springBootQueue() {
    new JMSQueueConfigurationImpl('makingdevs', null, false)
  }
}

beans {
  myString String, "Hola bean de spring"
}



