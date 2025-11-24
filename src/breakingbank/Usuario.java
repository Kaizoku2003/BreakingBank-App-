/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakingbank;

/**
 *
 * @author Jesús Centurión
 * @author Fabrizio Falcón
 * @author Santino Gianninoto
 * @author Benjamín Ojeda
 */

public class Usuario {

    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;
    private String cedula;
    private String password;
    private double saldo; // 💰 saldo propio
    private double deudaTarjeta;
    private double deudaTelefonia;
    private double deudaANDE;

    public Usuario(String nombreCompleto, String telefono, String correo,
                   String direccion, String cedula, String password, double saldo, double deudaTarjeta,
                   double deudaTelefonia, double deudaANDE) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.cedula = cedula;
        this.password = password;
        this.saldo = saldo;
        this.deudaTarjeta = deudaTarjeta;
        this.deudaTelefonia = deudaTelefonia;
        this.deudaANDE = deudaANDE;
    }

    // Constructor sin saldo (por si quieres)
    public Usuario(String nombreCompleto, String telefono, String correo,
                   String direccion, String cedula, String password) {
        this(nombreCompleto, telefono, correo, direccion, cedula, password, 0.0, 0.0, 0.0, 0.0);
    }

    // Getters y setters
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    
    public double getDeudaTarjeta() { return deudaTarjeta; }
    public void setDeudaTarjeta(double d) { deudaTarjeta = d; }

    public double getDeudaTelefonia() { return deudaTelefonia; }
    public void setDeudaTelefonia(double d) { deudaTelefonia = d; }

    public double getDeudaANDE() { return deudaANDE; }
    public void setDeudaANDE(double d) { deudaANDE = d; }

    // Para guardar en el archivo
    public String aLineaArchivo() {
        // Formato: cedula;nombre;telefono;correo;direccion;password;saldo
        return cedula + ";" + nombreCompleto + ";" + telefono + ";" + correo + ";" +
               direccion + ";" + password + ";" + saldo + ";" +
               deudaTarjeta + ";" + deudaTelefonia + ";" + deudaANDE;
    }

    // Para leer desde el archivo
    public static Usuario desdeLineaArchivo(String linea) {
        String[] partes = linea.split(";");
        if (partes.length < 10) {
            return null; // línea vieja o mal formada
        }
        String cedula    = partes[0];
        String nombre    = partes[1];
        String telefono  = partes[2];
        String correo    = partes[3];
        String direccion = partes[4];
        String password  = partes[5];
        double saldo     = 0.0;
        try {
            saldo = Double.parseDouble(partes[6]);
        } catch (NumberFormatException e) {
            saldo = 0.0;
        }
        double d1 = Double.parseDouble(partes[7]);
        double d2 = Double.parseDouble(partes[8]);
        double d3 = Double.parseDouble(partes[9]);
        return new Usuario(nombre, telefono, correo, direccion, cedula, password, saldo, d1, d2, d3);
    }
}
