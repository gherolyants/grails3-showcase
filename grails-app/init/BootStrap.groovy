import grails3.showcase.User

class BootStrap {

    def init = { servletContext ->
        new User(login: "theGood", name: "The Good", age: 31).save()
        new User(login: "theBad", name: "The Bad", age: 32).save()
        new User(login: "theUgly", name: "The Ugly", age: 33).save()
    }

    def destroy = {
    }
}
