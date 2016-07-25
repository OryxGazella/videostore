class MovieRentalBuilder {
    private var days = 0
    private var title = ""

    fun days(days : Int) {
        this.days = days
    }

    fun title(title: String) {
        this.title = title
    }

    fun buildRegularMovie(): Rental {
        return Rental(RegularMovie(title), days)
    }

    fun buildNewRelease(): Rental {
        return Rental(NewReleaseMovie(title), days)
    }

    fun buildChildrensMovie(): Rental {
        return Rental(ChildrensMovie(title), days)
    }
}