package org.sumo.apiapp

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus


class TestTasklet implements Tasklet {

        @Override
   public RepeatStatus execute(StepContribution contrib, ChunkContext context) throws Exception {
                println "Task executed"
                println context.getStepContext().getJobParameters()                                                
           return RepeatStatus.FINISHED
   }
        
}