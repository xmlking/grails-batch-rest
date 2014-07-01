package org.sumo.apiapp

import org.springframework.batch.item.ItemWriter
import org.sumo.apiapp.Drug

class DrugWriter implements ItemWriter<Drug> {
	
		@Override
		void write(List<? extends Drug> drugs) throws Exception {
			drugs.each { drug ->
				log.debug "saving drug[${drug.ndc}] to database"

                if(drug.hasErrors()) {
                    log.error "Binding Errors ...${drug.errors}"
                }

				drug.save(failOnError:true)
			}
		}
}