package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import com.ankurpathak.server.validation.constraints.Url;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by ankur on 25-02-2017.
 */

@Document(collection = "subDivisions")
@QueryEntity
public class SubDivision extends ExtendedDomain implements Serializable {
    @Field("name")
    @NotBlankx(label = "Name")
    private String name;

    @Url(ignoreBlank = true)
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


    public SubDivision() {
    }

    /*
    @DBRef
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    */
    @DBRef(lazy = true)
    @NotNullx(label = "Division")
    private Division division;

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
