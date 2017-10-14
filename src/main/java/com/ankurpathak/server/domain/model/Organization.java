package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import com.ankurpathak.server.validation.constraints.UrlOptional;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by ankur on 05-02-2017.
 */
@Document(collection = "organizations")
@QueryEntity
public class Organization extends Domain implements Serializable{


    @Field("name")
    @NotBlankx(label = "Name")
    private String name;


    @Field("ref")
    @NotBlankx(label = "Ref")
    private String ref;


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Valid
    @NotNullx(label = "Location")
    @Field("location")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Field("imgBaseUrl")
    @UrlOptional(label = "Image Base Url")
    private String imgBaseUrl;


    public String getImgBaseUrl() {
        return imgBaseUrl;
    }

    public void setImgBaseUrl(String imgBaseUrl) {
        this.imgBaseUrl = imgBaseUrl;
    }

    @Field("imgUrl")
    private String imgUrl;

    @Field("hierarchy")
    private Hierarchy hierarchy;






    //@DBRef
    //private List<User> users;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public enum Hierarchy{
        LEVEL1, LEVEL2, LEVEL3, LEVEL4
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }


    public Organization() {
    }


}
