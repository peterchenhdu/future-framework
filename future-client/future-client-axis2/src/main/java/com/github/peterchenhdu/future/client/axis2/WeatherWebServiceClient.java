/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.client.axis2;

import com.github.peterchenhdu.future.client.axis2.stub.WeatherWebServiceStub;

import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * WeatherWebServiceClient
 *
 * @author chenpi
 * @since 2018/7/26 21:22
 */
public class WeatherWebServiceClient {

    /**
     * @param args
     * @throws RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        //wsdl--->  http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl

        //Stub代码生成：
        //1、下载axis2-1.7.8-bin.zip: http://axis.apache.org/axis2/java/core/download.html
        //2、bin目录下执行：wsdl2java -uri http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl -p com.github.peterchenhdu.future.client.axis2.stub -s
        //3、生成目标代码WeatherWebServiceStub

        //客户端根据WeatherWebServiceStub类直接调用接口
        WeatherWebServiceStub stub = new WeatherWebServiceStub();
        WeatherWebServiceStub.GetWeatherbyCityName cityName = new WeatherWebServiceStub.GetWeatherbyCityName();
        cityName.setTheCityName("杭州");

        WeatherWebServiceStub.GetWeatherbyCityNameResponse response = stub.getWeatherbyCityName(cityName);
        WeatherWebServiceStub.ArrayOfString string = response.getGetWeatherbyCityNameResult();
        System.out.println(Arrays.asList(string.getString()));
    }

}