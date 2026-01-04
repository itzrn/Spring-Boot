package com.spring.learning.service;

import com.spring.learning.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository; // these are things which will need the application context to run everytime, to not do this which may make the process light we can mock the things

    @Disabled // this helps to disable the test when you run the class
    @Test
    public void testFindByUserName(){
        assertEquals(4, 2+2);
        assertNotNull(userRepository.findByUserName("aryan"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,3,5"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b, "failed for "+a+" "+b); // this also accept some message which helps to figure out which test got fail
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1,
            3,
            4
    })
    public void test1(int a){
        assertEquals(4, a+1, "failed for "+a);
    }
}

/*

We can also provide our custom clas for the test
we have to use @ArgumentSource using ArgumentProvider interface which implements providerArgument function

to run using mvn
    run -> mvn test

we have a test annotation @BeforeEach
if it is applied on any function then that function will run first for every other test

@BeforeAll -> first run that particular function then run all other tests

similarly -> AfterEach and AfterAll


what is the mock?
    mean to provide a dummy things using which i can perform the test on the logic
    which can be achieved using mockito
 */
