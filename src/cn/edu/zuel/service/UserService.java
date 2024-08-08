package cn.edu.zuel.service;

import cn.edu.zuel.dao.UserDao;
import cn.edu.zuel.entity.User;

public class UserService {
    private UserDao userDao=new UserDao();
    public User login(User user){
        return userDao.login(user);
    }
    public int register(User user){return userDao.register(user);}
    public User findByUsername(String username){return userDao.findByUsername(username);}
}
