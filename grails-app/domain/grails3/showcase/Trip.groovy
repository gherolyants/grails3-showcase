package grails3.showcase


class Trip {

    static belongsTo = [user: User]

    String destination
    Date start
    Date end
}