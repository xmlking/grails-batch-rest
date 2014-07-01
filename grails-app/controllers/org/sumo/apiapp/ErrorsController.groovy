package org.sumo.apiapp

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
class ErrorsController {

	def error401() 	{
		render status: UNAUTHORIZED 
	}
	
	def error403() {
		request.withFormat {
			form {
				flash.message = g.message(code: 'access.denied')
			}
			'*'{ render status: FORBIDDEN }
		}
	}	

	def error404 () {
		request.withFormat {
			form {
				flash.message = g.message(code: 'not.found')
			}
			'*'{ render status: NOT_FOUND }
		}
	}
	
	def error500() {
		request.withFormat {
			form {
				render view: '/error' 
			}
			'*'{ render status: INTERNAL_SERVER_ERROR  }
		}
		
	}
	
}
