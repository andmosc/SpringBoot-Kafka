package ru.andmosk.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.andmosk.util.WikimediaChangesHandler;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WikimediaChangesProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    @Value("${topic.name.wikimedia}")
    private String topicName;

    @Value("${url.wikimedia}")
    private String url;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage() throws InterruptedException {

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);
        EventSource.Builder  builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
