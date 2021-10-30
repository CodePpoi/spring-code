package com.IOC.beans;

@MyBean("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Long id) {
        User user = new User();
        if (id == 1) {
            user.setId(id);
            user.setName("zhangsan");
        } else if (id == 2) {
            user.setId(id);
            user.setName("lisi");
        }
        return user;
    }
}