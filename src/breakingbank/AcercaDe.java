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
                abrirDocumentacionPDF();
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
     * abre el pdf con la documentacion
     */
    private void abrirDocumentacionPDF() {
        try {
            // IMPORTANTE: Cambia "documentacion.pdf" por el nombre real de tu archivo
            // El archivo debe estar en la carpeta raíz de tu proyecto (fuera de 'src')
            File archivoPDF = new File("documentacion.pdf");

            if (archivoPDF.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivoPDF);
                } else {
                    JOptionPane.showMessageDialog(this, "El sistema no soporta abrir archivos automáticamente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el archivo 'documentacion.pdf'.\nAsegúrate de que esté en la carpeta del proyecto.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al abrir el PDF: " + ex.getMessage());
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
