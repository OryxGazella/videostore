import junit.framework.TestCase

class VideoStoreTest extends TestCase {
    private RentalStatement statement
    private Movie newRelease1
    private Movie newRelease2
    private Movie childrens
    private Movie regular1
    private Movie regular2
    private Movie regular3

    VideoStoreTest(String name) {
        super(name)
    }

    protected void setUp() {
        statement = new RentalStatement("Customer Name")
        newRelease1 = new NewReleaseMovie("New Release 1")
        newRelease2 = new NewReleaseMovie("New Release 2")
        childrens = new ChildrensMovie("Childrens")
        regular1 = new RegularMovie("Regular 1")
        regular2 = new RegularMovie("Regular 2")
        regular3 = new RegularMovie("Regular 3")
    }


    void testSingleNewReleaseStatement() {
        statement.addRental(new Rental(newRelease1, 3))
        statement.makeRentalStatement()
        assert statement.amountOwed == 9.0 as double
        assert statement.frequentRenterPoints == 2 as int
    }

    void testDualNewReleaseStatement() {
        statement.addRental(new Rental(newRelease1, 3))
        statement.addRental(new Rental(newRelease2, 3))
        statement.makeRentalStatement()
        assert statement.amountOwed == 18.0 as double
        assert statement.frequentRenterPoints == 4 as int
    }

    void testSingleChildrensStatement() {
        statement.addRental(new Rental(childrens, 3))
        statement.makeRentalStatement()
        assert statement.amountOwed == 1.5 as double
        assert statement.frequentRenterPoints == 1 as int
    }


    void testMultipleRegularStatement() {
        statement.addRental(new Rental(regular1, 1))
        statement.addRental(new Rental(regular2, 2))
        statement.addRental(new Rental(regular3, 3))
        statement.makeRentalStatement()
        assert statement.amountOwed == 7.5 as double
        assert statement.frequentRenterPoints == 3 as int
    }

    void testRentalStatementFormat() {
        statement.addRental(new Rental(regular1, 1))
        statement.addRental(new Rental(regular2, 2))
        statement.addRental(new Rental(regular3, 3))

        assert statement.makeRentalStatement() ==
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