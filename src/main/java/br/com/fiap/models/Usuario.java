package br.com.fiap.models;

import br.com.fiap.enums.RoleEnum;

public class Usuario {
    private Long id;
    private String usuario;
    private String senha;
    private RoleEnum role;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String senha, RoleEnum role) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
