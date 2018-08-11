/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.resource.map.xml.parse;

import com.github.peterchenhdu.future.example.tankbattle.entity.Brick;
import com.github.peterchenhdu.future.example.tankbattle.entity.Iron;
import com.github.peterchenhdu.future.example.tankbattle.entity.Water;
import com.github.peterchenhdu.future.example.tankbattle.resource.map.Map;
import com.github.peterchenhdu.future.example.tankbattle.resource.map.xml.XmlMap;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Vector;

public class MapParser {
    public static XmlMap getMapFromXml(String name) {
        try {

            DigesterLoader loader = DigesterLoader.newLoader(new FromAnnotationsRuleModule() {
                @Override
                protected void configureRules() {
                    bindRulesFrom(XmlMap.class);
                }

            });

            Digester digester = loader.newDigester();
            return digester.parse(new FileInputStream(new File(System.getProperty("user" +
                    ".home") + File
                    .separator +
                    ".tankBattle" + File.separator + "custom" + File.separator + name + ".xml")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void generateXmlFromMap(Map map, String mapName) throws ParserConfigurationException, TransformerException,
            IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document document = db.newDocument();
        Element rootElement = document.createElement("map");
        Vector<Brick> brickList = map.getBricks();
        Element bricks = document.createElement("bricks");
        rootElement.appendChild(bricks);
        for (int i = 0; i < brickList.size(); i++) {
            Element brick = document.createElement("brick");
            Element x = document.createElement("x");
            x.setTextContent(brickList.get(i).getX().toString());
            Element y = document.createElement("y");
            y.setTextContent(brickList.get(i).getY().toString());
            brick.appendChild(x);
            brick.appendChild(y);
            bricks.appendChild(brick);
        }

        Vector<Iron> ironList = map.getIrons();
        Element irons = document.createElement("irons");
        rootElement.appendChild(irons);
        for (int i = 0; i < ironList.size(); i++) {
            Element iron = document.createElement("iron");
            Element x = document.createElement("x");
            x.setTextContent(ironList.get(i).getX().toString());
            Element y = document.createElement("y");
            y.setTextContent(ironList.get(i).getY().toString());
            iron.appendChild(x);
            iron.appendChild(y);
            irons.appendChild(iron);
        }

        Vector<Water> waterList = map.getWaters();
        Element waters = document.createElement("waters");
        rootElement.appendChild(waters);
        for (int i = 0; i < waterList.size(); i++) {
            Element water = document.createElement("water");
            Element x = document.createElement("x");
            x.setTextContent(waterList.get(i).getX().toString());
            Element y = document.createElement("y");
            y.setTextContent(waterList.get(i).getY().toString());
            water.appendChild(x);
            water.appendChild(y);
            waters.appendChild(water);
        }

        document.appendChild(rootElement);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source xmlSource = new DOMSource(document);
        OutputStream file = FileUtils.openOutputStream(new File(System.getProperty("user.home") + File.separator +
                ".tankBattle" + File.separator + "custom" + File.separator + mapName + ".xml"));
        Result outputTarget = new StreamResult(file);
        transformer.transform(xmlSource, outputTarget);
    }


}