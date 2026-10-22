package app.repository;

import app.domain.User;
import app.service.outputports.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplCollection implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User selectUserById(int id) {
        for (User user : users) {
            if (user.getId() != null && user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> selectAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User updateUser(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }

        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);
            if (currentUser.getId() != null && currentUser.getId().equals(user.getId())) {
                users.set(i, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        users.removeIf(user -> user.getId() != null && user.getId().equals(id));
    }

    @Override
    public int countUsers() {
        return users.size();
    }
}
