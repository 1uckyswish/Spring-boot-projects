package com.amigoscode.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Greet {
    @GetMapping("/greet")
    public GreetResponse greet() {
        return new GreetResponse("hello", List.of("Java", "Spring"), new Person("John" , 21, 3000.00));
    }

    record GreetResponse(String message, List<String> fav, Person person) { }

    record Person(String name, int age, double cash) { }
//    class GreetResponse {
//        private String message;
//
//        public GreetResponse(String message) {
//            this.message = message;
//        }
//
//        public String getGreet() {
//            return message;
//        }
//
//        @Override
//        public String toString() {
//            return "GreetResponse{" +
//                    "message='" + message + '\'' +
//                    '}';
//        }
//    }
}
