package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 25-02-2017.
 */

@Document(collection = "products")
@QueryEntity
public class Product extends ExtendedDomain implements Serializable {
    @Field("name")
    @NotBlankx(label = "Name")
    private String name;

    @Field("imgUrl")
    private String imgUrl;

    @Field("fields")
    public List<Property> fields;


    public List<Property> getFields() {
        return fields;
    }

    public void setFields(List<Property> fields) {
        this.fields = fields;
    }

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



    
    public Product() {
    }

    @DBRef(lazy = true)
    private Division division;


    @DBRef(lazy = true)
    private SubDivision subDivision;

    @DBRef(lazy = true)
    private Category category;

    @DBRef(lazy = true)
    private SubCategory subCategory;


    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public SubDivision getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(SubDivision subDivision) {
        this.subDivision = subDivision;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
