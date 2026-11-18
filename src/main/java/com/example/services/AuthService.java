package com.example.services;

import com.example.dtos.UsuarioDTO;
import exceptions.CredencialesInvalidasException;
import exceptions.TokenNoValidoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    public List<String> authenticar(UsuarioDTO usuarioDTO) {

        // Esto debería venir de la bd, pero para este ejercicio, lo ponemos así
        if(usuarioDTO == null || !usuarioDTO.getUsername().equals("angelito")  || !usuarioDTO.getPassword().equals("123") ){
            throw new CredencialesInvalidasException();
        }

        return List.of("ver_reportes", "crear_pdf", "eliminar_facturas");
    }

}
