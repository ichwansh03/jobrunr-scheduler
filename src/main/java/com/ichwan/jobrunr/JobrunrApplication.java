package com.ichwan.jobrunr;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.configuration.JobRunrConfiguration;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class JobrunrApplication {

	/** Prepare for biweekly presentation
	 * Create scenario for job fails
	 * Create and delete schedule reccurently
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(JobrunrApplication.class, args);
	}

	/**
	 * Default: h2-database
	 * @param dataSource
	 * @param jobActivator
	 * @return
	 */
	/*@Bean
	public JobRunrConfiguration.JobRunrConfigurationResult configureJobRunr(DataSource dataSource, JobActivator jobActivator) {
		return JobRunr
				.configure()
				.useJobActivator(jobActivator)
				.useStorageProvider(new PostgresStorageProvider(dataSource))
				.initialize();
	}*/
}
