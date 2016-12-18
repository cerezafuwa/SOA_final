package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by hubeini on 2016/12/19.
 */
public class Memo {
    private int id;
    private Date date;
    private String text;
    private String user;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memo memo = (Memo) o;

        if (id != memo.id) return false;
        if (date != null ? !date.equals(memo.date) : memo.date != null) return false;
        if (text != null ? !text.equals(memo.text) : memo.text != null) return false;
        if (user != null ? !user.equals(memo.user) : memo.user != null) return false;
        if (time != null ? !time.equals(memo.time) : memo.time != null) return false;

        return true;
    }

    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
