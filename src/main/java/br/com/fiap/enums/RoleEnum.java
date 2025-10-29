package br.com.fiap.enums;

public enum RoleEnum {
    ADMIN,
    USER;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
