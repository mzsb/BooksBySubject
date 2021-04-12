package hu.mzsb.booksbysubject.di

import dagger.Module
import dagger.Provides
import hu.mzsb.booksbysubject.ui.bookdetails.BookDetailsPresenter
import hu.mzsb.booksbysubject.ui.books.BooksPresenter

@Module
class PresenterModule {
    @Provides
    fun provideBooksPresenter(): BooksPresenter = BooksPresenter()

    @Provides
    fun provideBookDetailsPresenter(): BookDetailsPresenter = BookDetailsPresenter()
}