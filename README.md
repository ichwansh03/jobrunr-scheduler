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