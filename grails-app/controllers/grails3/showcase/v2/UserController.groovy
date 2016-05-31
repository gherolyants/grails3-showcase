package grails3.showcase.v2

import grails.rest.RestfulController
import grails3.showcase.User

class UserController extends RestfulController {
    static responseFormats = ['json', 'xml']
    static namespace = 'v2'

    UserController() {
        super(User)
    }

    @Override
    Object index() {
        render view: '/user/index_v2', model: [userList: User.findAll()]
    }
}
