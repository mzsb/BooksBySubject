package hu.mzsb.booksbysubject.di


import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import hu.mzsb.booksbysubject.ui.bookdetails.BookDetailsViewModel
import hu.mzsb.booksbysubject.ui.books.BooksPresenter
import hu.mzsb.booksbysubject.ui.books.BooksViewModel
import javax.inject.Singleton


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindBooksViewModel(booksViewModel: BooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailsViewModel::class)
    abstract fun bindBookDetailsViewModel(bookDetailsViewModel: BookDetailsViewModel): ViewModel
}