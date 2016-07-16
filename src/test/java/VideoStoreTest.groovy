import spock.lang.Specification

import static DSL.aStatement

class VideoStoreTest extends Specification {

    def 'New releases cost £3 / day and earn 2 frequent renter points'() {
        given:
        def statement = aStatement {
            customerName 'Cumberland Bandersnatch'
            rentals {
                newRelease {
                    title 'Store Trek Free: The Search for Specs'
                    days 3
                }
            }
        }

        when:
        statement.makeRentalStatement()

        then:
        with(statement) {
            amountOwed == 9.0 as double
            frequentRenterPoints == 2 as int
        }
    }

    def 'Two new releases rented for 3 days each cost 2 x 3 x £3 = £18 earning 4 points'() {
        given:
        def statement = aStatement {
            customerName "Scala JoHudson"
            rentals {
                newRelease {
                    title "She"
                    days 3
                }
                newRelease {
                    title "The lady with bling in her ear"
                    days 3
                }
            }
        }

        when:
        statement.makeRentalStatement()

        then:
        with(statement) {
            amountOwed == 18.0 as double
            frequentRenterPoints == 4 as int
        }
    }

    def "A children's rental costs 50p a day, but only earns you a single point"() {
        given:
        def statement = aStatement {
            customerName "Donny Night-Louie"
            rentals {
                childrensMovie {
                    title "The Unbearable Lightness of Being - Sing Along Edition"
                    days 3
                }
            }
        }

        when:
        statement.makeRentalStatement()

        then:
        with(statement) {
            amountOwed == 1.5 as double
            frequentRenterPoints == 1 as int
        }
    }

    def 'Regular movies cost £2 for two days and £1 50p every day after that'() {
        given:
        def statement = aStatement {
            customerName "Kenny Smith"
            rentals {
                regularMovie {
                    title "Drachma"
                    days 1
                }
                regularMovie {
                    title "Clicks"
                    days 2
                }
                regularMovie {
                    title "Clicks 2"
                    days 3
                }
            }
        }

        when:
        statement.makeRentalStatement()

        then:
        with(statement) {
            amountOwed == 7.5 as double
            frequentRenterPoints == 3 as int
        }
    }

    def 'Rental statements show the cost of every movie rental, the total owed and the frequent renter points'() {
        given:
        def statement = aStatement {
            customerName "Valyssa Imes"
            rentals {
                regularMovie {
                    title "Centy Awful Season 1"
                    days 1
                }
                regularMovie {
                    title "Centy Awful Season 2"
                    days 2
                }
                regularMovie {
                    title "Centy Awful Season 3"
                    days 3
                }
            }
        }

        expect:
        statement.makeRentalStatement() ==
                '''\
                Rental Record for Valyssa Imes
                \tCenty Awful Season 1\t2.0
                \tCenty Awful Season 2\t2.0
                \tCenty Awful Season 3\t3.5
                You owed 7.5
                You earned 3 frequent renter points
                '''.stripIndent()
    }
}