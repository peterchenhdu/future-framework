/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.tool.mb.crawler4j.model;

/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年5月24日
 * @see
 * @since infosys V1.0.0
 */

public class User {

    private long id;
    private String name;
    private String address;


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}
