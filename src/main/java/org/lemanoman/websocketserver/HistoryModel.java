package org.lemanoman.websocketserver;


import org.lemanoman.websocketserver.config.Message;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String event;
    private String clientId;
    private String senderName;
    private String message;

    public HistoryModel(){}

    public HistoryModel(Message message){
        this.clientId = message.getClientId();
        this.senderName = message.getSenderName();
        this.event = message.getEvent();
        this.message = message.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

