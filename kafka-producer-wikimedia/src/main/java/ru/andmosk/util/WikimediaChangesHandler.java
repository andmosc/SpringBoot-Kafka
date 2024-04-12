package ru.andmosk.util;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@AllArgsConstructor
public class WikimediaChangesHandler implements EventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final String topic;

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        String data = messageEvent.getData();
        LOGGER.info("event data -> " + data);
        kafkaTemplate.send(topic, data);
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
