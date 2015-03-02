package com.kafka.consumer;

public class KafkaConstants {
    public static String SEED_BROKER = "127.0.0.1";
    public static int PORT = 9092;
    public static int PARTITION = 0;
    public static String TOPIC = "page_visits";

    public static String clientName() {
        return "Client_" + TOPIC + "_" + PARTITION;
    }
}
