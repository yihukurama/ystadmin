package com.gdyunst.ystadmin.application.security;

import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
/**
 * 功能描述:验证token 
 * @Author:denghsuai
 * @Date:2016年9月29日 上午9:31:19
 */
public class JwtTokenValidator {
	public static UserEntity parseToken(String token,String secret) {
		UserEntity u = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            u = new UserEntity();
            u.setUsername(body.getSubject());
            u.setId((String) body.get("userId"));

        } catch (JwtException e) {
            // Simply print the exception and null will be returned for the userDto
            e.printStackTrace();
        }
        return u;
    }
}

