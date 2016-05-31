package grails3.showcase.v1


import grails.rest.*
import grails3.showcase.User

class UserController extends RestfulController {
    static responseFormats = ['json', 'xml']
    static namespace = "v1"

    UserController() {
        super(User)
    }
}
