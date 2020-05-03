package joandersongoncalves.example.bestsellersbooksbynyt.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joandersongoncalves.example.bestsellersbooksbynyt.R
import joandersongoncalves.example.bestsellersbooksbynyt.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(
        private val books: List<Book>,
        val onItemClickListener: ((book: Book) -> Unit)
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    class BooksViewHolder(
        itemView: View,
        private val onItemClickListener: ((book: Book) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.textTitle
        private val author = itemView.textAuthor

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
                onItemClickListener.invoke(book)
            }
        }
    }
}