/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Vista.VistaMensajes;
import Vista.VistaMonitores;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.MonitorDAO;
import Modelo.Monitor;

import java.util.List;


/**
 *
 * @author Nicolás
 */
public class ControladorMonitor {
    
    private final SessionFactory sessionFactory;
    private final MonitorDAO monitorDAO;
    private final VistaMensajes vMensaje;
    private final VistaMonitores vMonitor;
    
    private Session sesion;
    private Transaction tr;
    
    public ControladorMonitor(SessionFactory sessionFactory, VistaMonitores vistaMonitor, VistaMensajes vistaMensaje)
    {
        this.sessionFactory = sessionFactory;
        this.monitorDAO = new MonitorDAO();
        vMonitor = vistaMonitor;
        vMensaje = vistaMensaje;
        
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
}
