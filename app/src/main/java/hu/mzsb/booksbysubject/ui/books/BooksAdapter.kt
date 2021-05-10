package hu.mzsb.booksbysubject.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.mzsb.booksbysubject.databinding.BookRowBinding
import hu.mzsb.booksbysubject.ui.books.models.UiBook
import kotlinx.android.synthetic.main.book_row.view.*

class BooksAdapter : ListAdapter<UiBook, BooksAdapter.BookViewHolder>(BookComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            BookRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)

        holder.binding.title.text = book.title
        holder.binding.authorName.text = if(book.authorName.isNotEmpty()) book.authorName else "Anonymous"
        if(book.isRead){
            holder.binding.ivIsRead.visibility = View.VISIBLE
        }
        else {
            holder.binding.ivIsRead.visibility = View.INVISIBLE
        }
        holder.binding.root.setOnClickListener {
            listener?.onBookClicked(book)
        }
    }

    inner class BookViewHolder(val binding: BookRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onBookClicked(book: UiBook)
    }
}