package grails3.showcase


import grails.rest.*
import grails.converters.*
import grails3.showcase.Trip

class TripController {

    def index() {
        def date = Date.parse('yyyy-mm-dd', params.date)
//        def trips = Trip.findAllByDestinationLikeAndStartLessThanAndEndGreaterThan("%$params.destination%", date, date)
        def query = Trip.where {
            destination like "%$params.destination%"
            start <= date
            end >= date
            user.age == params.age as Integer
        }
        render(query.list() as JSON)
    }
}
