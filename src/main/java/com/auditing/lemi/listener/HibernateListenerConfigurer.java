package com.auditing.lemi.listener;

import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
@RequiredArgsConstructor
public class HibernateListenerConfigurer {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private final EventListener eventListener;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_COMMIT_UPDATE).appendListener(eventListener);
        registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT).appendListener(eventListener);

    }
}