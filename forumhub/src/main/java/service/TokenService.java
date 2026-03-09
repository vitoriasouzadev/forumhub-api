package br.com.alura.forumhub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String secret = "12345678";

    public String gerarToken(String login){

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("forumhub")
                    .withSubject(login)
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);

        } catch (Exception exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }

    }

    public String getSubject(String token){

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("forumhub")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (Exception exception){
            return null;
        }

    }

    private Instant dataExpiracao(){

        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));

    }

}