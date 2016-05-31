package grails3.showcase


import grails.rest.*
import grails.converters.*

class UserTripController extends RestfulController {
    static responseFormats = ['json', 'xml']

    UserTripController() {
        super(Trip)
    }

    @Override
    protected Object queryForResource(Serializable id) {
        User.get(params.userId).trips.find { it.id == id }
    }

    @Override
    protected List listAllResources(Map params) {
        User.get(params.userId).trips as List
    }

    @Override
    protected Object createResource() {
        def trip = super.createResource() as Trip
        User.get(params.userId).addToTrips(trip)
        trip
    }
}
