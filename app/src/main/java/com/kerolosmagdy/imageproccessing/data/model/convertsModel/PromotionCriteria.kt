package com.example.elamirgroup.data.model


import com.google.gson.annotations.SerializedName

data class PromotionCriteria(
// condition to get offers
    // il item line
    @SerializedName("ALL_RESULT_MANDATORY")
    val allResultMandatory: Boolean,
    @SerializedName("CODE")
    val criteriaCode: String, //Criteria code
    @SerializedName("CUTSOMER_RELATION_CODE")
    val customerRelationCode: String,
    @SerializedName("CUTSOMER_RELATION_TYPE")
    val customerRelationType: Int,
    @SerializedName("DISTINCT_COUNT")
    val distinctCount: Int,
    @SerializedName("PromoCode")
    val promoCode: String,
    @SerializedName("IL_CRITERIA_TYPE")
    val iLCriteriaType: Int,
    /*
    Criteria Line
    0 = min gross value money
    1=
    2=qty
    3=volume
    4=
    5=
     */
    @SerializedName("IL_ITEM_RELATION_CODE")
    val iLItemRelationCode: String, // 1 == group name ,2 ==item COde ,if all == empty null
    @SerializedName("IL_ITEM_RELATION_TYPE")
    val iLItemRelationType: Int, // 0 all 1 item_group 2 itemCode
    @SerializedName("IL_MIN_AMOUNT_VALUE")
    val iLMinAmountValue: Int,
    @SerializedName("IL_MIN_QUANTITY_VALUE")
    val ilMinQuantityValue: Int,
    @SerializedName("IL_UOFM_CODE")
    val iLUOFMCode: String, // if IL_MIN_QUANTITY_VALUE else empty
    @SerializedName("IS_MANDATORY")
    val isMandatory: Boolean,
    @SerializedName("MINIMUM_VOLUME")
    val minimumVolume: Int,
    @SerializedName("MIN_MATCHING_CRITERIA")
    val minMatchingCriteria: Int, // 0 is all condition is success , if number must success all numbers conditions
    @SerializedName("SEQUENCE_NUMBER")
    val sequenceNumber: Int,
    @SerializedName("NewItemCode")
    val groupItem: List<String> ,

    )