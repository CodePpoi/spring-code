package com.IOC.beans;


@MyBean(value = "userController")
public class UserController {

    @AutoInject
    private UserServiceImpl userService;

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
