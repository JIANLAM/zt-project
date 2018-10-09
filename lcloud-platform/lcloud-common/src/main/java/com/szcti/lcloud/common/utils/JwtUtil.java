package com.szcti.lcloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

public class JwtUtil {
    public static Long getUserIdByToken(String  authHeader,String secret) {
        Long userid =null;
        try {
            if(StringUtils.hasLength(authHeader) && authHeader.length()>7) {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                if(claims.get("userid")!=null&&!"".equals(claims.get("userid")))
                    userid = Long.valueOf(String.valueOf(claims.get("userid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userid;
    }
    public static Long getLibraryIdByToken(String  authHeader,String secret) {
        Long libraryId =null;
        try {
            if(StringUtils.hasLength(authHeader) && authHeader.length()>7) {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                if(claims.get("libraryid")!=null&&!"".equals(claims.get("libraryid")))
                    libraryId = Long.valueOf(String.valueOf(claims.get("libraryid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libraryId;
    }
    public static Long getPeopleIdByToken(String  authHeader,String secret) {
        Long peopleid =null;
        try {
            if(StringUtils.hasLength(authHeader) && authHeader.length()>7) {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                if(claims.get("peopleid")!=null&&!"".equals(claims.get("peopleid")))
                    peopleid = Long.valueOf(String.valueOf(claims.get("peopleid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peopleid;
    }

    public static Long getReaderIdByToken(String  authHeader,String secret) {
        Long readerid =null;
        try {
            if(StringUtils.hasLength(authHeader) && authHeader.length()>7) {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                if(claims.get("readerid")!=null&&!"".equals(claims.get("readerid")))
                    readerid = Long.valueOf(String.valueOf(claims.get("readerid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readerid;
    }

    public static Integer getUserTypeByToken(String  authHeader,String secret) {
        Integer userType =null;
        try {
            if(StringUtils.hasLength(authHeader) && authHeader.length()>7) {
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                if(claims.get("userType")!=null&&!"".equals(claims.get("userType")))
                    userType = Integer.valueOf(String.valueOf(claims.get("userType")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userType;
    }
}
