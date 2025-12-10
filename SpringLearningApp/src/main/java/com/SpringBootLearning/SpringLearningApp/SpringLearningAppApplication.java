package com.SpringBootLearning.SpringLearningApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// this annotation is available only at once place at the entry point
// which also scan all the beans in the package recursively
@SpringBootApplication
public class SpringLearningAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringLearningAppApplication.class, args);
	}

//	@Autowired
	private PaymentService paymentService;

	public SpringLearningAppApplication(PaymentService paymentService) {
		this.paymentService = paymentService;
		// this shows Dependency injection
		// Here Object of RazorpayPaymentService automatically get passed as it is present in the bean
	}

	@Override
	public void run(String... args) throws Exception { // this is the run method which get runs when the whole spring project
		// get setup, application context get created the scanning is done then just after this function runs.

		String payment = paymentService.pay();
		System.out.println("Payment done : "+payment);

	}

}

// Here one ambiguity will arise as we have the two type of payment services then spring will get confuse
// which is need to use
// it's better to make any of it primary(using @Primary) or remove it from bean