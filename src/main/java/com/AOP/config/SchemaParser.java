package com.AOP.config;


import com.AOP.bean.Advisor;

import java.util.ArrayList;
import java.util.List;

public class SchemaParser implements ConfigParser {

    @Override
    public List<Advisor> parse() {
        return new ArrayList<Advisor>();
    }
    
}