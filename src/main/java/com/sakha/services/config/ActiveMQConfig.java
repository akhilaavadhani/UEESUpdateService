package com.sakha.services.config;

import com.sakha.services.pojo.Queues;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

import static com.sakha.services.pojo.Queues.QueueName.*;

/**
 * Created by welcome on 29/12/15.
 */
@Configuration
public class ActiveMQConfig extends SingleRouteCamelConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ActiveMQConfig.class);

    @Value("${activemq.broker.url}")
    String brokerUrl;

    @Bean
    ConnectionFactory jmsConnectionFactory() {
        LOG.info("Connected to JMS Queue on {} ", brokerUrl);
        return new ActiveMQConnectionFactory(brokerUrl);
    }


    @Override
    public RouteBuilder route() {
        LOG.info("Initializing camel routes......................");
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(PPP_ES.getQueueName()).to("bean:pppESUpdater?method=updateES");
                //from(QueueName.PPP_MONGO.queueName).to("bean:pppMongoUpdater?method=updateMongo");
            }
        };
    }
}
