/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.wechat.msg;

import com.github.peterchenhdu.future.util.RestTemplateUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class MessageUtil {

    /**
     * 内部类
     * 实例化当前对象
     *
     */
    private static class Instance{
        private static final MessageUtil mu = new MessageUtil();
        private static String ACCESS_TOKEN = null;
    }

    /**
     * 暴露在外部的方法
     * @return
     */
    public static MessageUtil getInstance() {
        return Instance.mu;
    }


    /**
     * 获取accessToken
     * @return
     * @throws Exception 
     */
    private String getAssessToken() throws Exception {
        if (Instance.ACCESS_TOKEN==null) {
            HttpConnection conn = new HttpConnection();
            StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
            sb.append("&appid="+PropertiesUtil.getInstance().getPropMap().get("wechat.appid"));
            sb.append("&secret="+PropertiesUtil.getInstance().getPropMap().get("wechat.appsecret"));
            String result = conn.get(sb.toString());
            System.out.println(result);
            JSONObject obj = new JSONObject(result);
            String accessToken = obj.getString("access_token");
            Instance.ACCESS_TOKEN = accessToken;
            System.out.println(accessToken);
        }

        return Instance.ACCESS_TOKEN;
    }

    /**
     * 群发文本消息
     * @param openId
     * @param text
     * @return
     */
    private boolean messTextMessage(String[] openId,String text) {
        try {
            String resp = "";//响应
            String reqUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+Instance.ACCESS_TOKEN;
            try {


                Map<String, Object> param = new HashMap<String, Object>();
                param.put("touser", openId);
                param.put("msgtype", "text");
                Map<String, Object> content =  new HashMap<String, Object>();
                content.put("content", text);
                param.put("text",content);


                resp = RestTemplateUtils.getRequest(reqUrl, param);
                System.out.println(resp);
            } catch (Exception e) {
                System.out.println("发送POST请求出现异常！" + e);
                e.printStackTrace();
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        MessageUtil.getInstance().getAssessToken();
        String[] openId = {"opMhEuI5sQcmT8T0A4Mn86XCrHas"};//
        MessageUtil.getInstance().messTextMessage(openId, "测试群发消息");
    }

}