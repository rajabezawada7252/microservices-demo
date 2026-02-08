package com.example.space.ingestion.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        HttpClient httpClient = HttpClient.create()
                // Max time to establish connection
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                // Max time to wait for response
                .responseTimeout(Duration.ofSeconds(2))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(2))
                            .addHandlerLast(new WriteTimeoutHandler(2)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
