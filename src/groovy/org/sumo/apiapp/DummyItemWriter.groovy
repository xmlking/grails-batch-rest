package org.sumo.apiapp

import java.util.List;

import org.springframework.batch.item.ItemWriter


class DummyItemWriter implements ItemWriter<Object> {
	
	@Override
	public void write(List<? extends Object> items) throws Exception {
		items.each { p ->
			println p
		}
   }
	
}