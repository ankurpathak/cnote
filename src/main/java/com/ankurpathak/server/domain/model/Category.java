package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by ankur on 25-02-2017.
 */

@Document(collection = "categories")
@QueryEntity
public class Category extends ExtendedDomain implements Serializable {
    @Field("name")
    @NotBlankx(label = "Name")
    private String name;

    @Field("imgUrl")
    private String imgUrl;

    @Field("description")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Category() {
    }

    /*
    @DBRef
    private List<SubCategory> subCategories;

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    */
    @DBRef(lazy = true)
    @NotNullx(label = "SubDivision")
    private SubDivision subDivision;

    public SubDivision getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(SubDivision subDivision) {
        this.subDivision = subDivision;
    }
}
