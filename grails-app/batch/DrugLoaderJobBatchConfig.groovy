import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper
import org.springframework.batch.item.file.transform.FixedLengthTokenizer
import org.springframework.batch.item.support.SingleItemPeekableItemReader
import org.sumo.apiapp.AggregateItemReader
import org.sumo.apiapp.DropTempDatabaseTasklet
import org.sumo.apiapp.DrugProcessor
import org.sumo.apiapp.DrugWriter

import java.text.SimpleDateFormat

beans {
	xmlns batch:"http://www.springframework.org/schema/batch"
	
	batch.job(id: 'dataIngestionJob') {
		batch.step(id:"cleanup", next:"drugDataLoaderStep") {
			batch.tasklet(ref:"dropTempDatabase")
		}
		batch.step(id: 'drugDataLoaderStep') {
			batch.tasklet{
			   batch.chunk(
				  reader : "aggregateItemReader",
				  processor : "itemProcessor",
				  writer : "itemWriter",
//				  'skip-limit' : 5,
				  'commit-interval' : 10) // 'commit-interval' : 1 for mongoDB as it doesn't support transactions across multiple documents
//			   batch.'skippable-exception-classes'{
//				  batch.include (ref:'validationException')
//			   }
			}
		 }
	}

//    dropTempDatabase(DropMongoDatabaseTasklet) { bean ->
//		bean.autowire = "byName"
//	}
    dropTempDatabase(DropTempDatabaseTasklet) { bean ->
        bean.autowire = "byName"
    }
	
	aggregateItemReader(AggregateItemReader) { bean ->
		bean.autowire = "byName"
		itemReader = ref('singleItemPeekableItemReader')
	}

	singleItemPeekableItemReader(SingleItemPeekableItemReader) { bean ->
		bean.autowire = "byName"
		delegate.delegate = ref('flatFileItemReader')
	}

	flatFileItemReader(FlatFileItemReader) { bean ->
		bean.autowire = "byName"
		resource = "classpath:data.file"
//		recordSeparatorPolicy = ref('drugEndOfRecordPolicy')
		lineMapper = ref("lineMapper") 
	}

	lineMapper(PatternMatchingCompositeLineMapper) { bean ->
		tokenizers = [
			'0000????????A*': ref('recordTypeATokenizer'),
			'0000????????C*': ref('recordTypeCTokenizer'),
			'0000????????E*': ref('recordTypeETokenizer'),
			'0000????????G*': ref('recordTypeGTokenizer'),
			'0000????????J*': ref('recordTypeJTokenizer'),
			'0000????????L*': ref('recordTypeLTokenizer'),
			'0000????????M*': ref('recordTypeMTokenizer'),
			'0000????????P*': ref('recordTypePTokenizer'),
			'0000????????Q*': ref('recordTypeQTokenizer'),
			'0000????????R*': ref('recordTypeRTokenizer'),
			'0000????????S*': ref('recordTypeSTokenizer'),
			'0000????????T*': ref('recordTypeTTokenizer'),
			'0000????????W*': ref('recordTypeWTokenizer'),
			'0000????????X*': ref('recordTypeXTokenizer'),
			'0000????????Y*': ref('recordTypeYTokenizer'),
			'0000????????Z*': ref('recordTypeZTokenizer')
			]
		fieldSetMappers = [
			'0000????????A*': ref('passThroughFieldSetMapper'),//do we need rest of them?
			'0000????????C*': ref('passThroughFieldSetMapper'),
			'0000????????E*': ref('passThroughFieldSetMapper'),
			'0000????????G*': ref('passThroughFieldSetMapper'),
			'0000????????J*': ref('passThroughFieldSetMapper'),
			'0000????????L*': ref('passThroughFieldSetMapper'),
			'0000????????M*': ref('passThroughFieldSetMapper'),
			'0000????????P*': ref('passThroughFieldSetMapper'),
			'0000????????Q*': ref('passThroughFieldSetMapper'),
			'0000????????R*': ref('passThroughFieldSetMapper'),
			'0000????????S*': ref('passThroughFieldSetMapper'),
			'0000????????T*': ref('passThroughFieldSetMapper'),
			'0000????????W*': ref('passThroughFieldSetMapper'),
			'0000????????X*': ref('passThroughFieldSetMapper'),
			'0000????????Y*': ref('passThroughFieldSetMapper'),
			'0000????????Z*': ref('passThroughFieldSetMapper')
			]
	}
 
	recordTypeATokenizer(FixedLengthTokenizer) { bean -> 
		names =   'NDC, TYPE, recordCode, transactionCode, A017, A024, A029, A030, A039, A044, A050, A051, A052, A054, A067, A068, A069, A070, A071, A072, A074, A075, A076, A077, A078, A079, A089, A090, A091, A092, A093, A094, A095, lastChangeDate'
		columns = '1-12, 13-13,14-16,17-17,18-24,25-29,30-30,31-39,40-44,45-50,51-51,52-52,53-54,55-67,68-68,69-69,70-70,71-71,72-72,73-74,75-75,76-76,77-77,78-78,79-79,80-89,90-90,91-91,92-92,93-93,94-94,95-95,96-96,96-100'
	}
	recordTypeCTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, C017, C028, C029, C042, C047, C058, C059, C072, C077, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-28,29-29,30-42,43-47,48-58,59-59,60-72,73-77,78-95,96-100'
	}
	recordTypeETokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, E017, E042, E077, E085, E089, E091, E092, E093, E094, E095, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-42,43-77,78-85,86-89,90-91,92-92,93-93,94-94,95-95,96-96,96-100'
	}
	recordTypeGTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, G017, G031, G036, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-31,32-36,37-96,96-100'
	}
	recordTypeJTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, J017, J047, J057, J082, J088, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-47,48-57,58-82,83-88,89-96,96-100'
	}
	recordTypeLTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, L017, L040, L044, L052, L054, L059, L060, L072, L073, L083, L089, L091, L093, L094, L095, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-40,41-44,45-52,53-54,55-59,60-60,61-72,73-73,74-83,84-89,90-91,92-93,94-94,95-95,96-96,96-100'
	}
	recordTypeMTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, M017, M030, M041, M043, M051, M059, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-30,31-41,42-43,44-51,52-59,60-96,96-100'
	}
	recordTypePTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, P017, P018, P027, P040, P051, P091, P092, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-18,19-27,28-40,41-51,52-91,92-92,93-96,96-100'
	}
	recordTypeQTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, Q017, Q018, Q028, Q041, Q049, Q059, Q072, Q080, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-18,19-28,29-41,42-49,50-59,60-72,73-80,81-96,96-100'
	}
	recordTypeRTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, R017, R018, R028, R041, R049, R059, R072, R080, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-18,19-28,29-41,42-49,50-59,60-72,73-80,81-96,96-100'
	}
	recordTypeSTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, S017, S018, S028, S041, S049, S059, S072, S080, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-18,19-28,29-41,42-49,50-59,60-72,73-80,81-96,96-100'
	}
	recordTypeTTokenizer(FixedLengthTokenizer) { bean ->
		names =   'NDC, TYPE, recordCode, transactionCode, T017, T018, T031, T039, T052, T060, T073, T081, lastChangeDate'
		columns = '1-12,13-13,14-16,17-17,18-18,19-31,32-39,40-52,53-60,61-73,74-81,82-96,96-100'
	}
	recordTypeWTokenizer(FixedLengthTokenizer) { bean ->
		names =   'W001, TYPE, W005, W008, W009, W010'
		columns = '1-4,  5-5,  6-8,  9-9,  10-10,11-97'
	}
	recordTypeXTokenizer(FixedLengthTokenizer) { bean ->
		names =   'X001'
		columns = '1-81'
	}
	recordTypeYTokenizer(FixedLengthTokenizer) { bean ->
		names =   'Y001, Y005, Y040, Y041, Y044, Y045, Y047, Y048, Y049'
		columns = '1-5,  6-40, 41-41,42-44,45-45,46-47,48-48,49-49,49-65'
	}
	recordTypeZTokenizer(FixedLengthTokenizer) { bean ->
		names =   'Z001, Z005, Z020, Z022, Z062, Z077'
		columns = '1-5,  6-20, 21-22,23-62,63-77,78-97' 
	}

    passThroughFieldSetMapper(PassThroughFieldSetMapper)

//	drugEndOfRecordPolicy(DrugEndOfRecordPolicy)

//  itemProcessor(PassThroughItemProcessor)
    itemProcessor(DrugProcessor)

//	itemWriter(DummyItemWriter)
	itemWriter(DrugWriter)
}
