/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * File Name: Main.java
 * Description: 
 * Author: chenpi
 * Create Date: 2017年6月3日
 */
package com.github.peterchenhdu.future.example.digester3.simpletest;

import java.io.IOException;

import com.github.peterchenhdu.future.example.digester3.pojo.Bar;
import com.github.peterchenhdu.future.example.digester3.pojo.Foo;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

/**
 * @author chenpi
 * @version 2017年6月3日
 */

public class ExampleNSMain {

    public static void main(String[] args) {

        try {
            Digester digester = new Digester();

            digester.setValidating(false);
            digester.setNamespaceAware(true);
            digester.setRuleNamespaceURI("http://www.mycompany.com/MyNamespace");

            digester.addObjectCreate("foo", Foo.class);
            digester.addSetProperties("foo");
            digester.addObjectCreate("foo/bar", Bar.class);
            digester.addSetProperties("foo/bar");

            digester.addSetNext("foo/bar", "addBar", Bar.class.getName());

            Foo foo = digester
                    .parse(ExampleNSMain.class.getClassLoader().getResourceAsStream("example_ns.xml"));

            System.out.println(foo.getName());
            for (Bar bar : foo.getBarList()) {
                System.out.println(bar.getId() + "," + bar.getTitle());
            }

        } catch (IOException e) {

            e.printStackTrace();
        } catch (SAXException e) {

            e.printStackTrace();
        }

    }
}
