package com.spring.learning; // this is the base package where @SpringBootApplication will scan all the Components

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // this annotation is only applied on main class
public class SpringLearningApplication {

	public static void main(String[] args) {
		// we can also print which environment is running
		ConfigurableApplicationContext context = SpringApplication.run(SpringLearningApplication.class, args);
		System.out.println(context.getEnvironment().getActiveProfiles()[0]); // getActiveProfiles returns an array means that we can provide multiple .properties

	}

//	@Bean
//	public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
//		return new MongoTransactionManager(dbFactory); // this requires MongoDatabaseFactory as parameter
//	}
	// we can also put this in a separate package with separate class

}


