/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Vista.VistaMensajes;
import Vista.VistaMonitores;
import Vista.VistaNuevoMonitor;
import Vista.VistaActualizarMonitor;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.MonitorDAO;
import Modelo.Monitor;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;


/**
 *
 * @author Nicolás
 */
public class ControladorMonitor {
    
    private final SessionFactory sessionFactory;
    private final MonitorDAO monitorDAO;
    private final VistaMensajes vMensaje;
    private final VistaMonitores vMonitor;
    
    private VistaNuevoMonitor vNuevoMonitor;
    private VistaActualizarMonitor vActMonitor;
    private Monitor monitorActual; //monitor que se esta actualizando
    private Session sesion;
    private Transaction tr;
    
    public ControladorMonitor(SessionFactory sessionFactory, VistaMonitores vistaMonitor, VistaMensajes vistaMensaje)
    {
        this.sessionFactory = sessionFactory;
        this.monitorDAO = new MonitorDAO();
        vMonitor = vistaMonitor;
        vMensaje = vistaMensaje;
        vMonitor.setControladorMonitor(this);
        
        GestionTablasMonitor.inicializarTablaMonitores(vMonitor);
        GestionTablasMonitor.dibujarTablaMonitores(vMonitor);
        
        dibujaRellenarTablaMonitores();
        
    }
    
    private void dibujaRellenarTablaMonitores()
    {
        GestionTablasMonitor.dibujarTablaMonitores(vMonitor);
        
        try{
            sesion = sessionFactory.openSession();
            tr=sesion.beginTransaction();
            List<Monitor> lMonitores = monitorDAO.listaMonitores(sesion);
            GestionTablasMonitor.vaciarTablaMonitores();
            GestionTablasMonitor.rellenarTablaMonitores(lMonitores);
            tr.commit();
        }
        catch (Exception ex){
            tr.rollback();
            vMensaje.Mensaje(null,"Error en la petición de Monitores\n" + ex.getMessage(),null,"error");
        }
        finally{
            if(sesion != null && sesion.isOpen()){
                sesion.close();
            }
        }
    }
    
    
    public void abrirVentanaNuevoMonitor()
    {
        vNuevoMonitor = new VistaNuevoMonitor();
        
        try{
            sesion = sessionFactory.openSession();
            tr = sesion.beginTransaction();
            
            String sigCodigo = monitorDAO.SigCodigoMonitor(sesion);
            
            tr.commit();
            
            vNuevoMonitor.getTxtCodigo().setText(sigCodigo);
            vNuevoMonitor.getTxtCodigo().setEditable(false);
        }
        catch (Exception e)
        {
            if(tr != null)
            {
                tr.rollback();
            }
            vMensaje.Mensaje(null, "Error sl obtener el siguiente codigo de Monitor\n" + e.getMessage(), null, "error");
        }
        finally
        {
            if (sesion != null && sesion.isOpen())
            {
                sesion.close();
            }
        }
        
        vNuevoMonitor.getBtnInsertar().addActionListener(e -> insertarMonitor());
        vNuevoMonitor.getBtnCancelar().addActionListener(e -> vNuevoMonitor.dispose());
        
        vNuevoMonitor.setVisible(true);
        
        dibujaRellenarTablaMonitores();       
    }
    
    
    public void insertarMonitor()
    {
        try{
            Monitor m = new Monitor();
            m.setCodMonitor(vNuevoMonitor.getTxtCodigo().getText());
            m.setNombre(vNuevoMonitor.getTxtNombre().getText());
            m.setDni(vNuevoMonitor.getTxtDni().getText());
            m.setTelefono(vNuevoMonitor.getTxtTelefono().getText());
            m.setCorreo(vNuevoMonitor.getTxtCorreo().getText());
            m.setNick(vNuevoMonitor.getTxtNick().getText());
            
            Date fechaChooser = vNuevoMonitor.getJdcFechaEntrada().getDate();
            if(fechaChooser != null)
            {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                String fechaString = formatoFecha.format(fechaChooser);
                m.setFechaEntrada(fechaString);
            }
            
            sesion =sessionFactory.openSession();
            tr = sesion.beginTransaction();
            
            monitorDAO.insertarMonitor(sesion,m);
            
            tr.commit();
            
            vMensaje.Mensaje(vNuevoMonitor, "Monitor insertado correctamente", "Información", "info");
            
            vNuevoMonitor.dispose();
        }
        catch (Exception e)
        {
            if(tr != null)
            {
                tr.rollback();
            }
            vMensaje.Mensaje(vNuevoMonitor, "Error al insertar el monitor\n" + e.getMessage(), "Error", "error");
        }
        finally
        {
            if(sesion != null && sesion.isOpen())
            {
                sesion.close();
            }
        }
    }
    
    
    
    
    public void bajaMonitor()
    {
        //compruebo si hay alguna fila seleccionada
        int fila = vMonitor.getTablaMonitores().getSelectedRow();
        if(fila == -1) 
        {
            vMensaje.Mensaje(null, "No hay ningun monitor seleccionado, selecciona un monitor de la tabla", "Aviso", "info");
            return;
        }
        
        //obtener el codigo y nombre del monitor seleccionado
        String codMonitor = (String) vMonitor.getTablaMonitores().getValueAt(fila, 0);//columna codigo
        String nombre = (String) vMonitor.getTablaMonitores().getValueAt(fila, 1);//columna nombre
        
        
        //pedir confirmacion
        String mensaje = "¿Seguro que quieres dar de baja a " + nombre + "?";
        int result = JOptionPane.showConfirmDialog(null, mensaje,"Atención",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        
        if(result != JOptionPane.YES_OPTION)
        {
            return;
        }
        
        //borrar el monitor de la bd
        try{
            sesion = sessionFactory.openSession();
            tr=sesion.beginTransaction();
            
            monitorDAO.bajaMonitor(sesion,codMonitor);
            
            tr.commit();
            
            vMensaje.Mensaje(null, "Se ha dado de baja al monitor correctamente", "Información", "info");
            
            dibujaRellenarTablaMonitores();
        }
        catch (Exception e)
        {
            if (tr != null) {
            tr.rollback();
            }
            vMensaje.Mensaje(null,"Error al eliminar el monitor\n" + e.getMessage(),"Error", "error");
        }
        finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }  
        
    }
    
    
    
    
    
    public void actualizarMonitor()
    {
        int fila= vMonitor.getTablaMonitores().getSelectedRow();
        if(fila == -1)
        {
            vMensaje.Mensaje(null, "No hay ningun monitor seleccionado, selecciona un monitor de la tabla", "Aviso", "info");
            return;
        }

        String codMonitor = (String) vMonitor.getTablaMonitores().getValueAt(fila, 0);

        try
        {
            sesion = sessionFactory.openSession();
            tr=sesion.beginTransaction();

            monitorActual = monitorDAO.obtenerMonitorCodigo(sesion, codMonitor);

            tr.commit();
        }
        catch (Exception e)
        {
            if (tr != null) {
            tr.rollback();
            }
            vMensaje.Mensaje(null,"Error al cargar el monitor\n" + e.getMessage(),"Error", "error");
        }
        finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }

        vActMonitor = new VistaActualizarMonitor();

        vActMonitor.getTxtCodigo().setText(monitorActual.getCodMonitor());
        vActMonitor.getTxtCodigo().setEditable(false);

        vActMonitor.getTxtNombre().setText(monitorActual.getNombre());
        vActMonitor.getTxtDni().setText(monitorActual.getDni());
        vActMonitor.getTxtTelefono().setText(monitorActual.getTelefono());
        vActMonitor.getTxtCorreo().setText(monitorActual.getCorreo());
        vActMonitor.getTxtNick().setText(monitorActual.getNick());

        try{
            if(monitorActual.getFechaEntrada() != null)
            {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = formato.parse(monitorActual.getFechaEntrada());
                vActMonitor.getJdcFechaEntrada().setDate(fecha);
            }
        }catch (Exception e) {
            vMensaje.Mensaje(null, "Formato de fecha no válido en la BD", "Aviso", "info");
        }

        vActMonitor.getBtnActualizar().addActionListener(e -> guardarCambiosMonitor());
        vActMonitor.getBtnCancelar().addActionListener(e -> vActMonitor.dispose());

        vActMonitor.setVisible(true);

        dibujaRellenarTablaMonitores();
    }
    
    
    
    private void guardarCambiosMonitor() {
        try {
            monitorActual.setNombre(vActMonitor.getTxtNombre().getText());
            monitorActual.setDni(vActMonitor.getTxtDni().getText());
            monitorActual.setTelefono(vActMonitor.getTxtTelefono().getText());
            monitorActual.setCorreo(vActMonitor.getTxtCorreo().getText());
            monitorActual.setNick(vActMonitor.getTxtNick().getText());

            Date fechaChooser = vActMonitor.getJdcFechaEntrada().getDate();
            if (fechaChooser != null) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaStr = formato.format(fechaChooser);
                monitorActual.setFechaEntrada(fechaStr);
            }

            // 3) Guardar cambios en BD
            sesion = sessionFactory.openSession();
            tr = sesion.beginTransaction();

            monitorDAO.actualizarMonitor(sesion, monitorActual);

            tr.commit();

            vMensaje.Mensaje(null, "Monitor actualizado correctamente.", "Información", "info");
            vActMonitor.dispose();

        } catch (Exception ex) {
            if (tr != null) tr.rollback();
            {
                vMensaje.Mensaje(null, "Error al actualizar el monitor\n" + ex.getMessage(), "Error", "error");
            }
                
        } finally {
            if (sesion != null && sesion.isOpen()){
                sesion.close();
            }
        }
    }

    
}
