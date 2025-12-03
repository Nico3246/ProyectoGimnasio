/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author Nicolás
 */

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Clase para mostrar mensajes mediante JOptionPane
 * como en el ejemplo del PDF: vMensaje.Mensaje(..., "info"/"error"...)
 */
public class VistaMensajes {

    /**
     * Muestra un mensaje en una ventana emergente.
     *
     * @param parent Ventana padre (puede ser null).
     * @param texto  Texto del mensaje.
     * @param titulo Título de la ventana (si es null se pone uno por defecto).
     * @param tipo   "info", "error", "warning", "plain".
     */
    public void Mensaje(Component parent, String texto, String titulo, String tipo) {

        int messageType;

        if (tipo == null) {
            tipo = "plain";
        }

        switch (tipo.toLowerCase()) {
            case "info":
                messageType = JOptionPane.INFORMATION_MESSAGE;
                if (titulo == null) titulo = "Información";
                break;
            case "error":
                messageType = JOptionPane.ERROR_MESSAGE;
                if (titulo == null) titulo = "Error";
                break;
            case "warning":
            case "advertencia":
                messageType = JOptionPane.WARNING_MESSAGE;
                if (titulo == null) titulo = "Advertencia";
                break;
            default:
                messageType = JOptionPane.PLAIN_MESSAGE;
                if (titulo == null) titulo = "Mensaje";
                break;
        }

        JOptionPane.showMessageDialog(parent, texto, titulo, messageType);
    }
}
