package com.xlcxx.config.auth.utils;

import com.xlcxx.plodes.system.domian.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String SIGIN_SALUT = "xlxcc";

    private static final long EXPIRE_TIME = 3600 * 1000 * 4;

    /**
     * 用户登录成功后生成Jwt
     */
    public static String createJWT(User user) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap();
        claims.put("username", user.getUsername());
        //生成签发人
        String subject = user.getUsername();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, SIGIN_SALUT);
        long expMillis = nowMillis + EXPIRE_TIME;
        Date exp = new Date(expMillis);
        //设置过期时间
        builder.setExpiration(exp);
        return builder.compact();
    }
    /**
     * Token的解密
     */
    public static Claims parseJWT(String token) {
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(SIGIN_SALUT)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 校验token  主要校验时间
     */
    public static Boolean isVerify(String token) {
        try {
//得到DefaultJwtParser
            Claims claims = Jwts.parser()
                    //设置签名的秘钥
                    .setSigningKey(SIGIN_SALUT)
                    //设置需要解析的jwt
                    .parseClaimsJws(token).getBody();
            Date date = claims.getExpiration();
            Date niw = new Date();
            if (date.compareTo(niw) < 0) {
                return false;
            }
            return true;
        }catch (Exception e){
            logger.error("验证token得时效性:"+e.getMessage());
            return false;
        }

    }
}