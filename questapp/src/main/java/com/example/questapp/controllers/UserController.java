package com.example.questapp.controllers;

import com.example.questapp.entities.User;
import com.example.questapp.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;// repo = bir şey şeklinde yazılmadı, onun yerine bu işi spring boota bırakıp
    //userRepository objesinin bean'ini bulup inject edecek, içerisine atayacak gibi düşünebiliriz
    //bunun için autowired, setter injection, constructor injection gibi metodlar var

    //Constructor injection
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //custom exception ekle
        return userRepository.findById(userId).orElse(null); //id bulamazsa bu user ı null objesi dönecek
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }



}
