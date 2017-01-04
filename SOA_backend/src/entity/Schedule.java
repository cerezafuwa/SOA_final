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
@Entity
@IdClass(SchedulePK.class)
public class Schedule {
    private String user;
    private Date date;
    private Timestamp time;
    private String description;

    public Schedule(){
        time=Timestamp.valueOf(LocalDateTime.now());
    }

    public Schedule(Date date, String description, String user){
        this();
        this.date = date;
        this.description = description;
        this.user = user;
    }

    @Id
    @Column(name = "user", nullable = false, length = 255)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    @Id
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (user != null ? !user.equals(schedule.user) : schedule.user != null) return false;
        if (date != null ? !date.equals(schedule.date) : schedule.date != null) return false;
        if (time != null ? !time.equals(schedule.time) : schedule.time != null) return false;
        if (description != null ? !description.equals(schedule.description) : schedule.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
