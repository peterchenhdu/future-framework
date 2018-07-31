/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author chenpi
 * @since 2018/7/30 23:06
 */
public class JwtUtils {

    private JwtUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM;
    private static final Key SIGNING_KEY;

    static {
        SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
        String secret = "26c2ec27c5b4ef4a8e5b36c12445180f438174f46061f7ee0d6dedf69a749f3e98db2a52da49d3273af3579665e915bd";
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(CodecUtils.decryption(secret));
        SIGNING_KEY = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
    }


    public static void main(String[] args) {
        System.out.println(getToken("ss", 2));
        System.out.println(checkToken(getToken("ss", 2)));
    }

    /**
     * 获取Token
     *
     * @param uid 用户ID
     * @param exp 失效时间，单位分钟
     * @return String
     */
    public static String getToken(String uid, int exp) {
        Date endTime = DateUtils.addMinutes(new Date(), exp);
        return Jwts.builder()
                .setSubject(uid)
                .setExpiration(endTime)
                .setIssuedAt(new Date())
                .setIssuer("chen pi")
                .claim("role", "admin")
                .signWith(SIGNATURE_ALGORITHM, SIGNING_KEY).compact();
    }
//    iss: jwt签发者
//    sub: jwt所面向的用户
//    aud: 接收jwt的一方
//    exp: jwt的过期时间，这个过期时间必须要大于签发时间
//    nbf: 定义在什么时间之前，该jwt都是不可用的.
//    iat: jwt的签发时间
//    jti: jwt的唯一身份标识


    /**
     * 检查Token是否合法
     *
     * @param token token
     * @return JWTResult
     */
    public static JWTResult checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
            String sub = claims.get("sub", String.class);
            logger.debug("token.sub : " + sub);
            return new JWTResult(true, sub, "合法请求", 200);
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return new JWTResult(false, null, "token已过期", 402);
        } catch (io.jsonwebtoken.SignatureException e) {
            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
            return new JWTResult(false, null, "非法请求", 403);
        } catch (Exception e) {
            return new JWTResult(false, null, "异常请求", 403);
        }
    }

    public static class JWTResult {
        private boolean status;
        private String uid;
        private String msg;
        private int code;

        public JWTResult() {
            super();
        }

        public JWTResult(boolean status, String uid, String msg, int code) {
            super();
            this.status = status;
            this.uid = uid;
            this.msg = msg;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isSuccess() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "JWTResult [status=" + status + ", uid=" + uid + ", msg=" + msg + ", code=" + code
                    + "]";
        }
    }
}
