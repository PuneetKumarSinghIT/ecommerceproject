package com.ecommerce.ecommerseproject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*Annotation to tell spring boot that this class is to define the APIs call.*/
/*RestController allow JVM to know that we are going to right some APIs which
* need to be exposed to external world.
* */
@RestController
public class ControllerHello {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World! I am Puneet!";
    }

    @RequestMapping(value = "hello/{id}", method = RequestMethod.GET)
    public String sayHelloPersonal(@PathVariable("id") String id) {
        return "Hello world! I am " + id;
    }



}

// Learned about RestController.
// Learned about RequestMapping
// Learned about PathVariable
// Learned about RequestMethod