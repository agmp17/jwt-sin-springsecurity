package com.example.utilities;

import com.example.dtos.UsuarioDTO;
import exceptions.TokenNoValidoException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtility {

    public static final int EXPIRATION_IN_MINUTES = 1;
    // public static final int EXPIRATION_IN_MINUTES = 30;

    public static final String SECRET_KEY = "MI CLAVE ES MUY SEGURA 123456789*=)(&- ABC abc";

    public void validarJwt(String authorization) {
        // Verifica que exista el encabezado y use el esquema Bearer.
        if (authorization == null
                || !authorization.regionMatches(true, 0, "Bearer ", 0, 7)) {
            throw new TokenNoValidoException();
        }

        String token = authorization.substring(7).trim();

        if (token.isEmpty()) {
            throw new TokenNoValidoException();
        }

        SecretKey llave = generaLlave();

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(llave)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            System.out.println("El subject en los claims: " + claims.getSubject());

        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenNoValidoException(e);
        }
    }

    public String buildJwt(UsuarioDTO usuarioDTO) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", usuarioDTO.getUsername());
        return construyeJws(extraClaims);
    }

    private String construyeJws(Map<String, Object> extraClaims) {
        Date creado = new Date(System.currentTimeMillis());
        Date expira = new Date(creado.getTime() + (EXPIRATION_IN_MINUTES * 60 * 1000));

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()

                .subject("1 (debería ser el id del usuario en la bd)")
                .expiration(expira)
                .issuedAt(creado)
                .claims(extraClaims)

                .signWith(generaLlave(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey generaLlave() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
