package com.ms.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    // obter o valor que eu coloquei no application.properties, em seguida criar um metodo para iniciar o fila
    @Value("${broker.queue.email.name}") // acesso ao valor da propriedade
    private String queue;

    @Bean
    public Queue queue(){
        return new Queue(queue, true); // true é para saber se ela é duravel ou não, se o servidor parar se a fila vai ser preservada
    }

    @Bean
    // configuração de conversão, pois vamos estar rececebendo mensagem com o corpo em json, e vamos depois estar fazendo a conversão
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
