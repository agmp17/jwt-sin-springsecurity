package com.example;

public class UsuarioDAO {

    public static boolean validar(String username, String password) {

        if( username.equals("admin") && password.equals("123") ) {
            return true;
        } else {
            return false;
        }
    }
}
