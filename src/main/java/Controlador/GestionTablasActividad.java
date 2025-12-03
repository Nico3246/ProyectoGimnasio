/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Modelo.Actividad;

import java.util.List;

import Vista.VistaActividades;

/**
 *
 * @author Nicolás
 */
public class GestionTablasActividad {
    
    private static DefaultTableModel modeloTablaActividades;
    private VistaActividades vActividad;
    
    
    public static void inicializarTablaActividades(VistaActividades vActividad)
    {
        modeloTablaActividades = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;//las celdas no seran editable
            }
        };
                
        vActividad.getTablaActividades().setModel(modeloTablaActividades);
    }
    
    
    public static void dibujarTablaActividades(VistaActividades vActividad)
    {
        String[] columnas = {"idActividad","Nombre","Día","Hora","Descripción","precioBaseMes","monitorResponsable"};
        
        modeloTablaActividades.setColumnIdentifiers(columnas);
        
        JTable tabla = vActividad.getTablaActividades();
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//redimensiopna automaticamente las columnas
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false);//no se pueden mover las columnas con el raton
        tabla.setAutoCreateRowSorter(true);//ordena haciendo clic
        
        int[] anchuras = {60, 270, 50, 50, 270, 150, 100};
        
        TableColumnModel modeloColumna = tabla.getColumnModel();
        
        for(int i=0;i<anchuras.length;i++)
        {
            TableColumn columna = modeloColumna.getColumn(i);
            int ancho = anchuras[i];
            columna.setMinWidth(ancho);
            columna.setPreferredWidth(ancho);
        }
        
    }
    
    
    public static void rellenarTablaActividades(List<Actividad> actividades)
    {
        Object[] fila = new Object[7];
        
        for(Actividad a : actividades)
        {
            fila[0] = a.getIdActividad();       
            fila[1] = a.getNombre();           
            fila[2] = a.getDia();              
            fila[3] = a.getHora();         
            fila[4] = a.getDescripcion();           
            fila[5] = a.getPrecioBaseMes();
            fila[6] = a.getMonitorResponsable();           
            
            modeloTablaActividades.addRow(fila);
        }
    }
    
    public static void vaciarTablaActividades()
    {
        modeloTablaActividades.setRowCount(0);
    }
    
    
}