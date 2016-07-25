import java.util.*

class RentalsBuilder {
    private var rentals: MutableList<Rental> = ArrayList()

    fun regularMovie(movieRentalSpec: MovieRentalBuilder.() -> Unit) {
        val builder = MovieRentalBuilder()
        builder.movieRentalSpec()
        rentals.add(builder.buildRegularMovie())
    }

    fun newRelease(movieRentalSpec: MovieRentalBuilder.() -> Unit) {
        val builder = MovieRentalBuilder()
        builder.movieRentalSpec()
        rentals.add(builder.buildNewRelease())
    }

    fun childrensMovie(movieRentalSpec: MovieRentalBuilder.() -> Unit) {
        val builder = MovieRentalBuilder()
        builder.movieRentalSpec()
        rentals.add(builder.buildChildrensMovie())
    }

    fun build(): List<Rental> = rentals
}