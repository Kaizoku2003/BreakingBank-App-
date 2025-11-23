/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakingbank;

/**
 *
 * @author benja
 */



public class CuentaService {

    private final UsuarioRepository repo = new UsuarioRepository();

    // Saldo del usuario que está logueado
    public double obtenerSaldoActual() {
        Usuario u = authService.getUsuarioActual();
        if (u == null) {
            return 0.0;
        }
        return u.getSaldo();
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }

        Usuario u = authService.getUsuarioActual();
        if (u == null) {
            return false; // nadie logueado
        }

        double nuevoSaldo = u.getSaldo() + monto;
        u.setSaldo(nuevoSaldo);

        // Guardar en archivo
        return repo.actualizarUsuario(u);
    }
}
