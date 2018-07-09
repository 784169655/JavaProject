package com.springcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients //在本服务处定义 ProductClient 的形式 调用 Product服务
//调用 调用方服务定义的  ProductClient 的形式 调用 Product 服务
@EnableFeignClients(basePackages = "com.springcloud.client")
public class OrderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServerApplication.class, args);
	}
}
