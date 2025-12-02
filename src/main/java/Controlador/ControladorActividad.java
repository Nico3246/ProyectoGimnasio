/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *//*
package Controlador;

import Vista.VistaMenus;
import Vista.VistaMensajes;
import Vista.VistaSocios;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import Modelo.ActividadDAO;
import Modelo.Actividad;
import Modelo.Socio;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Nicolás
 *//*
public class ControladorActividad {
    
    private final SessionFactory sessionFactory;
    private ActividadDAO actividadDao=null;
    
    public ControladorActividad(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        actividadDao=new ActividadDAO();
        menuActividad();
    }
    
    public void menuActividad()
    {
        VistaMenus vistaMenus = new VistaMenus();
        
        Scanner sc = new Scanner(System.in);
        
        String opc=" ";
        
        do{
            vistaMenus.menuActividad();
            opc= sc.nextLine();
            
            switch(opc)
            {
                case "1":
                    MostrarInscripciones();
                    break;
                case "2":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Introduce uns opción correcta");
            }
        
        
        }while(!opc.equals("2"));
    
    }
    
    
    public void MostrarInscripciones()
    {
        Session sesion=null;
        Transaction tr=null;
        VistaMensajes vistaMensajes = new VistaMensajes();
        VistaSocios vistaSocios = new VistaSocios();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el identificador de la actividad: ");
        String idActividad=sc.nextLine();
        
        try
        {
            sesion =sessionFactory.openSession();
            tr = sesion.beginTransaction();
            
            //compruebo que el id de la actividad existe
            Actividad actividad = actividadDao.buscarPorID(sesion, idActividad);
            
            if(actividad==null)
            {
                vistaMensajes.mensajeConsola("La actividad con id "+ idActividad + " no existe");
                tr.commit();
                return;
            }
            
            List<Socio> listaSocios = new ArrayList<>(actividad.getSocios());
            
            if(listaSocios.isEmpty())
            {
                vistaMensajes.mensajeConsola("La actividad " + actividad.getNombre() + " no tiene socios inscritos");
            }
            else
            {
                //muestra los datos de los socios, nº,nombre ,telefono y correo
                vistaSocios.muestasDatosSocios(listaSocios);
            }
            
            tr.commit();
            
        }
        catch(HibernateException e)
        {
            if(tr != null && tr.isActive()) tr.rollback();
            vistaMensajes.mensajeConsola("Se ha producido un error al consultar las inscripciones");
        }
        finally
        {
            if(sesion != null && sesion.isOpen())
            {
                sesion.close();
            }
        }
        
    }
}
*/