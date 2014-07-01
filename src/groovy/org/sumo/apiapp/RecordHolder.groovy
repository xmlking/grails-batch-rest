package org.sumo.apiapp

import java.util.List;
import groovy.transform.ToString
/**
 * class for temporary state management while item is being
 * collected.
 *
 */
@ToString(includeNames=true)
public class RecordHolder<T> {
		String key
		List<T> records
		boolean exhausted = false;

		public RecordHolder() {
			this.key = null;
			this.records = new ArrayList<T>();
			this.exhausted = false;
		}
}