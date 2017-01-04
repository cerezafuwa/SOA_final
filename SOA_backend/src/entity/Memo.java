package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by hubeini on 2016/12/30.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@IdClass(MemoPK.class)
public class Memo {
    private Date date;
    private String text;
    private String user;
    private Timestamp time;

    public Memo(){
        time=Timestamp.valueOf(LocalDateTime.now());
    }

    public Memo(Date date, String text, String user){
        this();
        this.date = date;
        this.text = text;
        this.user = user;
    }

    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 255)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Id
    @Column(name = "user", nullable = false, length = 255)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Id
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memo memo = (Memo) o;

        if (date != null ? !date.equals(memo.date) : memo.date != null) return false;
        if (text != null ? !text.equals(memo.text) : memo.text != null) return false;
        if (user != null ? !user.equals(memo.user) : memo.user != null) return false;
        if (time != null ? !time.equals(memo.time) : memo.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
