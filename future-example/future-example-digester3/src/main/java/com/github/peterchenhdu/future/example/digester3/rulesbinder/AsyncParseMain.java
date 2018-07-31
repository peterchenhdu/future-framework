
/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.digester3.rulesbinder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.github.peterchenhdu.future.example.digester3.rulesbinder.module.EmployeeModule;
import com.github.peterchenhdu.future.example.digester3.simpletest.ExampleMain;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Address;
import com.github.peterchenhdu.future.example.digester3.rulesbinder.pojo.Employee;

/**
 * @author chenpi
 * @version 2017年6月5日
 */
public class AsyncParseMain {
    private static DigesterLoader dl = DigesterLoader.newLoader(new EmployeeModule())
            .setNamespaceAware(false).setExecutorService(Executors.newSingleThreadExecutor());

    public static void main(String[] args) {
        try {

            Digester digester = dl.newDigester();
            Future<Employee> future = digester.asyncParse(ExampleMain.class.getClassLoader().getResourceAsStream
                    ("employee.xml"));

            Employee employee = future.get();

            System.out.print(employee.getFirstName() + " ");
            System.out.print(employee.getLastName() + ", ");
            for (Address a : employee.getAddressList()) {
                System.out.print(a.getType() + ", ");
                System.out.print(a.getCity() + ", ");
                System.out.println(a.getState());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
