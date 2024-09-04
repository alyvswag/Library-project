package com.example.libraryproject.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue emailQueue() {
        return new Queue("emailQueue", false);
    }

    @Bean
    public Queue reminderQueue() {
        return new Queue("reminderQueue", false);
    }

    @Bean
    public Queue bookQueue() {
        return new Queue("bookQueue", false);
    }
    @Bean
    public Queue eventQueue() {
        return new Queue("eventQueue", false);
    }

    //----
    @Bean
    public TopicExchange emailTopicExchange() {
        return new TopicExchange("emailTopicExchange", true, false);
    }
    //------

    @Bean
    public Binding binding(Queue emailQueue, TopicExchange emailTopicExchange) {
        return BindingBuilder.bind(emailQueue).to(emailTopicExchange).with("email.key");
    }

    @Bean
    public Binding reminderBinding(Queue reminderQueue, TopicExchange emailTopicExchange) {
        return BindingBuilder.bind(reminderQueue).to(emailTopicExchange).with("reminder.key");
    }

    @Bean
    public Binding bookBinding(Queue bookQueue, TopicExchange emailTopicExchange) {
        return BindingBuilder.bind(bookQueue).to(emailTopicExchange).with("book.key");
    }
    @Bean
    public Binding eventBinding(Queue eventQueue, TopicExchange emailTopicExchange) {
        return BindingBuilder.bind(eventQueue).to(emailTopicExchange).with("event.key");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
