package grails3.showcase

class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
        "/user"(resources: 'user', namespace: 'v1') {
            "/trip"(resources: "trip")
        }
        "/user"(version: 'v2', resources: 'user', namespace: 'v2') {
            "/trip"(resources: "trip")
        }

        "/"(view: '/index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}