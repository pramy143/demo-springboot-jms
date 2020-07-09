package org.example.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfig {

    @Bean
    public JmsTransactionManager jmsTransactionManager() {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(activeMqConnectionFactory());
        return jmsTransactionManager;
    }

    @Bean
    public JmsComponent jmsComponent() {
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(activeMqConnectionFactory(), jmsTransactionManager());
        return jmsComponent;
    }

    @Bean
    public ConnectionFactory activeMqConnectionFactory(){
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }
}