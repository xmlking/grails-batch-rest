package org.sumo.apiapp

import org.springframework.batch.item.ItemProcessor

class DrugProcessor<T> implements ItemProcessor<RecordHolder<T>, Drug> {
	@Override
	public Drug process(RecordHolder<T> drugRecordHolder) throws Exception {
		log.debug "Processing drug[${drugRecordHolder.key}]"

		Drug existingDrug = Drug.findByNdc(drugRecordHolder.key)// Drug.get(drugRecordHolder.key)

		//If no change, ignore the drug
		//Store Batch Job ID in each drug record to track the completed record
		//Idempotent Behaver : Check if this record processed successfully in the previous attempt and skip if true.
		if(!existingDrug) {
            return copy(drugRecordHolder, new Drug())
		} else {
            existingDrug =  copy(drugRecordHolder, existingDrug)
            if(existingDrug.isDirty()) {
                return existingDrug
            } else {
                return null
            }
        }
	}

    private Drug copy(RecordHolder<T> drugRecordHolder, Drug drug){

        drug.with {
            ndc = drugRecordHolder.key
            drugRecordHolder.records.each {
                switch (it.readString('TYPE')) {
                    case "A":
                        recordTypeA = new RecordTypeA(it.properties) //use  Data Binding and property editors to convert types
                        break

                    case "E":
                        recordTypeE = new RecordTypeE(it.properties)
                        break

                    case "G":
                        recordTypeG = new RecordTypeG(it.properties)
                        break

                    case "J":
                        recordTypeJ = new RecordTypeJ(it.properties)
                        break

                    case "L":
                        recordTypeL = new RecordTypeL(it.properties)
                        break

                    case "M":
                        recordTypeM = new RecordTypeM(it.properties)
                        break

                    case "P":
                        addToRecordTypePs(new RecordTypeP(it.properties))
                        break

                    case "Q":
                        addToRecordTypeQs(new RecordTypeQ(it.properties))
                        break

                    case "R":
                        addToRecordTypeRs(new RecordTypeR(it.properties))
                        break

                    case "S":
                        addToRecordTypeSs(new RecordTypeS(it.properties))
                        break

                    case "T":
                        addToRecordTypeTs(new RecordTypeT(it.properties))
                        break

                    case "C":
                        addToRecordTypeCs(new RecordTypeC(it.properties))
                        break

                    default:
                        log.error "unknown record type ${it}"
                }
            }
        }
        return drug
    }
}
