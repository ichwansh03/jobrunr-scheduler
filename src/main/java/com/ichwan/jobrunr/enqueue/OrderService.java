package com.ichwan.jobrunr.enqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public void retrieveProduct(UUID productId) throws InterruptedException {
        //TO DO
        LOGGER.info("Get product to send send receipt", productId);
        Thread.sleep(2000);
    }

    public void sendReceipt(UUID receiptId) throws InterruptedException {
        //TO DO
        LOGGER.info("Send receipt product", receiptId);
        Thread.sleep(3000);
    }
}
