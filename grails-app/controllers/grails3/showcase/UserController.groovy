package grails3.showcase


import grails.rest.*
import grails.converters.*

class UserController extends RestfulController {
    static responseFormats = ['json', 'xml']
    UserController() {
        super(User)
    }
}
