import org.springframework.batch.core.JobParametersBuilder;



class HourlyBatchRunnerJob {
	static triggers = {
	  cron name:"cronTrigger", startDelay:0, cronExpression :'0 0 0/1 * * ? *'
	}
		 
		 def jobLauncher
		 def testJob
		 
	def execute() {
				 def jobParams = new JobParametersBuilder().addDate("run.date", new Date()).toJobParameters()
				 jobLauncher.run(testJob, jobParams)
	}
}