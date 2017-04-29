package com.crowcompass.takehome.config;


import com.crowcompass.takehome.JaxRsApiApplication;
import com.crowcompass.takehome.resource.FirstResource;
import com.crowcompass.takehome.resource.InternationalFixedCalendarResource;
import com.crowcompass.takehome.service.FirstService;
import com.crowcompass.takehome.service.InternationalFixedCalendarService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;

/**
 * Created by claytongraham on 10/25/15.
 */

@Configuration
public class AppConfig {
    @Bean( destroyMethod = "shutdown" )
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Server jaxRsServer() {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(
                jaxRsApiApplication(), JAXRSServerFactoryBean.class );

        // ALWAYS ADD NEW RESOURCES
        factory.setServiceBeans( Arrays.< Object >asList(
                internationalFixedCalendarResource(), firstResource()) );

        factory.setAddress( factory.getAddress() );
        factory.setProviders( Arrays.< Object >asList(jsonProvider()) );

        return factory.create();
    }



    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }

    @Bean
    public FirstResource firstResource() {
        return new FirstResource();
    }

    @Bean
    public InternationalFixedCalendarResource internationalFixedCalendarResource() {
        return new InternationalFixedCalendarResource();
    }

    @Bean
    public FirstService firstService() {
        return new FirstService();
    }

    @Bean
    public InternationalFixedCalendarService internationalFixedCalendarService() {
        return new InternationalFixedCalendarService();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
}