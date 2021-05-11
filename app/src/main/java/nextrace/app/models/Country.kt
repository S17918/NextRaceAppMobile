package nextrace.app.models

class Country(val id: Int, val name: String) {
    fun getLink(): String{
        val link: String = "https://halkotrans.pl/src/flags/"
        val png: String = ".png"
        return link + name + png
    }
}