/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@Entity(name = "Product")
@Table(name = "`products`")
public class Product implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public enum Type {
        TSHIRT,
        SHORTS
    }

    public enum Gender {
        FEMALE,
        MALE,
        UNISEX
    }

    @Id
    @GeneratedValue
    @Column(name = "`id`")
    private int id;

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn(name="`manufacturer`")
    private Manufacturer manufacturer;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`type`")
    private Type type;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`gender`")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender = Gender.UNISEX;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="`vendor`")
    private Vendor vendor;

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

    @OneToMany(mappedBy="product", fetch = FetchType.EAGER)
    @OrderBy("id ASC")
    private final SortedSet<ProductSize> sizes;

    public Product() {
        this.sizes = new TreeSet<>();
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
     * @return the vendor
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the vendor to set
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
     * @return the list of sizes
     */
    public SortedSet<ProductSize> getSizes() {
        return sizes;
    }

    /**
     * @param item
     */
    public void addSize(ProductSize item) {
            item.setProduct(this);
            sizes.add(item);
    }

    public ProductSize getSize(String name) {
    	if (name.compareTo("new") != 0) {
            for (ProductSize s : sizes) {
                if (name.compareTo(s.getName()) == 0) {
                    return s;
                }
            }
        }
		// TODO: create size by type:
        // getType();
    	ProductSize ps = new TShirtSize(name);
    	ps.setProduct(this);
    	return ps;
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
        return "FROM Product";
    }

    public void applyMeasurement(Measurement measure) {
    	for (ProductSize s: sizes) {
            s.applyMeasurement(measure);
    	}
    }

}
