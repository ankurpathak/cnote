package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 26-02-2017.
 */
public class EnquiryLineItem {


    @Field("sample")
    @NotNullx(label = "Sample")
    private Boolean sample;


    public Boolean getSample() {
        return sample;
    }

    public void setSample(Boolean sample) {
        this.sample = sample;
    }

    @DBRef
    @NotNullx(label = "Product")
    private Product product;


    @Field("remark")
    //@NotBlankx(label = "Remark")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Field("fields")
    private List<@Valid Property> fields;

    public List<Property> getFields() {
        return fields;
    }

    public void setFields(List<Property> fields) {
        this.fields = fields;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
