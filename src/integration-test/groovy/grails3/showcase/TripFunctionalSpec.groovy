package grails3.showcase

import grails.buildtestdata.mixin.Build
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import grails.transaction.*
import grails.web.mime.MimeType
import org.apache.http.entity.ContentType

import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
@Rollback
@Build([User, Trip])
class TripFunctionalSpec extends GebSpec {

    @Shared
    def userId

    void setup() {
        userId = User.buildWithoutSave(login: 'lennon').save(flush: true).id
    }

    RestBuilder getRestBuilder() {
        new RestBuilder()
    }

    String getResourcePath() {
        "${baseUrl}/user/${userId}/trip"
    }

    Closure getValidJson() {{->
        Trip.buildWithoutSave(destination: "Yellow Submarine")
    }}

    Closure getInvalidJson() {{->
        new Trip(destination: "Beijing")
    }}

    void "Test the index action"() {
        given:
        def trip = Trip.build(destination: "Moscow")
        User.get(userId).addToTrips(trip).save(flush: true)

        when:"The index action is requested"
        def response = restBuilder.get(resourcePath)

        then:"The response is correct"
        response.status == OK.value()
        response.json == [:]
    }

    void "Test the save action correctly persists an instance"() {
        when:"The save action is executed with no content"
        def response = restBuilder.post(resourcePath)

        then:"The response is correct"
        response.status == UNPROCESSABLE_ENTITY.value()

        when:"The save action is executed with invalid data"
        response = restBuilder.post(resourcePath) {
            json invalidJson
        }
        then:"The response is correct"
        response.status == UNPROCESSABLE_ENTITY.value()

        when:"The save action is executed with valid data"
        response = restBuilder.post(resourcePath) {
            contentType MimeType.JSON.name
            accept MimeType.JSON.name
            json validJson
        }

        then:"The response is correct"
        response.status == CREATED.value()
        Trip.count() == 1
        response.json.id
    }

    void "Test the show action correctly renders an instance"() {
        given:
        def trip = Trip.build(destination: "Moscow")
        User.get(userId).addToTrips(trip).save(flush: true)

        when:"When the show action is called to retrieve a resource"
        def response = restBuilder.get(resourcePath) {
            accept MimeType.JSON.name
            header ACCEPT_VERSION
        }

        then:"The response is correct"
        response.status == OK.value()
        response.json*.id == [trip.id]
        response.json*.destination == ["Moscow"]
    }
}