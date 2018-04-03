package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

public class HistoryDto {

    @SerializedName("plugin_obj_id")
    private String pluginObjId;
    @SerializedName("item_id")
    private String itemId;
    @SerializedName("payment_model")
    private String paymentModel;
    @SerializedName("billable_period")
    private String billablePeriod;
    @SerializedName("bill_date")
    private String billDate;
    @SerializedName("rental_fee")
    private String rentalFee;
    @SerializedName("rental_period")
    private String rentalPeriod;
    @SerializedName("rental_start")
    private String rentalStart;
    @SerializedName("rental_end")
    private String rentalEnd;
    @SerializedName("return_date")
    private String returnDate;
    @SerializedName("code")
    private String code;
    @SerializedName("title")
    private String title;
    @SerializedName("payment_method")
    private Object paymentMethod;
    @SerializedName("renew_error")
    private String renewError;
    private Integer live;
    @SerializedName("rented_via")
    private String rentedVia;

    public String getPluginObjId() {
        return pluginObjId;
    }

    public void setPluginObjId(String pluginObjId) {
        this.pluginObjId = pluginObjId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPaymentModel() {
        return paymentModel;
    }

    public void setPaymentModel(String paymentModel) {
        this.paymentModel = paymentModel;
    }

    public String getBillablePeriod() {
        return billablePeriod;
    }

    public void setBillablePeriod(String billablePeriod) {
        this.billablePeriod = billablePeriod;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(String rentalFee) {
        this.rentalFee = rentalFee;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public String getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(String rentalStart) {
        this.rentalStart = rentalStart;
    }

    public String getRentalEnd() {
        return rentalEnd;
    }

    public void setRentalEnd(String rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Object paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getRenewError() {
        return renewError;
    }

    public void setRenewError(String renewError) {
        this.renewError = renewError;
    }

    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    public String getRentedVia() {
        return rentedVia;
    }

    public void setRentedVia(String rentedVia) {
        this.rentedVia = rentedVia;
    }

}
