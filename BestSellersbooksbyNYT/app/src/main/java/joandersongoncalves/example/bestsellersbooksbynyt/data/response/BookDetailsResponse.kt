package joandersongoncalves.example.bestsellersbooksbynyt.data.response

import com.squareup.moshi.JsonClass
import joandersongoncalves.example.bestsellersbooksbynyt.data.model.Book

@JsonClass(generateAdapter = true)
data class BookDetailsResponse (
    //@Json(name = "title")//não precisa , pois mesmo nome
    val title: String,
    //@Json(name = "author")//idem
    val author: String,
    //@Json(name = "description")//idem
    val description: String
) {
    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )
}