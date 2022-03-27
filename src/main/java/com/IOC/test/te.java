package com.IOC.test;

import com.AOP.proxy.onepackage.JDKProxy;
import org.springframework.util.Assert;

public class te {



    public static void main(String[] args) throws Exception {
        Add add = new Add();
        add.count();
        Object proxy = JDKProxy.createProxy(add);
        //proxy.
        //proxy.after(add);
        Assert.isTrue(2 == add.c, "ERR");
    }

    public void te1() {
        //P
    }
}
