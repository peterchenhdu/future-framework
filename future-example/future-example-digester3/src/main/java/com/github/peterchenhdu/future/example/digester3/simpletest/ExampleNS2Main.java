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

import com.github.peterchenhdu.future.example.digester3.pojo.Bar;
import com.github.peterchenhdu.future.example.digester3.pojo.Foo;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * @author chenpi
 * @version 2017年6月3日
 */

public class ExampleNS2Main {

    public static void main(String[] args) {

        try {
            Digester digester = new Digester();

            digester.setValidating(false);
            digester.setNamespaceAware(false);
            //digester.setRuleNamespaceURI("http://www.mycompany.com/MyNamespace");

            digester.addObjectCreate("m:foo", Foo.class);
            digester.addSetProperties("m:foo");
            digester.addObjectCreate("m:foo/m:bar", Bar.class);
            digester.addSetProperties("m:foo/m:bar");
            digester.addSetNext("m:foo/m:bar", "addBar", Bar.class.getName());

            digester.addObjectCreate("m:foo/y:bar", Bar.class);
            digester.addSetProperties("m:foo/y:bar");
            digester.addSetNext("m:foo/y:bar", "addBar", Bar.class.getName());

            Foo foo = digester
                    .parse(ExampleNS2Main.class.getClassLoader().getResourceAsStream("example_ns.xml"));

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
