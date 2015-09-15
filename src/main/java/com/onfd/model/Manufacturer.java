/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity(name = "Manufacturer")
@Table(name = "`manufacturers`")
public class Manufacturer implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "`id`")
    private int id;

    @OneToMany(mappedBy="manufacturer")
    @OrderBy("id ASC")
    private final List<Product> products;

    @Column(name = "`name`")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="`vendor`")
    private Vendor vendor;

    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`added`", updatable = false, insertable = false)
    private Date addedDate;

    @Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`updated`", updatable = false, insertable = false)
    private Date updDate;

    public Manufacturer() {
        this.products = new ArrayList<>();
        id = -1;
    }

    public boolean isNew() {
        return id == -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the products by manufacturer
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @return the vendor
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the addedDate
     */
    public Date getAddedDate() {
        return addedDate;
    }

    /**
     * @return the updDate
     */
    public Date getUpdDate() {
        return updDate;
    }

    public static String getAllQuery() {
        return "FROM Manufacturer";
    }

    public static String getByIdQuery() {
        return "FROM Manufacturer m WHERE m.id = :p0";
    }
}
