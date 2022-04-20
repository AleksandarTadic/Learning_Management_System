package rs.ac.singidunum.isa.app.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMQConfiguration {
    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("obavestenje");
    }

//    @Bean
//    public Topic logTopic() {
//        return new ActiveMQTopic("obavestenje");
//    }
}
