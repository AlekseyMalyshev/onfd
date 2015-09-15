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

import com.onfd.model.Customer.Gender;

/**
 *
 * @author Aleksey Malyshev
 */
@Entity(name = "TShirt")
@Table(name = "`t-shirts`")
@DiscriminatorValue("0")
public class TShirtSize extends ProductSize {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum Size {

        XS,
        S,
        M,
        L,
        XL,
        XXL
    }

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`product_size`")
    private TShirtSize.Size productSize;

    @Column(name = "`bust`")
    private Double bust;
    private static final double[] bustScaleF = {1.0, 1.5, 2.5, 4.0};
    private static final double[] bustScaleM = {1.0, 2.0, 3.0, 4.0};

    @Column(name = "`shoulder`")
    private Double shoulder;

    @Column(name = "`waist`")
    private Double waist;
    private static final double[] waistScaleF = {0.5, 0.75, 2.0, 3.0};
    private static final double[] waistScaleM = {0.75, 1.0, 2.0, 3.0};

    @Column(name = "`armhole`")
    private Double armhole;
    private static final double[] armholeScaleF = {0.0, 0.5, 1.0, 2.0};
    private static final double[] armholeScaleM = {0.0, 0.5, 1.5, 3.0};

    @Column(name = "`front_length`")
    private Double frontLength;

    @Column(name = "`back_length`")
    private Double backLength;

    @Column(name = "`neck_depth`")
    private Double neckDepth;

    @Column(name = "`neck_width`")
    private Double neckWidth;

    @Column(name = "`sleeve_length`")
    private Double sleeveLength;

    @Column(name = "`sleeve_width`")
    private Double sleeveWidth;

    @Column(name = "`hem`")
    private Double hem;

    public TShirtSize() {
        setType(Product.Type.TSHIRT);
    }

    public TShirtSize(String name) {
        setType(Product.Type.TSHIRT);
        productSize = Size.valueOf(name);
    }

    /**
     * @return the productSize
     */
    public TShirtSize.Size getProductSize() {
        return productSize;
    }

    /**
     * @param productSize the productSize to set
     */
    public void setProductSize(TShirtSize.Size productSize) {
        this.productSize = productSize;
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

    /**
     * @return the neckDepth
     */
    public Double getNeckDepth() {
        return neckDepth;
    }

    /**
     * @param neckDepth the neckDepth to set
     */
    public void setNeckDepth(Double neckDepth) {
        this.neckDepth = neckDepth;
    }

    /**
     * @return the neckWidth
     */
    public Double getNeckWidth() {
        return neckWidth;
    }

    /**
     * @param neckWidth the neckWidth to set
     */
    public void setNeckWidth(Double neckWidth) {
        this.neckWidth = neckWidth;
    }

    /**
     * @return the sleeveLength
     */
    public Double getSleeveLength() {
        return sleeveLength;
    }

    /**
     * @param sleeveLength the sleeveLength to set
     */
    public void setSleeveLength(Double sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    /**
     * @return the sleeveWidth
     */
    public Double getSleeveWidth() {
        return sleeveWidth;
    }

    /**
     * @param sleeveWidth the sleeveWidth to set
     */
    public void setSleeveWidth(Double sleeveWidth) {
        this.sleeveWidth = sleeveWidth;
    }

    /**
     * @return the hem
     */
    public Double getHem() {
        return hem;
    }

    /**
     * @param hem the hem to set
     */
    public void setHem(Double hem) {
        this.hem = hem;
    }

    @Override
    public String getName() {
        return productSize.name();
    }

    @Override
    public int compareTo(Object o) {
        TShirtSize s = (TShirtSize) o;
        return productSize.compareTo(s.productSize);
    }

    private static CustomerFit calculateFit(double item, double body, double[] scale) {

        CustomerFit fit = CustomerFit.Undefined;

        // body measurement is circumference
        item *= 2;

        if (item < body + scale[0]) {
            fit = CustomerFit.Small;
        }
        else if (item >= body + scale[0]
                && item < body + scale[1]) {
            fit = CustomerFit.Tight;
        }
        else if (item >= body + scale[1]
                && item < body + scale[2]) {
            fit = CustomerFit.Fit;
        }
        else if (item >= body + scale[2]
                && item < body + scale[3]) {
            fit = CustomerFit.Loose;
        }
        else {
            fit = CustomerFit.Large;
        }
        return fit;
    }

    private void applyFit(CustomerFit fit) {
        if (fit != CustomerFit.Undefined) {					// This fit does not affect overall ranking
            if (CustomerFit.Small == fit || // If it is small, then it is small
                    CustomerFit.Undefined == customerFit || // The first measure
                    customerFit.ordinal() > fit.ordinal()) {	// This fit is smaller, apply it
                customerFit = fit;
            }
        }
    }

    @Override
    public void applyMeasurement(Measurement measure) {
        Gender g = measure.getCustomer().getGender();

        if (g == Gender.FEMALE) {
            applyFit(calculateFit(bust, measure.getBust(), bustScaleF));
            applyFit(calculateFit(waist, measure.getWaist(), waistScaleF));
            applyFit(calculateFit(armhole, measure.getArmhole(), armholeScaleF));
        }
        else if (g == Gender.MALE) {
            applyFit(calculateFit(bust, measure.getBust(), bustScaleM));
            applyFit(calculateFit(waist, measure.getWaist(), waistScaleM));
            applyFit(calculateFit(armhole, measure.getArmhole(), armholeScaleM));
        }
    }

}
