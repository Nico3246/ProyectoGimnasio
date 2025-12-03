/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Modelo.Socio;

import java.util.List;

import Vista.VistaSocios;

/**
 *
 * @author Nicolás
 */
public class GestionTablasSocio {
    
    private static DefaultTableModel modeloTablaSocios;
    private VistaSocios vSocio;
    
    
    public static void inicializarTablaSocios(VistaSocios vSocio)
    {
        modeloTablaSocios = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;//las celdas no seran editable
            }
        };
                
        vSocio.getTablaSocios().setModel(modeloTablaSocios);
    }
    
    
    public static void dibujarTablaSocios(VistaSocios vSocio)
    {
        String[] columnas = {"Numero Socio","Nombre","DNI","Fecha nacimiento","Telefono","Correo","Fecha entrada", "Categoria"};
        
        modeloTablaSocios.setColumnIdentifiers(columnas);
        
        JTable tabla = vSocio.getTablaSocios();
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//redimensiopna automaticamente las columnas
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false);//no se pueden mover las columnas con el raton
        tabla.setAutoCreateRowSorter(true);//ordena haciendo clic
        
        int[] anchuras = {60, 300, 100, 100, 270, 150, 100, 50};
        
        TableColumnModel modeloColumna = tabla.getColumnModel();
        
        for(int i=0;i<anchuras.length;i++)
        {
            TableColumn columna = modeloColumna.getColumn(i);
            int ancho = anchuras[i];
            columna.setMinWidth(ancho);
            columna.setPreferredWidth(ancho);
        }
        
    }
    
    
    public static void rellenarTablaSocios(List<Socio> Socios)
    {
        Object[] fila = new Object[8];
        
        for(Socio s : Socios)
        {
            fila[0] = s.getNumeroSocio();       
            fila[1] = s.getNombre();           
            fila[2] = s.getDni();             
            fila[3] = s.getFechaNacimiento();         
            fila[4] = s.getTelefono();           
            fila[5] = s.getCorreo(); 
            fila[6] = s.getFechaEntrada();
            fila[7] = s.getCategoria();
            
            modeloTablaSocios.addRow(fila);
        }
    }
    
    public static void vaciarTablaSocios()
    {
        modeloTablaSocios.setRowCount(0);
    }
    
    
}
