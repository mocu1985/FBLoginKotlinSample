package epost.android.mitake.com.fbloginkotlinsample.data

public class Info {
    var name: String? = null
    var birthday: String? = null
    var sex: Boolean = true
    var address: String? = null

    constructor()
    constructor(name: String, birthday: String, sex: Boolean = true, address: String = "") {
        this.name = name
        this.birthday = birthday
        this.sex = sex
        this.address = address
    }
}
