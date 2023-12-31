package com.example.elamirgroup.data.model


import com.google.gson.annotations.SerializedName

data class PromoResult(
    @SerializedName("ALL_RESULT_MANDATORY")
    val aLLRESULTMANDATORY: Boolean,
    @SerializedName("BONUS_VALUE_MONEY_AMOUNT_VALUE") //
    val bONUSVALUEMONEYAMOUNTVALUE: Int,
    @SerializedName("BONUS_VALUE_MONEY_TREATMENT_TYPE")

    val bONUSVALUEMONEYTREATMENTTYPE: Int,
    @SerializedName("BONUS_VALUE_PERCENT_VALUE") //
    val bONUSVALUEPERCENTVALUE: Int,
    @SerializedName("CRITERIA_CODE") //
    val cRITERIACODE: String,
    @SerializedName("CUT_OFF_PRICE_MONEY_AMOUNT_VALUE") //
    val cUTOFFPRICEMONEYAMOUNTVALUE: Int,
    @SerializedName("DISCOUNT_MAX_VALUE")//
    val dISCOUNTMAXVALUE: Int,
    @SerializedName("FIRST_DISCOUNT_MONEY_AMOUNT_VALUE")// if value will (-) from price
    val fIRSTDISCOUNTMONEYAMOUNTVALUE: Double,
    @SerializedName("FIRST_DISCOUNT_MONEY_TREATMENT_TYPE")//
    val fIRSTDISCOUNTMONEYTREATMENTTYPE: Double,
    @SerializedName("FIRST_DISCOUNT_PERCENT_VALUE")//if value will (%) from price
    val fIRSTDISCOUNTPERCENTVALUE: Double,
    @SerializedName("ITEM_RELATION_CODE")//
    val iTEMRELATIONCODE: String?,
    @SerializedName("ITEM_RELATION_TYPE")//
    val iTEMRELATIONTYPE: Int,
    @SerializedName("OPTIONAL")//
    val oPTIONAL: Int,
    @SerializedName("PromoCode")//
    val promoCode: String,
    @SerializedName("QUANTITY_VALUE")//
    val qUANTITYVALUE: Int,
    @SerializedName("RESULT_CODE")//
    val rESULTCODE: String,
    @SerializedName("SEQUENCE_NUMBER")//
    val sEQUENCENUMBER: Int,
    @SerializedName("TAX_FREE")//
    val tAXFREE: Int,
    @SerializedName("TRADE_DEAL_MATCHING_CRITERIA_ID")//
    val tRADEDEALMATCHINGCRITERIAID: Int,
    @SerializedName("TYPE")//
    val tYPE: Int,
    //  0 money on order ,
    //  2 = % on item , ****
    //  3 =add item free and pay it  , ****
    //  8 = money on item
    @SerializedName("UofM_ID")//
    val uofMID: Int,
    // 0 = case
    // 7 = psc
    // null not qty
    @SerializedName("NewItemCode")
    val groupItem: List<String>,

    )