/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakingbank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesús Centurión
 * @author Fabrizio Falcón
 * @author Santino Gianninotto
 * @author Benjamín Ojeda
 *
 * Clase encargada de leer los datos de los usuarios registrados de usuarios.txt
 */
public class UsuarioRepository {

    private static final String FILE_NAME = "usuarios.txt";

    public boolean guardar(Usuario u) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            pw.println(u.aLineaArchivo());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeCedula(String cedula) {
        return buscarPorCedula(cedula) != null;
    }

    public Usuario buscarPorCedula(String cedula) {
        for (Usuario u : leerTodos()) {
            if (u.getCedula().equals(cedula)) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarPorNombreYPassword(String nombre, String password) {
        for (Usuario u : leerTodos()) {
            if (u.getNombreCompleto().equals(nombre)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> leerTodos() {
        List<Usuario> lista = new ArrayList<>();
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario u = Usuario.desdeLineaArchivo(linea);
                if (u != null) {
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Actualizar datos de un usuario (incluye saldo)
     *
     * @param usuario
     * @return
     */
    public boolean actualizarUsuario(Usuario usuario) {
        List<Usuario> usuarios = leerTodos();
        boolean encontrado = false;

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getCedula().equals(usuario.getCedula())) {
                usuarios.set(i, usuario); // reemplazar
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        // Reescribir todo el archivo
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Usuario u : usuarios) {
                pw.println(u.aLineaArchivo());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
