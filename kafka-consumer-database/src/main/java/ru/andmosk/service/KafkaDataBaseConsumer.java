package ru.andmosk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.andmosk.entity.WikimediaEntity;
import ru.andmosk.repository.WikimediaRepository;

@Service
public class KafkaDataBaseConsumer {

    private static final String TOPIC_NAME = "wikimedia_recentchange";
    private static final String GROUP_ID = "myGroup";
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDataBaseConsumer.class);

    private WikimediaRepository wikimediaRepository;

    @KafkaListener(groupId = GROUP_ID, topics = TOPIC_NAME)
    public void consume(String message) {
        LOGGER.info("message received -> " + message);
        WikimediaEntity wikimediaEntity = new WikimediaEntity();
        wikimediaEntity.setWikiEventData(message);

        wikimediaRepository.save(wikimediaEntity);
    }
}
