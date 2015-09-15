/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity(name = "Vendor")
@Table(name = "`vendors`")
@DiscriminatorValue("1")
public class Vendor extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "`company`")
    private String company;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor")
    @OrderBy("id ASC")
    private final List<Product> products;

    @JsonCreator
    public static Vendor factory() {
        JsonFactory jsonFactory = new JsonFactory();
        return jsonFactory.getCurrentUser();
    }

    public Vendor() {
        this.products = new ArrayList<>();
        setRole(Role.ROLE_VENDOR);
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the measurements
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param item
     */
    public void addProduct(Product item) {
        item.setVendor(this);
        getProducts().add(item);
    }

    public Product getProduct(Integer id) {
        for (Product m : getProducts()) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

}
