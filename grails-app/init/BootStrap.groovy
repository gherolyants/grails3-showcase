import grails3.showcase.User

class BootStrap {

    def init = { servletContext ->
        new User(login: "theGood", name: "The Good", age: 31, password: '1').save()
        new User(login: "theBad", name: "The Bad", age: 32, password: '2').save()
        new User(login: "theUgly", name: "The Ugly", age: 33, password: '3').save()
    }

    def destroy = {
    }
}
