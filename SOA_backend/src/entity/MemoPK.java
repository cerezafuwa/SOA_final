package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by hubeini on 2016/12/30.
 */
public class MemoPK implements Serializable {
    private String user;
    private Timestamp time;

    @Column(name = "user", nullable = false, length = 255)
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "time", nullable = false)
    @Id
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

        MemoPK memoPK = (MemoPK) o;

        if (user != null ? !user.equals(memoPK.user) : memoPK.user != null) return false;
        if (time != null ? !time.equals(memoPK.time) : memoPK.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
