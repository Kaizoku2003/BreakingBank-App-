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
    // Necesitamos instanciar authService si no es estático (basado en tu código anterior)
    private final authService authService = new authService(); 

    public boolean transferir(String cedulaDestino, double monto) {
        // 1. Validaciones básicas
        if (monto <= 0) return false;

        // 2. Obtener usuario que envía (Origen)
        Usuario origen = authService.getUsuarioActual();
        if (origen == null) return false; // No hay nadie logueado

        // 3. Verificar fondos suficientes
        if (origen.getSaldo() < monto) {
            return false; // No tiene suficiente dinero
        }

        // 4. Buscar usuario que recibe (Destino)
        // OJO: Aquí asumimos que tu repositorio tiene este método.
        // Si no lo tiene, tendrás que crearlo en UsuarioRepository.
        Usuario destino = repo.buscarPorCedula(cedulaDestino);
        
        if (destino == null) {
            return false; // La cuenta destino no existe
        }
        
        // --- REALIZAR LA TRANSACCIÓN ---
        
        // Restar al origen
        origen.setSaldo(origen.getSaldo() - monto);
        
        // Sumar al destino
        destino.setSaldo(destino.getSaldo() + monto);

        // Guardar ambos cambios
        boolean guardarOrigen = repo.actualizarUsuario(origen);
        boolean guardarDestino = repo.actualizarUsuario(destino);

        return guardarOrigen && guardarDestino;
    }
}
