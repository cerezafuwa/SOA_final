package dao;

import entity.User;

/**
 * Created by hubeini on 2016/12/25.
 */
public class UserDAO extends BaseDAO<User> {

    private static UserDAO instance = new UserDAO();

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return instance;
    }

    public User findById(String username) {
        return super.findById(User.class, username);
    }

    public User findById(String username, String password) {
        User user = findById(username);
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

    public void delete(String username) {
        super.delete(User.class, username);
    }
}
