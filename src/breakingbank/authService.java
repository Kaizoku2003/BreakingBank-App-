/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakingbank;

/**
 *
 * @author Jesús Centurión
 * @author Fabrizio Falcón
 * @author Santino Gianninotto
 * @author Benjamín Ojeda
 *
 * clase encargada del servicio de autenticacion de usuarios
 */
public class authService {

    private final UsuarioRepository repo = new UsuarioRepository();

    // usuario actual logueado (compartido para toda la app)
    private static Usuario usuarioActual = null;

    public boolean registrarUsuario(String nombre, String telefono, String correo,
            String direccion, String cedula, String password, String pinTransaccion) {

        if (repo.existeCedula(cedula)) {
            return false; // ya existe
        }

        Usuario u = new Usuario(nombre, telefono, correo, direccion, cedula, password, 0.0, 0.0, 0.0, 0.0, pinTransaccion);
        return repo.guardar(u);
    }

    public Usuario iniciarSesion(String nombre, String password) {
        Usuario u = repo.buscarPorNombreYPassword(nombre, password);
        if (u != null) {
            usuarioActual = u;
        }
        return u;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
