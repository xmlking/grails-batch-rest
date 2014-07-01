package org.sumo.apiapp.render

import grails.converters.JSON
import grails.rest.render.RenderContext
import grails.rest.render.json.JsonCollectionRenderer
import org.codehaus.groovy.grails.web.mime.MimeType

import groovy.transform.CompileStatic
import static groovy.transform.TypeCheckingMode.SKIP

@CompileStatic
class SumoJsonCollectionRenderer extends JsonCollectionRenderer {

    SumoJsonCollectionRenderer(Class componentType) {
        super(componentType)
    }

    public SumoJsonCollectionRenderer(Class componentType, MimeType... mimeTypes) {
        super(componentType, mimeTypes)
    }

    @CompileStatic(SKIP)
    @Override
    protected void renderJson(object, RenderContext context) {
//        log.debug(object)
//        log.debug(object.size())
//        log.debug(object.getTotalCount())
        Map tObject = ['records': object]
        if (context.arguments?.metadata) {
            tObject['metadata'] = context.arguments.metadata
        }
        super.renderJson(tObject, context)
    }
}
