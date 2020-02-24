package top.cyblogs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Stomp的配置类
 *
 * @author CY
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // 客户端的订阅地址前缀
        config.enableSimpleBroker("/downloadStatus")
                // 让后端发送心跳，维持连接状态, 10S一次
                .setHeartbeatValue(new long[]{10000L, 10000L})
                .setTaskScheduler(new DefaultManagedTaskScheduler());
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket的连接地址
        registry.addEndpoint("/socketConnect")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}