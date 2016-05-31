package grails3.showcase


import grails.rest.*
import grails.converters.*
import grails3.showcase.Trip

class TripController {

    def index() {
        def date = Date.parse('yyyy-mm-dd', params.date)
        render(Trip.findAllByDestinationLikeAndStartLessThanAndEndGreaterThan("%$params.destination%", date, date) as JSON)
    }
}
