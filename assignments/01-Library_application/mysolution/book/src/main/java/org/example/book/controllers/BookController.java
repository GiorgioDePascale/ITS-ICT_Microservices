package org.example.book.controllers;

import org.example.book.models.User;
import org.example.book.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v2/prova")
public class BookController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable String name){
        return "Hello " + name;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void setUser(@RequestBody User user){
        userRepository.save(user);
        System.out.println(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId){
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            return userOpt.get();
        }else{
            return null;
        }
    }

}
