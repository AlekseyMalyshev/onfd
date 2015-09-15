/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.onfd.model.TShirtSize.Size;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity(name = "Shorts")
@Table(name = "`shorts`")
@DiscriminatorValue("1")
public class ShortsSize extends ProductSize {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum Size {

        S29,
        S30,
        S31,
        S32,
        S33,
        s34
    }

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`product_size`")
    private ShortsSize.Size productSize;

    @Column(name = "`waist`")
    private Double waist;

    @Column(name = "`front_length`")
    private Double frontLength;

    @Column(name = "`back_length`")
    private Double backLength;

    public ShortsSize() {
        setType(Product.Type.SHORTS);
    }

    public ShortsSize(String name) {
        setType(Product.Type.SHORTS);
        productSize = Size.valueOf(name);
    }

    /**
     * @return the productSize
     */
    public ShortsSize.Size getProductSize() {
        return productSize;
    }

    /**
     * @param productSize the productSize to set
     */
    public void setProductSize(ShortsSize.Size productSize) {
        this.productSize = productSize;
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
     * @return the frontLength
     */
    public Double getFrontLength() {
        return frontLength;
    }

    /**
     * @param frontLength the frontLength to set
     */
    public void setFrontLength(Double frontLength) {
        this.frontLength = frontLength;
    }

    /**
     * @return the backLength
     */
    public Double getBackLength() {
        return backLength;
    }

    /**
     * @param backLength the backLength to set
     */
    public void setBackLength(Double backLength) {
        this.backLength = backLength;
    }

    @Override
    public String getName() {
        return productSize.name();
    }

    @Override
    public int compareTo(Object o) {
        ShortsSize s = (ShortsSize) o;
        return productSize.compareTo(s.productSize);
    }

    @Override
    public void applyMeasurement(Measurement measure) {
        // TODO Auto-generated method stub
    }

}
