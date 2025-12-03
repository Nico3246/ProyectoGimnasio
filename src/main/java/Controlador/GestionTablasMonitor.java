/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Modelo.Monitor;

import java.util.List;

import Vista.VistaMonitores;

/**
 *
 * @author Nicolás
 */
public class GestionTablasMonitor {
    
    private static DefaultTableModel modeloTablaMonitores;
    private VistaMonitores vMonitor;
    
    
    public static void inicializarTablaMonitores(VistaMonitores vMonitor)
    {
        modeloTablaMonitores = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;//las celdas no seran editable
            }
        };
                
        vMonitor.getTablaMonitores().setModel(modeloTablaMonitores);
    }
    
    
    public static void dibujarTablaMonitores(VistaMonitores vMonitor)
    {
        String[] columnas = {"Código","Nombre","DNI","Teléfono","Correo","Fecha incorporación","Nick"};
        
        modeloTablaMonitores.setColumnIdentifiers(columnas);
        
        JTable tabla = vMonitor.getTablaMonitores();
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//redimensiopna automaticamente las columnas
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false);//no se pueden mover las columnas con el raton
        tabla.setAutoCreateRowSorter(true);//ordena haciendo clic
        
        int[] anchuras = {60, 300, 100, 100, 270, 150, 100};
        
        TableColumnModel modeloColumna = tabla.getColumnModel();
        
        for(int i=0;i<anchuras.length;i++)
        {
            TableColumn columna = modeloColumna.getColumn(i);
            int ancho = anchuras[i];
            columna.setMinWidth(ancho);
            columna.setPreferredWidth(ancho);
        }
        
    }
    
    
    public static void rellenarTablaMonitores(List<Monitor> monitores)
    {
        Object[] fila = new Object[7];
        
        for(Monitor m : monitores)
        {
            fila[0] = m.getCodMonitor();       // Código
            fila[1] = m.getNombre();           // Nombre
            fila[2] = m.getDni();              // DNI
            fila[3] = m.getTelefono();         // Teléfono
            fila[4] = m.getCorreo();           // Correo
            fila[5] = m.getFechaEntrada(); // Fecha incorporación
            fila[6] = m.getNick();            // Nick
            
            modeloTablaMonitores.addRow(fila);
        }
    }
    
    public static void vaciarTablaMonitores()
    {
        modeloTablaMonitores.setRowCount(0);
    }
    
    
}
