class MovieRentalBuilder {
    String name
    int amount

    def title(String name) {
        this.name = name
    }

    def days(int amount) {
        this.amount = amount
    }

    def newRelease() {
        new Rental(new NewReleaseMovie(name), amount)
    }

    def childrensMovie() {
        new Rental(new ChildrensMovie(name), amount)
    }

    Rental regularMovie() {
        new Rental(new RegularMovie(name), amount)
    }
}
