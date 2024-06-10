package com.graphql.praphqlplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.graphql.praphqlplay.${lec}")
@EnableR2dbcRepositories(basePackages = "com.graphql.praphqlplay.${lec}")
public class GraphqlPlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlayApplication.class, args);
	}
}
