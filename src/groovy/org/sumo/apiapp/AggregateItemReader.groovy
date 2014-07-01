package org.sumo.apiapp

import groovy.util.logging.Slf4j

import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream
import org.springframework.batch.item.ItemStreamException
import org.springframework.batch.item.support.SingleItemPeekableItemReader

@Slf4j
class AggregateItemReader<T> implements ItemReader<RecordHolder<T>>, ItemStream {
	
	private SingleItemPeekableItemReader<T> itemReader;
	
	@Override
	public RecordHolder<T> read() throws Exception {

		RecordHolder<T> holder = new RecordHolder<T>();
	
		synchronized (this) {
			while (process(getNextRecord(), holder)) {
				continue;
			}

			if (!holder.exhausted) {
				return holder;
			}
			else {
				//When you hit the end of the file close the reader.
				//close();
				return null;
			}
		}
	}
	
	private boolean process(T currentRecord, RecordHolder holder) {
		
		T nextRecord = peekNextRecord();

		// finish processing if we hit the end of file
		if (currentRecord == null) {
			log.info("Exhausted AggregateItemReader ( END OF FILE)");
			holder.exhausted = true;
			return false;
		}
		//TODO do it once
		holder.key = currentRecord.readString('NDC')

		if (nextRecord != null && holder.key == nextRecord.readString('NDC')) {
			holder.records.add(currentRecord);
			currentRecord = null;
			return true;
		}

		else {
			holder.records.add(currentRecord);
			return false;
		}
	}
	
	private T getNextRecord () {
		T record=null;

		try {
			record=itemReader.read();
		} catch (Exception e) {
			log.error("error in getNextRecord", e)
		}
		return record;
	}
	
	private T peekNextRecord() {
		T record=null;

		try {
			record=itemReader.peek();
		} catch (Exception e) {
			log.error("error in peekNextRecord", e)
		}
		return record;
	}
		
	public void setItemReader(SingleItemPeekableItemReader<T> itemReader) {
			this.itemReader = itemReader;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		this.itemReader.open(executionContext);
	}
	
	@Override
	public void close() throws ItemStreamException {
		this.itemReader.close();
	}
	
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		this.itemReader.update(executionContext);
	}

}