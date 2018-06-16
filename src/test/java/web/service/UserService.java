package web.service;

import com.sarakeal.annotation.Service;
import web.bean.User;

@Service
public class UserService {

    public User getUser() {
        User user = new User(1,"ace", "123");
        return user;
    }
}
