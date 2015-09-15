/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity(name = "Customer")
@Table(name = "`customers`")
@DiscriminatorValue("0")
public class Customer extends User {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public enum Gender {
        FEMALE,
        MALE,
        ND
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`gender`")
    private Gender gender = Gender.ND;

    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn(name="`measure_def`")
    private Measurement measureDef;

    @OneToMany(mappedBy="customer")
    @OrderBy("id ASC")
    private final List<Measurement> measurements;

    @JsonCreator
    public static Customer factory() {
        JsonFactory jsonFactory = new JsonFactory();
        return jsonFactory.getCurrentUser();
    }

    public Customer() {
        this.measurements = new ArrayList<>();
        setRole(Role.ROLE_CUSTOMER);
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Measurement getMeasureDef() {
        return measureDef;
    }

    public void setMeasureDef(Measurement measureDef) {
        this.measureDef = measureDef;
    }

	/**
     * @return the list of measurements
     */
    public List<Measurement> getMeasurements() {
        return measurements;
    }

    /**
     * @param item
     */
    public void addMeasurement(Measurement item) {
        item.setCustomer(this);
        measurements.add(item);
    }

    public Measurement getMeasurement(Integer id) {
        for (Measurement m : measurements) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

}
