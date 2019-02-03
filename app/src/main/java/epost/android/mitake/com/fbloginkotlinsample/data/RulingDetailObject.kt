package epost.android.mitake.com.fbloginkotlinsample.data

class RulingDetailObject {

    lateinit var detail: String //舉證內容
    lateinit var imgList: ArrayList<String> //舉證url


    constructor(detail: String, imgList: ArrayList<String> = ArrayList()) {
        this.detail = detail
        this.imgList = imgList
    }

    constructor()
}