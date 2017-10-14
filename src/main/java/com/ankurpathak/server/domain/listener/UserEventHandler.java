package com.ankurpathak.server.domain.listener;

import com.ankurpathak.server.domain.model.User;
import com.ankurpathak.server.domain.repository.IUserRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



/**
 * Created by ankur on 06-04-2017.
 */
@RepositoryEventHandler
@Component
public class UserEventHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private MongoClient mongoClient;


    @Value("${spring.data.mongodb.database}")
    private String database;

    @HandleBeforeSave
    public void handlePersonSave(User user) {
        Document query = new Document("_id", user.getId());
        MongoDatabase db = mongoClient.getDatabase(database);
        MongoCollection<Document> col = db.getCollection("users");
        Document userDocument = col.find(query).first();
        String password  = userDocument.get("password", String.class);
        Document updates =  new Document("$set", new Document("__password", password));
        col.updateOne(userDocument, updates);
    }


    @HandleAfterSave
    public void handlePersonSave1(User user) {
        Document query = new Document("_id", user.getId());
        MongoDatabase db = mongoClient.getDatabase(database);
        MongoCollection<Document> col = db.getCollection("users");
        Document userDocument = col.find(query).first();
        String password  = userDocument.get("password", String.class);
        String tempPassword  = userDocument.get("__password", String.class);
        if(password != null && password.equals(tempPassword))
            return;
        Document updates =  new Document("$set", new Document("password", passwordEncoder.encode(password)));
        updates.append("$unset", new Document("__password", true));
        col.updateOne(userDocument, updates);
    }



    @HandleAfterCreate
    //@Order(Ordered.LOWEST_PRECEDENCE)
    public void handlePersonCreate(User user) {
        Document query = new Document("_id", user.getId());
        MongoDatabase db = mongoClient.getDatabase(database);
        MongoCollection<Document> col = db.getCollection("users");
        Document userDocument = col.find(query).first();
        String password  = userDocument.get("password", String.class);
        Document updates =  new Document("$set", new Document("password", passwordEncoder.encode(password)));
        col.updateOne(userDocument, updates);
    }

}
