/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Aleksey Malyshev
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.EXISTING_PROPERTY, property="role")
@JsonSubTypes({@Type(value=Vendor.class, name="ROLE_VENDOR"),
               @Type(value=Customer.class, name="ROLE_CUSTOMER")})
@Entity(name = "User")
@Table(name = "`users`")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "`u_role`", discriminatorType = DiscriminatorType.INTEGER)
public abstract class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum Role {
        ROLE_CUSTOMER,
        ROLE_VENDOR,
        ROLE_ADMIN
    }

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "`id`")
    private int id;

    @NotEmpty
    @Column(name = "`login`")
    private String login;

    @JsonIgnore
    @NotEmpty
    @Size(min = 4, max = 64)
    @Column(name = "`pass`")
    private String password;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`u_role`", updatable = false, insertable = false)
    private Role role;

    @Column(name = "`name_first`")
    private String nameFirst;

    @Column(name = "`name_last`")
    private String nameLast;

    @NotEmpty
    @Email
    @Column(name = "`email`")
    private String email;

    @Column(name = "`phone`")
    private String phone;

    @Column(name = "`address`")
    private String address;

    @Column(name = "`city`")
    private String city;

    @Column(name = "`zipcode`")
    private Integer zipcode;

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

    public static String getQueryByName() {
        return "FROM User u WHERE u.login = :p0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
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
