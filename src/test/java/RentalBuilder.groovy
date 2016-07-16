class RentalBuilder {

    def rentals = [] as List<Rental>

    def newRelease(@DelegatesTo(MovieRentalBuilder) Closure closure) {
        def builder = new MovieRentalBuilder()
        closure.delegate = builder
        closure.run()
        rentals.add(builder.newRelease())
    }

    def childrensMovie(@DelegatesTo(MovieRentalBuilder) Closure closure) {
        def builder = new MovieRentalBuilder()
        closure.delegate = builder
        closure.run()
        rentals.add(builder.childrensMovie())
    }

    def regularMovie(@DelegatesTo(MovieRentalBuilder) Closure closure) {
        def builder = new MovieRentalBuilder()
        closure.delegate = builder
        closure.run()
        rentals.add(builder.regularMovie())
    }

    def build() {
        rentals
    }
}
