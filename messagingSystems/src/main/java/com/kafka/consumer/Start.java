package com.kafka.consumer;

import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.Message;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import static java.lang.String.valueOf;

public class Start {
    public static void main(String args[]) throws Exception {
        new Start().run();
    }

    public void run() throws Exception {
        long startOffSet = 0;

        while (true) {
            ByteBufferMessageSet messageAndOffsets = new ConsumerWrapper().fetchMessages(startOffSet);

            if (messageAndOffsets.iterator().hasNext()) {
                for (MessageAndOffset messageAndOffset : messageAndOffsets) {
                    startOffSet = messageAndOffset.nextOffset();

                    Message message = messageAndOffset.message();
                    System.out.println(valueOf(messageAndOffset.offset()) + ": " + printMessage(message));
                }
            } else {
                Thread.sleep(1000);
                System.out.println("Trying to Fetch Message ....");
            }
        }
    }


    private String printMessage(Message message) throws UnsupportedEncodingException {
        ByteBuffer payload = message.payload();
        byte[] bytes = new byte[payload.limit()];
        payload.get(bytes);
        return new String(bytes, "UTF-8");
    }
}

