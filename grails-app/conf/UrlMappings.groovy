import org.springframework.security.access.AccessDeniedException
class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
                controller(validator: { !['springBatchJob', 'springBatchJobExecution', 'springBatchJobInstance', 'springBatchStepExecution'].contains(it) })
            }
        }
        "/drugs"(resources:'drug')
        "/drugs/test403"(controller:"drug",action:"test403")
        "/drugs/search"(controller:"drug",action:"search")

        '/providers'(view: 'provider')
        '/api/providers'(resources: 'provider')

        "/api/type/$action?"(controller:'type')

        "/"(view:"/index")

        "401"(controller: "errors", action: "error401")
        "403"(controller: "errors", action: "error403")
        "404"(controller: 'errors', action: 'error404')
        "500"(controller: "errors", action: "error500")
        "500"(controller: "errors", action: "error403", exception: AccessDeniedException)
    }
}
