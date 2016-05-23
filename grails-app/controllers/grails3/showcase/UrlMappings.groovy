package grails3.showcase

class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
        "/user"(resources: "user") {
            "/trip"(resources: "trip")
        }

        "/"(view: '/index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
