import junit.framework.TestCase
import DSL.Companion.aStatement

class VideoStoreTest(name: String) : TestCase(name) {

    fun testSingleNewReleaseStatement() {
        given@
        val statement = aStatement {
            rentals {
                newRelease {
                    days(3)
                }
            }
        }

        when_@
        statement.makeRentalStatement()

        then@
        assertEquals(9.0, statement.amountOwed)
        assertEquals(2, statement.frequentRenterPoints)
    }

    fun testDualNewReleaseStatement() {
        given@
        val statement = aStatement {
            rentals {
                newRelease {
                    days(3)
                }
                newRelease {
                    days(3)
                }
            }
        }

        when_@
        statement.makeRentalStatement()

        then@
        assertEquals(18.0, statement.amountOwed)
        assertEquals(4, statement.frequentRenterPoints)
    }

    fun testSingleChildrensStatement() {
        given@
        val statement = aStatement {
            rentals {
                childrensMovie {
                    days(3)
                }
            }
        }

        when_@
        statement.makeRentalStatement()

        then@
        assertEquals(1.5, statement.amountOwed)
        assertEquals(1, statement.frequentRenterPoints)
    }

    fun testMultipleRegularStatement() {
        given@
        val statement = aStatement {
            rentals {
                regularMovie {
                    days(1)
                }
                regularMovie {
                    days(2)
                }
                regularMovie {
                    days(3)
                }
            }
        }

        when_@
        statement.makeRentalStatement()

        then@
        assertEquals(7.5, statement.amountOwed)
        assertEquals(3, statement.frequentRenterPoints)
    }

    fun testRentalStatementFormat() {
        given@
        val statement = aStatement {
            customerName("John Smith")
            rentals {
                regularMovie {
                    title("Regular 1")
                    days(1)
                }
                regularMovie {
                    title("Regular 2")
                    days(2)
                }
                regularMovie {
                    title("Regular 3")
                    days(3)
                }
            }
        }

        expect@
        assertEquals(
                "Rental Record for John Smith\n" +
                        "\tRegular 1\t2.0\n" +
                        "\tRegular 2\t2.0\n" +
                        "\tRegular 3\t3.5\n" +
                        "You owed 7.5" +
                        "\nYou earned 3 frequent renter points\n",
                statement.makeRentalStatement())
    }
}

