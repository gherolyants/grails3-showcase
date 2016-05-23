package grails3.showcase


import grails.rest.*
import grails.converters.*

class TripController extends RestfulController {
    static responseFormats = ['json', 'xml']
    TripController() {
        super(Trip)
    }
}
