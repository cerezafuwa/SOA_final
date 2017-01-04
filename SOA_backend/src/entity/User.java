package entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by hubeini on 2016/12/25.
 */
@XmlRootElement
@XmlType
@Entity
public class User{
    private String username;
    private String password;
    private boolean gender;
    private Timestamp registertime;
    private String email;
    private Date birthday;

    public User(){
        registertime = Timestamp.valueOf(LocalDateTime.now());
    }

    public User(String username, String password, boolean gender, Date birthday, String email) {
        this();
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
    }

    /**
     * generate JSONObject for user info
     * @return user infomation (no password)
     */

    @Id
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @XmlTransient
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "registertime", nullable = false)
    public Timestamp getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Timestamp registertime) {
        this.registertime = registertime;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    @Basic
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (gender != user.gender) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (registertime != null ? !registertime.equals(user.registertime) : user.registertime != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender ? 1 : 0);
        result = 31 * result + (registertime != null ? registertime.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }


}
