/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.digester3.rulesbinder.module;

import org.apache.commons.digester3.binder.AbstractRulesModule;

import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Address;
import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Employee;

/**
 * @author chenpi
 * @version 2017年6月5日
 */
public class EmployeeModule extends AbstractRulesModule {

    @Override
    protected void configure() {
        forPattern("employee").createObject().ofType(Employee.class);
        forPattern("employee/firstName").setBeanProperty();
        forPattern("employee/lastName").setBeanProperty();

        forPattern("employee/address").createObject().ofType(Address.class).then().setNext("addAddress");
        forPattern("employee/address/type").setBeanProperty();
        forPattern("employee/address/city").setBeanProperty();
        forPattern("employee/address/state").setBeanProperty();
    }

}
