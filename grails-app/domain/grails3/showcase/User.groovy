package grails3.showcase


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class User {
    String login
    String name
    Integer age
}