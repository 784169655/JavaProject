package com.spring2.encounter.config;

import com.spring2.encounter.domain.User;
import com.spring2.encounter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * 路由函数  配置    java版的 XML 文件
 * Created by  邱伟
 * 2018/3/27 10:52
 */

@Configuration
public class RouterFunctionConfiguration {

    /**
     * 请求  ServletRequest  或者  HttpServletRequest
     * 响应  ServletResponse  或者  HttpServletResponse
     * <p>
     * spring 5.0  重新定义了服务请求和响应接口
     * 请求接口： ServletRequest
     * 响应接口： ServletResponse
     * <p>
     * 即可支持  Servlet  规范   也可以支持自定义如 Netty （Web Service）
     *
     *
     * Flux  是0 到 N 个对象
     * Mono  是0 到 1 个对象集合
     *
     * Reactive 中的  Flux 和 Mono 是异步非阻塞处理
     * 之前的集合基本上是同步处理。(阻塞式的)
     *
     * Flux 或 Mono 都是 Publisher
     */

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository) {

        return RouterFunctions.route(RequestPredicates.GET("/person/findAll"),
                request -> {
                    //返回所有的对象
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class);
                });
    }
}