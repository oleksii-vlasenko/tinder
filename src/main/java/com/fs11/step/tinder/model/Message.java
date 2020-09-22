package com.fs11.step.tinder.model;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Message {

    private final int id;
    private final int send;
    private final int receive;
    private final String text;
    private final long date;
    private int marker = 0;

    public Message(int id, int send, int receive, String text, long date) {
        this.id = id;
        this.send = send;
        this.receive = receive;
        this.text = text;
        this.date = date;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }

    public int getMarker() {
        return this.marker;
    }

    public int getId() {
        return id;
    }

    public int getSend() {
        return send;
    }

    public int getReceive() {
        return receive;
    }

    public String getText() {
        return text;
    }

    public long getDate() {
        return date;
    }

    public String getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, h:mm a");
        String dateString = simpleDateFormat.format(this.date);
        return dateString;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from=" + send +
                ", to=" + receive +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", marker=" + marker +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                send == message.send &&
                receive == message.receive &&
                date == message.date &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, send, receive, text, date);
    }
}
