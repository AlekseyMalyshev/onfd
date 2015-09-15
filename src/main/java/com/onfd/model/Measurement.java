/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity(name = "Measurement")
@Table(name = "`measurements`")
public class Measurement implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public enum Fit {
        TIGHT,
        NORMAL,
        LOOSE
    }

    @Id
    @GeneratedValue
    @Column(name = "`id`")
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="`customer`")
    private Customer customer;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`bust`")
    private Double bust;

    @Column(name = "`shoulder`")
    private Double shoulder;

    @Column(name = "`waist`")
    private Double waist;

    @Column(name = "`armhole`")
    private Double armhole;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`fit`")
    private Fit fit = Fit.NORMAL;

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

    @JsonCreator
    public static Measurement factory(@JsonProperty("id") int id) {
        JsonFactory jsonFactory = new JsonFactory();
        return jsonFactory.getMeasurement(id);
    }

    public Measurement() {
        id = -1;
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param user the customer to set
     */
    public void setCustomer(Customer user) {
        this.customer = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	/**
     * @return the bust
     */
    public Double getBust() {
        return bust;
    }

    /**
     * @param bust the bust to set
     */
    public void setBust(Double bust) {
        this.bust = bust;
    }

    /**
     * @return the shoulder
     */
    public Double getShoulder() {
        return shoulder;
    }

    /**
     * @param shoulder the shoulder to set
     */
    public void setShoulder(Double shoulder) {
        this.shoulder = shoulder;
    }

    /**
     * @return the waist
     */
    public Double getWaist() {
        return waist;
    }

    /**
     * @param waist the waist to set
     */
    public void setWaist(Double waist) {
        this.waist = waist;
    }

    /**
     * @return the armhole
     */
    public Double getArmhole() {
        return armhole;
    }

    /**
     * @param armhole the armhole to set
     */
    public void setArmhole(Double armhole) {
        this.armhole = armhole;

    }

    /**
     * @return the fit
     */
    public Fit getFit() {
        return fit;
    }

    /**
     * @param fit the fit to set
     */
    public void setFit(Fit fit) {
        this.fit = fit;
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
}
