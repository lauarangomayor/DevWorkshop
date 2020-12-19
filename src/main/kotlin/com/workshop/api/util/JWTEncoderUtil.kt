package com.workshop.api.util

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.interfaces.DecodedJWT
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import java.util.stream.Collectors
import java.util.Calendar
import java.util.*

@Component
public class JWTEncoderUtil() {

    companion object {
        private val LOG = LoggerFactory.getLogger(JWTEncoderUtil::class.java)
    }
    fun getJWTToken(email:String): String {
        val secretKey = "mySecretKey"
        val grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
        val claims = Jwts.claims()
                            .setSubject(email)
        claims.put("identity", email)
        val token = Jwts.builder()
                        .setId("softtekJWT")
                        .setClaims(claims)
                        .claim("authorities", 
                                grantedAuthorities.stream()
                                                    .map(GrantedAuthority::getAuthority)
                                                    .collect(Collectors.toList()))
                        .setIssuedAt(Date(System.currentTimeMillis()))
                        .setExpiration(Date(System.currentTimeMillis() + 3600000*5))
                        .signWith(SignatureAlgorithm.HS512,
                                    secretKey.toByteArray()).compact()
        return "Bearer " + token
        }
    }