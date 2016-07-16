class StatementBuilder {
    private RentalStatement statement

    def customerName(String customerName) {
        statement = new RentalStatement(customerName)
    }

    def rentals(@DelegatesTo(RentalBuilder) Closure closure) {
        def builder = new RentalBuilder()
        closure.delegate = builder
        closure.run()
        def rentals = builder.build()
        rentals.each { statement.addRental(it)}
    }

    def build() {
        statement
    }
}