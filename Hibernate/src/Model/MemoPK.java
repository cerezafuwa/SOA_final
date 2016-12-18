package Model;

import java.io.Serializable;

/**
 * Created by hubeini on 2016/12/19.
 */
public class MemoPK implements Serializable {
    private int id;
    private String user;

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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoPK memoPK = (MemoPK) o;

        if (id != memoPK.id) return false;
        if (user != null ? !user.equals(memoPK.user) : memoPK.user != null) return false;

        return true;
    }

    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
