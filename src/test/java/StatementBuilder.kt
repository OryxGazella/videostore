class StatementBuilder {

    private var customerName : String? = "Customer"
    private var rentals : List<Rental> = emptyList()

    fun build(): RentalStatement {
        val statement = RentalStatement(customerName)
        rentals.forEach {
            statement.addRental(it)
        }
        return statement
    }

    fun customerName(customerName: String) {
        this.customerName = customerName
    }

    fun rentals(rentalsSpec: RentalsBuilder.() -> Unit) {
        val builder = RentalsBuilder()
        builder.rentalsSpec()
        this.rentals = builder.build()
    }
}