package com.SpringBootLearning.SpringLearningApp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
// @Service
// @Controller
// @Repository
// the above three is coming through Component annotation
// @RestController
// any of the above can be used
@ConditionalOnProperty(name = "payment.provider", havingValue = "strip")
public class StripPaymentService implements PaymentService{
    @Override
    public String pay(){
        String payment = "Strip Payment";
        System.out.println("Payment From : "+payment);
        return payment;
    }
}
