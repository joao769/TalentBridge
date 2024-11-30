package main.java.com.example.util;

public class SessionContext {
    private static int usuarioLogadoId = -1;

    public static int getUsuarioLogadoId() {
        return usuarioLogadoId;
    }

    public static void setUsuarioLogadoId(int id) {
        usuarioLogadoId = id;
    }

    public static void logout() {
        usuarioLogadoId = -1;
    }
}
