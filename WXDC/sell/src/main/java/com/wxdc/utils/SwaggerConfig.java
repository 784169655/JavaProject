/**
 *
 */
package com.wxdc.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author qiuwei
 * http://localhost:8080/swagger-ui.html
 */

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig implements EnvironmentAware {

	public static final String DEFAULT_INCLUDE_PATTERN = "/sell/.*";
	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment,"swagger.");
	}

	@Bean
    public Docket swaggerSpringfoxDocker(){
    	log.info("Swagger组件开始启动");
    	StopWatch watch = new StopWatch();
    	watch.start();
		Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
				.enable(true)
				.apiInfo(apiInfo())
				.genericModelSubstitutes(ResponseEntity.class)
				.select()
//    			.paths(PathSelectors.any()) // 加载任何url路径下的方法
				// 只加载当前url路径下的  并且当前url是requestMapping里出现的，不能是yml中的context-path
//				.paths(regex(DEFAULT_INCLUDE_PATTERN))
				.build();
		watch.stop();
    	log.info("Swagger组件启动完成 {} ms", watch.getTotalTimeMillis());
    	return swaggerSpringMvcPlugin;
    }

	 private ApiInfo apiInfo() {
	        return new ApiInfo(
					propertyResolver.getProperty("title"),
					propertyResolver.getProperty("description"),
					propertyResolver.getProperty("version"),
					propertyResolver.getProperty("termsOfServiceUrl"),
					propertyResolver.getProperty("contact"),
					propertyResolver.getProperty("license"),
					propertyResolver.getProperty("licenseUrl")
	        );
	    }
}
