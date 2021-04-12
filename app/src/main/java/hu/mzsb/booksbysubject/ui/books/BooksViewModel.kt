package hu.mzsb.booksbysubject.ui.books

import co.zsmb.rainbowcake.base.RainbowCakeViewModel

import javax.inject.Inject

class BooksViewModel @Inject constructor(
        private val booksPresenter: BooksPresenter
) : RainbowCakeViewModel<BooksViewState>(Initial) {


}
