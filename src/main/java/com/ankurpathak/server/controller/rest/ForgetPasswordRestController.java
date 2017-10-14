package com.ankurpathak.server.controller.rest;

import com.ankurpathak.server.domain.model.User;
import com.ankurpathak.server.service.IEmailService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;

import com.ankurpathak.server.domain.repository.IUserRepository;

/**
 * Created by ankur on 03-02-2017.
 */
@RestController
@RequestMapping("/api/v2")
public class ForgetPasswordRestController {


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private MongoClient mongoClient;


    @Value("${spring.data.mongodb.database}")
    private String database;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private IEmailService emailService;






    @GetMapping("/forget-password")
    public ResponseEntity<?> register(@RequestParam(name = "username", required = true) String username){


        try{
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
            User user = userDetails.getUser();
            String password = String.format("Pass@%s", RandomStringUtils.randomAlphanumeric(8));
            System.out.println("password " + password);
            Document query = new Document("_id", user.getId());
            MongoDatabase db = mongoClient.getDatabase(database);
            MongoCollection<Document> col = db.getCollection("users");
            Document userDocument = col.find(query).first();
            Document updates =  new Document("$set", new Document("password", passwordEncoder.encode(password)));
            col.updateOne(userDocument, updates);
            emailService.sendForgetMail(user, password);
            return ResponseEntity.noContent().build();
        }catch (UsernameNotFoundException ex){
            return ResponseEntity.notFound().build();
        }


    }






}
