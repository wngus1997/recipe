package com.food.recipe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
	@PropertySource(value="file:c:/springWorkspace/rcpConfigure.properties", ignoreResourceNotFound=true)
})
@ComponentScan(basePackages = {"com.food.recipe;"})
@MapperScan(basePackages = {"com.food.recipe;"})
public class RecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}

}
