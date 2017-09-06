package com.gdyunst.ystadmin.application.security;

import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 
 * 功能描述: Token生成类
 * @Author:denghsuai
 * @Date:2016年9月27日 下午7:34:51
 */
public abstract class JwtTokenGenerator {

    /**
     * 功能描述:给User生成token
     * @param u
     * @param secret  秘钥
     * @return token
     * @Author:dengshuai
     * @Date:2016年9月27日 下午7:57:13
     */
    public static String generateToken(UserEntity u, String secret) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
