package org.sumo.apiapp

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.sumo.apiapp.Drug

class DropTempDatabaseTasklet implements Tasklet {

    RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        println "Starting Temp Database drop Job...."
        //Drug.executeUpdate('delete from Drug')
        Drug.where { ndc != null }.deleteAll()
        return RepeatStatus.FINISHED
    }
}
