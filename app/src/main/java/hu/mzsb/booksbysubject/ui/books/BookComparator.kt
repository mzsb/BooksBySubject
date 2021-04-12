package hu.mzsb.booksbysubject.ui.books

import androidx.recyclerview.widget.DiffUtil
import hu.mzsb.booksbysubject.ui.books.models.UiBook

object BookComparator : DiffUtil.ItemCallback<UiBook>() {

    override fun areItemsTheSame(oldItem: UiBook, newItem: UiBook): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiBook, newItem: UiBook): Boolean {
        return oldItem == newItem
    }
}