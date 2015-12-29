package com.sakha.services.pojo;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by welcome on 29/12/15.
 */
public class Queues {

    @Value("${PPP_ES_DATAQUEUE}")
    public void setPppESDataQueue(String pppESDataQueue) {
        Queues.pppESDataQueue = pppESDataQueue;
    }

    static String pppESDataQueue;
    static String pppMongoDataQueue;


    public enum QueueName {
        PPP_ES(pppESDataQueue);

        public String getQueueName() {
            return queueName;
        }

        String queueName;
        QueueName(String name) {
            this.queueName = name;
        }
    }
}
