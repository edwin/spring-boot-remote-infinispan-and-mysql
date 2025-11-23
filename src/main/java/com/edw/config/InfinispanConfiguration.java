package com.edw.config;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.jboss.marshalling.commons.GenericJBossMarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *  com.edw.config.InfinispanConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 23 Nov 2025 15:04
 */
@Configuration
public class InfinispanConfiguration {

    @Value("${jdg.servers}")
    private String jdgHostAddress;

    @Value("${jdg.userName}")
    private String userName;

    @Value("${jdg.password}")
    private String password;

    @Bean
    public RemoteCacheManager remoteCacheManager() {
        return new RemoteCacheManager(
                new ConfigurationBuilder()
                        .addServers(jdgHostAddress)
                        .security().authentication().username(userName).password(password)
                        .clientIntelligence(ClientIntelligence.HASH_DISTRIBUTION_AWARE)
                        .marshaller(new GenericJBossMarshaller())
                        .build());
    }

}
