package com.kafka.consumer;

import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.OffsetRequest;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;

import java.util.HashMap;
import java.util.Map;

import static com.kafka.consumer.KafkaConstants.*;
import static kafka.api.OffsetRequest.CurrentVersion;
import static kafka.api.OffsetRequest.EarliestTime;

public class ConsumerWrapper {

    public ByteBufferMessageSet fetchMessages(long readOffset) {
        SimpleConsumer consumer = new SimpleConsumer(SEED_BROKER, PORT, 100000, 64 * 1024, clientName());
        long currentOffSet = readOffset + getLastOffset(consumer);

        FetchResponse fetch = consumer.fetch(new FetchRequestBuilder()
                .clientId(clientName())
                .addFetch(TOPIC, PARTITION, currentOffSet, 100) // Note: this fetchSize of 100000 might need to be increased if large batches are written to Kafka
                .build());

        return fetch.messageSet(TOPIC, PARTITION);
    }

    private long getLastOffset(SimpleConsumer consumer) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(TOPIC, PARTITION);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(EarliestTime(), 1));
        OffsetRequest request = new OffsetRequest(requestInfo, CurrentVersion(), clientName());
        OffsetResponse response = consumer.getOffsetsBefore(request);

        if (response.hasError()) {
            System.out.println("Error fetching data Offset Data the Broker. Reason: " + response.errorCode(TOPIC, PARTITION));
            return 0;
        }
        long[] offsets = response.offsets(TOPIC, PARTITION);
        return offsets[0];
    }
}
