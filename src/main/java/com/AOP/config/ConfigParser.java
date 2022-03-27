package com.AOP.config;

import com.AOP.bean.Advisor;

import java.util.List;


public interface ConfigParser {
    
    public List<Advisor> parse();

}