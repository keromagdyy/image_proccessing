package com.example.elamirgroup.data.model


import com.google.gson.annotations.SerializedName

data class PromotionHeader(
    @SerializedName("CALC_LEVEL")
    var calcLevel: Int, // will calc. from gross at this level will not add here
    @SerializedName("FIRING_LEVEL")
    var firingLevel: Int, // will remove from gross at this level
    @SerializedName("WITH_HOLDING_TAX_PERCENT_VALUE")
    var WITH_HOLDING_TAX_PERCENT_VALUE: Double, // value of tax %
    @SerializedName("WITH_HOLDING_FIRING_LEVEL")
    var withHoldingFiringLevel: Int,// tax fire at level

    @SerializedName("CHARGE_ID")
    var chargeID: Int,
    @SerializedName("CHARGE_TYPE")
    var chargeType: Int, // promo or discount
    @SerializedName("PromoCode")
    var promoCode: String, // promoCode
    @SerializedName("CUST_CLASSIFICATION_CODE")
    var customerClassificationCode: String,
    @SerializedName("DATE_FROM")
    var dateFrom: String,
    @SerializedName("DATE_TO")
    var dateTo: String,
    @SerializedName("DESCRIPTION")
    var description: String,

    @SerializedName("ITEMS_CLASSIFICATION_CODE")
    var itemClassificationCode: String, // group item
    @SerializedName("VERSION")
    var version: Int,
    @SerializedName("VERSION_DATE_FROM")
    var versionDateFrom: String,
//    @SerializedName("VERSION_DATE_TO")
//    var versionDateTo: String,

    @SerializedName("EXCLUSIVE_MATCHING_CRITERIA")
    var isExclusive: Int, //  0 = complete any condition , 1 is take first one only,
    var MULTIPLICITY: Int, // in the same order
    var REPEAT: Int, // repeat in all orders
    @SerializedName("TYPE")
    var TYPE: Int,
    @SerializedName("SEND_TO_CUSTOMER_APP")
    var SEND_TO_CUSTOMER_APP: Boolean,


    )