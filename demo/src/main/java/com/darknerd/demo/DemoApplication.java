package com.darknerd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/*

mvn compile -> to compile the project
mnv test -> if any test you have written then those test can be performed using this command in terminal
mvn package -> all the source code will get convert into jar, which u can easily share it to anyone
        one should only have the java and this .jar file and spring boot project will start to run
        java -jar filename.jar -> this is the command to run that particular jar file
mvn install -> in local there is a m2 repository, the jar of this project will get saved there
mvn clean -> will clear the target folder
inside target folder there will be one .jar.original -> this contains only the compiled code
inside target folder there will be one .jar -> this contains compiled code with all dependency, this .jar file is also called fat jar

packaging is done by the bellow plugin
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>



Core Concept
	1. Let there be a car class you want to make the object of it
		Car car = new Car(); -> this is used to make an instance of the car class, created by us
		But spring provide a functionality that we can ask to spring to make the object and provide it to us, means we are
		externalizing the creation of objects, hence we have inverted the control this is called as inversion of control

	2. Now one is need to go to IOC container and ask for any object
	3. IOC container keep all the classes with itself whatever present in the project
	4. Application context is a way to create IOC container(means application context is same to IOC container)
	5. those classes are kept in IOC container which are annotated with @Component(can be written on class, interface, method, field)
		after applying @Component, that item will automatically get registered in Spring bean
	6. bean in spring language is object
	7. @SpringBootApplication -> is mostly applied on tha main class, which tell you the entry point of the application
		this do 3 work:
			- @Configuration -> writing this means it will provide some configuration
								this is mostly used with another annotation that is @Bean(this annotation is only applied on function)
			- @EnableAutoConfiguration -> its like automate the configuration
										after adding the dependency of anything(like Mongodb) and application server gives all the info and set the environment
			- @ComponentScan -> this scan the classes to find which class is need to put in the IOC
								here in this project all the class will get scan which is under the package com.darknerd.demo

	8. @RestController -> this is a specialised version of @Component+something
	9. @Autowired
		is used when one class is dependent on other class
		using it there is no need to make the object of any class, just call the variable and start using it
 */

