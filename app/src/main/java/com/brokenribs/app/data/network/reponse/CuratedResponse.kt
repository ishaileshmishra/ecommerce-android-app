package com.brokenribs.app.data.network.reponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CuratedResponse {


    @SerializedName("page")
    @Expose
    private val page: String? = null

    @SerializedName("per_page")
    @Expose
    private val per_page : String? = null

    @SerializedName("total_results")
    @Expose
    private val total_results : String? = null

    @SerializedName("photos")
    @Expose
    private val photos: List<PhotosModel>? = null
}



class Photos {

    @SerializedName("id")
    @Expose
    private val id : Int? = null

    @SerializedName("width")
    @Expose
    private val width : Int? = null

    @SerializedName("height")
    @Expose
    private val height : Int? = null

    @SerializedName("url")
    @Expose
    private val url : String? = null

    @SerializedName("photographer")
    @Expose
    private val photographer : String? = null

    @SerializedName("photographer_url")
    @Expose
    private val photographer_url : String? = null

    @SerializedName("photographer_id")
    @Expose
    private val photographer_id : String? = null

    @SerializedName("src")
    @Expose
    private val src : String? = null

    @SerializedName("liked")
    @Expose
    private val liked : Boolean? = null

}
