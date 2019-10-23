package com.brokenribs.app.data.network.reponse

data class PhotosModel (

    val id : Int,

    val width : Int,

    val height : Int,

    val url : String,

    val photographer : String,

    val photographer_url : String,

    val photographer_id : String,

    val src : Any,

    val liked : Boolean

)