package hu.mzsb.booksbysubject.ui.bookdetails

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class BookDetailsViewModel @Inject constructor(
    private val bookDetailsPresenter: BookDetailsPresenter
) : RainbowCakeViewModel<BookDetailsViewState>(Initial) {


}