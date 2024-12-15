package com.ichwan.jobrunr.enqueue;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OrderTask {

    private OrderService orderService;

    //@Recurring(cron = "*/5 * * * *")
    @Job(name = "Requiring Product", retries = 3)
    public List<String> requiringTask() {

        List<String> products = null;

        for (int i = 0; i < 10; i++) {
            products.add("Product "+i);
        }

        return products;
    }

    @Job(name = "Enqueue Order Tasks")
    public void enqueueOrderTask(UUID taskId) {
        BackgroundJob.create(Stream.of(
                JobBuilder.aJob()
                        .withName("Retrieve Product")
                        .withAmountOfRetries(10)
                        .withDetails(() -> orderService.retrieveProduct(taskId)),

                JobBuilder.aJob()
                        .withName("Send Receipt")
                        .scheduleIn(Duration.ofMinutes(1L))
                        .withDetails(() -> orderService.sendReceipt(taskId))
        ));
    }
}
