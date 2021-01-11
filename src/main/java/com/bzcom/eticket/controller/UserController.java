package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.ConfirmationToken;
import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.model.User;
import com.bzcom.eticket.repository.ConfirmationTokenRepository;
import com.bzcom.eticket.service.TicketService;
import com.bzcom.eticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@RequestMapping({"/users"})
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    // Get All User
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    // Get a Single User
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Integer userId) {
        return userService.findById(userId);
    }

    // find user by name
    @GetMapping("/search/{name}")
    public List<User> searchUser(@PathVariable(value = "name") String name) {
        return userService.searchUser(name);
    }

    //find User by phone number
    @GetMapping("/phoneNumber")
    public User findUserByPhoneNumber(@RequestParam String phoneNum) {
        return userService.findUserByPhoneNumber(phoneNum);
    }

    // Create a new User
    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    // Update a User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Integer userId, @RequestBody User userDetail) {
        userDetail.setId(userId);
        User updateUser = userService.save(userDetail);
        return updateUser;
    }

}
