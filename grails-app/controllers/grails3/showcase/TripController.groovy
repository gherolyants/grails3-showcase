package grails3.showcase


import grails.rest.*
import grails.converters.*
import grails3.showcase.Trip
import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

class TripController {

    TripService tripService

    def index() {
        if (!params.date || !params.destination || !params.age) {
            render status: UNPROCESSABLE_ENTITY, message: 'All date, destination and age should be provided'
            return
        }

        def date = Date.parse('yyyy-mm-dd', params.date)
        def destination = params.destination as String
        def age = params.age as Integer

//        def trips = Trip.findAllByDestinationLikeAndStartLessThanAndEndGreaterThan("%$params.destination%", date, date)
        def result = tripService.findTrips(destination, date, age)
        render(result as JSON)
    }
}
