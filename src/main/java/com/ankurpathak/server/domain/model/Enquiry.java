package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import com.ankurpathak.server.validation.constraints.SizeOneOrMore;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 14-05-2017.
 */

@Document(collection = "enquiries")
@QueryEntity
public class Enquiry extends ExtendedDomain implements Serializable {

    public Enquiry() {
    }

    @NotNullx(label = "Line items")
    @SizeOneOrMore(label = "Line items")
    @Field("lineItems")
    private List<@Valid EnquiryLineItem> lineItems;


    public List<EnquiryLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<EnquiryLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Field("remark")
    @NotBlankx(label = "Remark")
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

    @DBRef(lazy = true)
    @NotNullx(label = "Customer")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
