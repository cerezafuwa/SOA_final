package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by hubeini on 2016/12/19.
 */
public class Schedule {
    private int id;
    private String user;
    private Date date;
    private Timestamp time;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (id != schedule.id) return false;
        if (user != null ? !user.equals(schedule.user) : schedule.user != null) return false;
        if (date != null ? !date.equals(schedule.date) : schedule.date != null) return false;
        if (time != null ? !time.equals(schedule.time) : schedule.time != null) return false;
        if (description != null ? !description.equals(schedule.description) : schedule.description != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
