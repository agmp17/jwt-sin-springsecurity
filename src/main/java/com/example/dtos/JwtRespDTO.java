package com.example.dtos;

import java.util.List;

public class JwtRespDTO {

    private String time;
    private String msg;
    private String sub;
    private List<String> permisos;
    private String jwt;

    public JwtRespDTO() {
    }

    public JwtRespDTO(String time, String msg, String sub, List<String> permisos, String jwt) {
        this.time = time;
        this.msg = msg;
        this.sub = sub;
        this.permisos = permisos;
        this.jwt = jwt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
