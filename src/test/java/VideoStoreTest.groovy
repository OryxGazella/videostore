import spock.lang.Specification

class VideoStoreTest extends Specification {
    private RentalStatement statement
    private Movie newRelease1
    private Movie newRelease2
    private Movie childrens
    private Movie regular1
    private Movie regular2
    private Movie regular3

    def setup() {
        statement = new RentalStatement("Customer Name")
        newRelease1 = new NewReleaseMovie("New Release 1")
        newRelease2 = new NewReleaseMovie("New Release 2")
        childrens = new ChildrensMovie("Childrens")
        regular1 = new RegularMovie("Regular 1")
        regular2 = new RegularMovie("Regular 2")
        regular3 = new RegularMovie("Regular 3")
    }

    def 'New releases cost £3 / day and earn 2 frequent renter points'() {
        expect:
        statement.addRental(new Rental(newRelease1, 3))
        statement.makeRentalStatement()
        statement.amountOwed == 9.0 as double
        statement.frequentRenterPoints == 2 as int
    }

    def 'Two new releases rented for 3 days each cost 2 x 3 x £3 = £18 earning 4 points'() {
        expect:
        statement.addRental(new Rental(newRelease1, 3))
        statement.addRental(new Rental(newRelease2, 3))
        statement.makeRentalStatement()
        statement.amountOwed == 18.0 as double
        statement.frequentRenterPoints == 4 as int
    }

    def "A children's rental costs 50p a day, but only earns you a single point"() {
        expect:
        statement.addRental(new Rental(childrens, 3))
        statement.makeRentalStatement()
        statement.amountOwed == 1.5 as double
        statement.frequentRenterPoints == 1 as int
    }


    def 'Regular movies cost £2 for two days and £1 50p every day after that'() {
        expect:
        statement.addRental(new Rental(regular1, 1))
        statement.addRental(new Rental(regular2, 2))
        statement.addRental(new Rental(regular3, 3))
        statement.makeRentalStatement()
        statement.amountOwed == 7.5 as double
        statement.frequentRenterPoints == 3 as int
    }

    def 'Rental statements show the cost of every movie rental, the total owned and the frequent renter points'() {
        expect:
        statement.addRental(new Rental(regular1, 1))
        statement.addRental(new Rental(regular2, 2))
        statement.addRental(new Rental(regular3, 3))

        statement.makeRentalStatement() ==
            """\
            Rental Record for Customer Name
            \tRegular 1\t2.0
            \tRegular 2\t2.0
            \tRegular 3\t3.5
            You owed 7.5
            You earned 3 frequent renter points
            """.stripIndent()
    }
}