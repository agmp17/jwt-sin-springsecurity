package com.example.controllers;

import com.example.dtos.JwtRespDTO;
import com.example.dtos.UsuarioDTO;
import com.example.UsuarioDAO;
import com.example.services.AuthService;
import com.example.utilities.JwtUtility;
import com.example.utilities.TimeUtility;
import exceptions.CredencialesInvalidasException;
import exceptions.TokenNoValidoException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.*;

@RestController
@RequestMapping("/api/jwt-simple")
public class UsuarioController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private TimeUtility timeUtility;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<String> saludo() {
        return ResponseEntity.ok("Está Funka");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {

        try {
            // validamos que el usuario tenga credenciales válidas
            List<String> permisos = authService.authenticar(usuarioDTO);

            // Solo se ejecuta si la autenticación fue exitosa.
            String jwt = jwtUtility.buildJwt(usuarioDTO);

            JwtRespDTO respuesta = new JwtRespDTO(
                    timeUtility.getHora(),
                    "Autenticación correcta",
                    usuarioDTO.getUsername(),
                    permisos,
                    jwt
            );

            return ResponseEntity.ok(respuesta);

        } catch (CredencialesInvalidasException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o contraseña incorrectos");
        }
    }

    @PostMapping("/valida")
    public ResponseEntity<String> validar(
            @RequestHeader(value = "Authorization", required = false) String authorization) {

        try {
            jwtUtility.validarJwt(authorization);
            return ResponseEntity.ok("token válido");

        } catch (TokenNoValidoException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.WWW_AUTHENTICATE, "Bearer")
                    .body(e.getMessage());
        }
    }

    private SecretKey generaLlave(String key) {
        byte[] keyenbytes =  key.getBytes();
        String s = new String(keyenbytes);
        System.out.println(s);
        SecretKey lakey = Keys.hmacShaKeyFor(key.getBytes());
        return lakey;
    }
}
