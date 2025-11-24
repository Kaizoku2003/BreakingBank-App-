/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakingbank;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesús enturión
 * @author Fabrizio Falcón
 * @author Santino Gianninoto
 * @author Benjamín Ojeda
 *
 */
/**
 * clase encargada de generar la interfaz "Acerca de" que muestra los javadocs
 */
public class AcercaDe extends javax.swing.JFrame {

    public AcercaDe() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Inicia los componentes de la interfaz
     */
    private void initComponents() {

        JLabel lblInformacion = new javax.swing.JLabel();
        btnAbrirPDF = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acerca del Proyecto");

        // CONFIGURACIÓN DEL TEXTO 
        lblInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformacion.setText("<html><center>"
                + "<h3>Facultad Politécnica<br>Lenguajes de Programación II</h3>"
                + "<br>"
                + "<h1 style='font-size:24px'>EXAMEN FINAL - 2025</h1>"
                + "<br>"
                + "<b>Grupo N°3</b><br>"
                + "Jesús Centurión<br>"
                + "Fabrizio Falcón<br>"
                + "Santino Gianninoto<br>"
                + "Benjamín Ojeda"
                + "</center></html>");

        // BOTÓN PDF
        btnAbrirPDF.setFont(new java.awt.Font("SansSerif", 0, 14));
        btnAbrirPDF.setText("Ver Documentación (PDF)");
        btnAbrirPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirDocumentacion();
            }
        });

        // BOTÓN VOLVER
        btnVolver.setText("Atrás");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverAlMenu();
            }
        });

        // DISEÑO (LAYOUT)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE) // Ancho ajustado
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnVolver)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAbrirPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE) // Altura para el texto
                                .addGap(18, 18, 18)
                                .addComponent(btnAbrirPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(btnVolver)
                                .addContainerGap())
        );

        pack();
    }

    /**
     * abre la documentacion del proyecto (javadoc)
     */
    private void abrirDocumentacion() {
        try {
            // 1. Definir el archivo (igual que antes)
            java.io.File proyectoDir = new java.io.File(System.getProperty("user.dir"));
            java.io.File docFile = new java.io.File(proyectoDir, "dist/javadoc/index.html");

            if (docFile.exists()) {
                // 2. Intentar abrirlo de forma robusta
                abrirEnNavegador(docFile);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "No se encontró la documentación en: \n" + docFile.getAbsolutePath(),
                        "Documentación no encontrada",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al intentar abrir la documentación:\n" + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método auxiliar para manejar la lógica de "Cross-Platform"
    */
    private void abrirEnNavegador(java.io.File file) throws java.io.IOException {
        String os = System.getProperty("os.name").toLowerCase();

        // Intento 1: Windows / Mac
        boolean desktopSupported = java.awt.Desktop.isDesktopSupported()
                && java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.BROWSE);

        if (desktopSupported) {
            try {
                java.awt.Desktop.getDesktop().browse(file.toURI());
                return;
            } catch (Exception e) {
                // Si falla aunque diga que es soportado (común en Linux), seguimos al plan B
                System.err.println("Fallo Desktop.browse, intentando comando nativo...");
            }
        }

        // Intento 2: Linux
        if (os.contains("nux") || os.contains("nix")) {
            // xdg-open es el estándar en Linux para abrir archivos con su programa predeterminado
            Runtime.getRuntime().exec(new String[]{"xdg-open", file.getAbsolutePath()});
        } else if (os.contains("win")) {
            Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", file.getAbsolutePath()});
        } else if (os.contains("mac")) {
            Runtime.getRuntime().exec(new String[]{"open", file.getAbsolutePath()});
        } else {
            throw new java.io.IOException("No se pudo abrir el navegador automáticamente en este sistema operativo.");
        }
    }

    /**
     * vuelve al menu
     */
    private void volverAlMenu() {
        new Menu().setVisible(true);
        this.dispose();
    }

    /**
     * inicializa la interfaz de para la documentacion
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcercaDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcercaDe().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnAbrirPDF;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
}
