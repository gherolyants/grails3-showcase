package grails3.showcase

import grails.transaction.Transactional

@Transactional
class TripService {

    List<Trip> findTrips(String destination, Date date, Integer age) {
        def query = Trip.where {
            destination like "%$destination%"
            start <= date
            end >= date
            user.age == age
        }
        query.list()
    }
}
