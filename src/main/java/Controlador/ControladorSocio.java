/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Vista.VistaMensajes;
import Vista.VistaSocios;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.SocioDAO;
import Modelo.Socio;

import java.util.List;


/**
 *
 * @author Nicolás
 */
public class ControladorSocio {
    
    private final SessionFactory sessionFactory;
    private final SocioDAO socioDAO;
    private final VistaMensajes vMensaje;
    private final VistaSocios vSocio;
    
    private Session sesion;
    private Transaction tr;
    
    public ControladorSocio(SessionFactory sessionFactory, VistaSocios vistaSocio, VistaMensajes vistaMensaje)
    {
        this.sessionFactory = sessionFactory;
        this.socioDAO = new SocioDAO();
        vSocio = vistaSocio;
        vMensaje = vistaMensaje;
        
        GestionTablasSocio.inicializarTablaSocios(vSocio);
        GestionTablasSocio.dibujarTablaSocios(vSocio);
        
        dibujaRellenarTablaSocios();
        
    }
    
    private void dibujaRellenarTablaSocios()
    {
        GestionTablasSocio.dibujarTablaSocios(vSocio);
        
        try{
            sesion = sessionFactory.openSession();
            tr=sesion.beginTransaction();
            List<Socio> lSocios = socioDAO.listaSocios(sesion);
            GestionTablasSocio.vaciarTablaSocios();
            GestionTablasSocio.rellenarTablaSocios(lSocios);
            tr.commit();
        }
        catch (Exception ex){
            tr.rollback();
            vMensaje.Mensaje(null,"Error en la petición de Socios\n" + ex.getMessage(),null,"error");
        }
        finally{
            if(sesion != null && sesion.isOpen()){
                sesion.close();
            }
        }
    }
}
