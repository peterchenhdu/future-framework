/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.digester3.rulesbinder.module;

import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Address;
import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Employee;
import org.apache.commons.digester3.binder.RulesBinder;
import org.apache.commons.digester3.binder.RulesModule;

/**
 * @author chenpi
 * @version 2017年6月5日
 */
public class EmployeeModule2
        implements RulesModule {

    public void configure(RulesBinder rulesBinder) {
        rulesBinder.forPattern("employee").createObject().ofType(Employee.class);
        rulesBinder.forPattern("employee/firstName").setBeanProperty();
        rulesBinder.forPattern("employee/lastName").setBeanProperty();

        rulesBinder.forPattern("employee/address")
                .createObject().ofType(Address.class)
                .then()
                .setNext("addAddress");
        rulesBinder.forPattern("employee/address/type").setBeanProperty();
        rulesBinder.forPattern("employee/address/city").setBeanProperty();
        rulesBinder.forPattern("employee/address/state").setBeanProperty();
    }

}
