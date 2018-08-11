/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.resource.map.xml;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

import java.util.Vector;

/**
 * Class Description...
 *
 * @author chenpi
 * @since 2018/3/31 9:17
 */
@ObjectCreate(pattern = "map/waters")
public class XmlWaters {

    private Vector<XmlWater> waters = new Vector<>();


    public Vector<XmlWater> getWaters() {
        return waters;
    }

    public void setWaters(Vector<XmlWater> waters) {
        this.waters = waters;
    }

    @SetNext
    public void addWater(XmlWater water) {
        this.waters.add(water);
    }
}
