class DSL {
    companion object {
        fun aStatement(statementSpec: StatementBuilder.() -> Unit): RentalStatement {
            val builder = StatementBuilder()
            builder.statementSpec()
            return builder.build()
        }
    }
}