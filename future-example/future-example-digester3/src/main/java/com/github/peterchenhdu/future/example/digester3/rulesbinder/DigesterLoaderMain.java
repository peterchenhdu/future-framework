
/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.digester3.rulesbinder;

import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.xml.sax.SAXException;

import com.github.peterchenhdu.future.example.digester3.rulesbinder.module.EmployeeModule;
import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Address;
import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Employee;
import com.github.peterchenhdu.future.example.digester3.simpletest.ExampleMain;

/**
 * @author chenpi
 * @version 2017年6月5日
 */
public class DigesterLoaderMain {

    private static DigesterLoader dl = DigesterLoader.newLoader(new EmployeeModule())
            .setNamespaceAware(false);

    public static void main(String[] args) {
        try {

            Digester digester = dl.newDigester();
            Employee employee = digester.parse(ExampleMain.class.getClassLoader().getResourceAsStream("employee.xml"));

            System.out.print(employee.getFirstName() + " ");
            System.out.print(employee.getLastName() + ", ");
            for (Address a : employee.getAddressList()) {
                System.out.print(a.getType() + ", ");
                System.out.print(a.getCity() + ", ");
                System.out.println(a.getState());
            }

        } catch (IOException e) {

            e.printStackTrace();
        } catch (SAXException e) {

            e.printStackTrace();
        }
    }
}
