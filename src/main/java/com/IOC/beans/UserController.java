package com.IOC.beans;


import java.io.Serializable;

@MyBean(value = "userController")
public class UserController{

    @AutoInject
    private UserService userService;

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }


    public UserController() {
        System.out.println("beans.BeanA created");
    }

    public void print() {
        System.out.println("called by someone");
    }


}
