package app.service.outputports;

import app.domain.User;

import java.util.List;

public interface UserRepository {

    public User save(User user);
    public User selectUserById(int id);
    public default User selectById(int id) {
        return selectUserById(id);
    }
    public List<User> selectAll();
    public User updateUser(User user);
    public void deleteById(int id);
    public int countUsers();

}
