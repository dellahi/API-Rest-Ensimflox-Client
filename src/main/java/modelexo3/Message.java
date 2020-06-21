package modelexo3;

import java.util.Date;

public class Message {
   public String content;
    private Date dateTime;
    private String sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", dateTime=" + dateTime +
                ", sender='" + sender + '\'' +
                '}';
    }
}
