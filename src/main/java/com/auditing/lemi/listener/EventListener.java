package com.auditing.lemi.listener;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.auditing.lemi.config.AwsProperties;
import com.auditing.lemi.entity.Versionable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventListener implements PostCommitUpdateEventListener, PostCommitInsertEventListener {

    private final AmazonSQS sqs;
    private final ObjectMapper objectMapper;
    private final AwsProperties properties;

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        uploadNewVersion(event.getEntity());
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        uploadNewVersion(event.getEntity());
    }
    private void uploadNewVersion(Object entity) {
        try {
            if (entity instanceof Versionable) {
                Versionable versionable = (Versionable) entity;
                SendMessageRequest send_msg_request = new SendMessageRequest()
                        .withQueueUrl(properties.getSqsQueue())
                        .withMessageBody(objectMapper.writeValueAsString(versionable));
                sqs.sendMessage(send_msg_request);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return true;
    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent postUpdateEvent) {
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent event) {

    }


}