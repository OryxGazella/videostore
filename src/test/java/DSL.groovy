class DSL {
    private DSL() {}

    static RentalStatement aStatement(@DelegatesTo(StatementBuilder) Closure closure) {
        def builder = new StatementBuilder()
        closure.delegate = builder
        closure.run()
        builder.build()
    }
}
