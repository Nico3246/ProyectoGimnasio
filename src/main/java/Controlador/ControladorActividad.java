/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Vista.VistaMensajes;
import Vista.VistaActividades;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.ActividadDAO;
import Modelo.Actividad;

import java.util.List;


/**
 *
 * @author Nicolás
 */
public class ControladorActividad {
    
    private final SessionFactory sessionFactory;
    private final ActividadDAO ActividadDAO;
    private final VistaMensajes vMensaje;
    private final VistaActividades vActividad;
    
    private Session sesion;
    private Transaction tr;
    
    public ControladorActividad(SessionFactory sessionFactory, VistaActividades vistaActividad, VistaMensajes vistaMensaje)
    {
        this.sessionFactory = sessionFactory;
        this.ActividadDAO = new ActividadDAO();
        vActividad = vistaActividad;
        vMensaje = vistaMensaje;
        
        GestionTablasActividad.inicializarTablaActividades(vActividad);
        GestionTablasActividad.dibujarTablaActividades(vActividad);
        
        dibujaRellenarTablaActividades();
        
    }
    
    private void dibujaRellenarTablaActividades()
    {
        GestionTablasActividad.dibujarTablaActividades(vActividad);
        
        try{
            sesion = sessionFactory.openSession();
            tr=sesion.beginTransaction();
            List<Actividad> lActividades = ActividadDAO.listaActividades(sesion);
            GestionTablasActividad.vaciarTablaActividades();
            GestionTablasActividad.rellenarTablaActividades(lActividades);
            tr.commit();
        }
        catch (Exception ex){
            tr.rollback();
            vMensaje.Mensaje(null,"Error en la petición de Actividades\n" + ex.getMessage(),null,"error");
        }
        finally{
            if(sesion != null && sesion.isOpen()){
                sesion.close();
            }
        }
    }
}
