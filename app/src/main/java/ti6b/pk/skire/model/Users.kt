package ti6b.pk.skire.model

class Users (
    val name: String? = null,
    val email: String? = null,
    val no_hp: String? = null,
    val pendidikan: String? = null
){
    constructor() : this("", "","","") {

    }
}