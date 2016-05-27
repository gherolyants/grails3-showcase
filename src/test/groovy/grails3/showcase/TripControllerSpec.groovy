package grails3.showcase

import grails.buildtestdata.mixin.Build
import grails.converters.JSON
import grails.test.mixin.*
import spock.lang.*
import static org.springframework.http.HttpStatus.*

@TestFor(TripController)
@Mock([Trip, User])
@Build([Trip, User])
class TripControllerSpec extends Specification {

    def jimmyPage, johnLennon

    void setup() {
        jimmyPage = User.buildWithoutSave(login: 'Jimmy page')
                .addToTrips(Trip.buildWithoutSave(destination: 'Los Angeles'))
                .save(flush: true)
        johnLennon = User.buildWithoutSave(login: 'John Lennon')
                .addToTrips(Trip.buildWithoutSave(destination: 'USSR'))
                .addToTrips(Trip.buildWithoutSave(destination: 'Woodstock'))
                .save(flush: true)
    }

    void "index should return list of user trips"() {
        given:
        params.userId = johnLennon.id

        when: "The index action is executed"
        controller.index()

        then: "The response is correct"
        response.json*.destination == ['USSR', 'Woodstock']
    }

    void "should throw error when incorrect instance being saved"() {
        given: "there's a user"
        def user = User.build(login: "hendrix")

        when: "save action is executed with an invalid instance"
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'POST'
        params.userId = user.id
        request.json = new Trip(destination: "Manhattan",
                start: Date.parse('YYYY-MM-DD', '2016-01-02')) as JSON
        controller.save()

        then: "error is returned"
        response.status == UNPROCESSABLE_ENTITY.value()
        response.json.errors
    }

    void "should save trip to db and bind to the user"() {
        given: "there's a user"
        def user = User.build(login: "hendrix")

        when: "the save action is executed with a valid instance"
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'POST'
        params.userId = user.id
        request.json = new Trip(destination: "Manhattan",
                start: Date.parse('YYYY-MM-DD', '2016-01-02'),
                end: Date.parse('YYYY-MM-DD', '2016-02-02')) as JSON
        controller.save()

        then: "entity is created in the database"
        response.status == CREATED.value()
        Trip.countByDestination("Manhattan") == 1
    }

    void "should return 404 error for empty trip id"() {
        when: "no trip id is specified"
        params.userId = johnLennon.id
        controller.show()

        then: "A 404 error is returned"
        response.status == 404
    }

    void "should return valid json when correct parameters provided"() {
        when: "a correct is passed to the show action"
        params.userId = johnLennon.id
        params.id = johnLennon.trips[0].id
        controller.show()

        then: "valid json is returned"
        response.status == OK.value()
        response.json.destination == "USSR"
    }
}