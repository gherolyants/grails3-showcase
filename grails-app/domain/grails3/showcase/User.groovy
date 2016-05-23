package grails3.showcase

class User {
    static hasMany = [trips: Trip]

    String login
    String password
    String name
    Integer age
}