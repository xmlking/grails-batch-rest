package org.sumo.apiapp

import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext

class DropMongoDatabaseTasklet implements Tasklet {

    RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
//        println "Starting Mongo Database drop Job...."
//		def mongo = new com.gmongo.GMongo()
//		def db = mongo.getDB('Batch')
//		db.dropDatabase()
//        return RepeatStatus.FINISHED
    }
}
