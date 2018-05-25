package com.ujjawal.springjms;

import com.ujjawal.springjms.model.Email;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class SpringJmsApplication {

	@Bean
	public JmsListenerContainerFactory myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory jmsFactory = new DefaultJmsListenerContainerFactory();
		configurer.configure(jmsFactory , connectionFactory);
		return jmsFactory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonMessageConverter(){
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		System.out.println("Sending an email message");
		jmsTemplate.convertAndSend("maill",new Email("inflll@gmail.com","thekkk"));
	}
}
