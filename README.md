Visit my article: [Handling Asynchronous Process in Quarkus with JobRunr](https://medium.com/stackademic/asynchronous-processing-in-quarkus-with-jobrunr-58dd1604ba97)

### How does it all work?
* You can enqueue, schedule or schedule a recurring background Job the JobScheduler.
* The JobScheduler analyses and decomposes the lambda to a JSON object and saves it into the StorageProvider.
* JobRunr returns immediately to the caller so that it is not blocking
* One or more BackgroundJobServers poll the StorageProvider for new enqueued jobs and process them
* When a job has been processed, it updates the state in the StorageProvider and fetches the next job to perform

Most enterprise applications make use of an IoC framework like Spring or Guice - we off course support these IoC frameworks. The JobActivator is a Java 8 functional interface and has the responsability to lookup the correct class on which the background job method is defined.
```
public interface JobActivator {
    <T> T activateJob(Class<T> type);
}
```
Given a class, the JobActivator must return a instance of that class that is completely initialized

* Go to class `DatabaseSqlMigrationFileProvider`, enter key param in CLI arguments
* JobRunr’s `BackgroundJobServer` periodically checks all scheduled jobs and enqueues them when it is time to run them, allowing workers to execute them. By default, the poll interval is equal to 15 seconds, but you can change it by passing the relevant argument to the BackgroundJobServer constructor or by changing the properties for your preferred framework.
* This filter is applied globally to all methods and has 10 retry attempts by default. So, your methods will be retried in case of exception automatically, and you receive warning log messages in the dashboard on every failed attempt. If retry attempts exceeded their maximum, the job will stay in the FAILED state (with an error log message), and you will be able to retry it manually. This is also configurable by means of a property setting (`default-number-of-retries` and `retry-back-off-time-seed`) if you are using the Spring, Micronaut or Quarkus integration.
