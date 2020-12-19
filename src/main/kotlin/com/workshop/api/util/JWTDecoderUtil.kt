package com.workshop.api.util

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.interfaces.DecodedJWT
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
public class JWTDecoderUtil() {

    companion object {
        private val LOG = LoggerFactory.getLogger(JWTDecoderUtil::class.java)
    }

    @Throws(JWTDecodeException::class)
    fun getEmail(authorizationHeader: String): String? {
        try {
            val token = authorizationHeader.replace("Bearer ", "")
            val jwt = JWT.decode(token)
            val subject = jwt.getSubject()
            val email = subject.replace("@clients", "")
            LOG.info("Email: $email")
            return email
        } catch (e: JWTDecodeException) {
            LOG.info("Failed to decode token as jwt", e)
            throw JWTDecodeException("Failed to decode token as jwt", e)
        }
    }
}