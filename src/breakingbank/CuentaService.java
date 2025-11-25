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
 *
 */
/**
 *
 * Clase encargada de manejar las operaciones de los usuarios
 */
public class CuentaService {

    private final UsuarioRepository repo = new UsuarioRepository();

    // Saldo del usuario que está logueado
    public double obtenerSaldoActual() {
    Usuario u = AuthService.getUsuarioActual();
    if (u == null) {
        return 0.0;
    }
    return u.getSaldo();
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }

        Usuario u = AuthService.getUsuarioActual();
        if (u == null) {
            return false; // nadie logueado
        }

        double nuevoSaldo = u.getSaldo() + monto;
        u.setSaldo(nuevoSaldo);

        // Guardar en archivo
        return repo.actualizarUsuario(u);
    }
    
    public boolean transferir(String cedulaOrigen, String cedulaDestino, double monto) {
        // 1. Validaciones básicas
        if (monto <= 0) {
            return false;
        }

        // 2. Buscar usuario que envía (Origen)
        Usuario origen = repo.buscarPorCedula(cedulaOrigen);

        // 3. Buscar usuario que recibe (Destino)
        Usuario destino = repo.buscarPorCedula(cedulaDestino);

        // Validar que ambos existan
        if (origen == null || destino == null) {
            return false;
        }

        // 4. Verificar fondos del origen
        if (origen.getSaldo() < monto) {
            return false;
        }

        // --- REALIZAR LA TRANSACCIÓN ---
        // Restar al origen
        origen.setSaldo(origen.getSaldo() - monto);

        // Sumar al destino
        destino.setSaldo(destino.getSaldo() + monto);

        // Guardar cambios de AMBOS usuarios
        boolean guardadoOrigen = repo.actualizarUsuario(origen);
        boolean guardadoDestino = repo.actualizarUsuario(destino);
        
         if (guardadoOrigen && AuthService.getUsuarioActual() != null && 
        AuthService.getUsuarioActual().getCedula().equals(cedulaOrigen)) {
        AuthService.setUsuarioActual(origen);
    }

        return guardadoOrigen && guardadoDestino;
    }
}
