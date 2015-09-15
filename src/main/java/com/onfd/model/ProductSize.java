/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity
@Table(name = "`product-sizes`")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "`type`", discriminatorType = DiscriminatorType.INTEGER)
public abstract class ProductSize implements Serializable, Comparable<Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    enum CustomerFit {
        Undefined,
        Small,
        Tight,
        Fit,
        Loose,
        Large
    }

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "`product`")
    private Product product;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`type`", updatable = false, insertable = false)
    private Product.Type type;

    @JsonIgnore
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`added`", updatable = false, insertable = false)
    private Date addedDate;

    @JsonIgnore
    @Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`updated`", updatable = false, insertable = false)
    private Date updDate;

    @Transient
    protected CustomerFit customerFit;

    public ProductSize() {
        id = -1;
        customerFit = CustomerFit.Undefined;
    }

    @JsonIgnore
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
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the type
     */
    public Product.Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Product.Type type) {
        this.type = type;
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

    @JsonIgnore
    public abstract String getName();

    public abstract void applyMeasurement(Measurement measure);

    public CustomerFit getCustomerFit() {
        return customerFit;
    }
}
