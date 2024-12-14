package com.ichwan.jobrunr.enqueue;

import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.jobrunr.scheduling.RecurringJobBuilder.aRecurringJob;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderTask orderTask;

    @GetMapping("/confirm-order")
    public String confirmOrder(){

        BackgroundJob.enqueue(() -> orderTask.enqueueOrderTask(UUID.randomUUID()));

        return "Done";
    }

    @GetMapping("/campaign")
    public String campaignNotif(){

        String cron = "*/1 * * * *";
        String campaign = BackgroundJob.scheduleRecurrently(cron, () -> System.out.println("Do another campaign"));
        System.out.println(campaign);

        return campaign;
    }

    @GetMapping("/notify")
    public String notif(){

        //*/2 8-16 * * MON-FRI
        BackgroundJob.createRecurrently(
                aRecurringJob()
                        .withCron("*/1 * * * *")
                        .withAmountOfRetries(4)
                        .withDetails(() -> orderTask.requiringTask()));

        return "Done";
    }
}