package org.sumo.apiapp

import org.springframework.batch.item.file.separator.RecordSeparatorPolicy

class DrugEndOfRecordPolicy implements RecordSeparatorPolicy {
	
	// activeNDC
	String activeNDC;

	public boolean isEndOfRecord(String line) {
		println "xxxxxx----${activeNDC}-----${line[-100..-89]}"
		if (!activeNDC) {
			activeNDC = line[-100..-89] 
			return false
		} else {
			return activeNDC != line[-100..-89]
		}
	}

	public String postProcess(String record) {
		return record;
	}

	public String preProcess(String record) {
		return record;
	}
}
