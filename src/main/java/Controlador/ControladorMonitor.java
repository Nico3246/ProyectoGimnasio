/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *//*
package Controlador;

import Vista.VistaMenus;
import Vista.VistaMensajes;
import Vista.VistaActividades;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import Modelo.MonitorDAO;
import Modelo.Actividad;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.persistence.NoResultException;

/**
 *
 * @author Nicolás
 *//*
public class ControladorMonitor {
    private final SessionFactory sessionFactory;
    private final MonitorDAO monitorDAO;
    private final VistaMensajes vistaMensajes;
    private final VistaActividades vistaActividades;
    
    
    public ControladorMonitor(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
        this.monitorDAO = new MonitorDAO();
        this.vistaMensajes = new VistaMensajes();
        this.vistaActividades = new VistaActividades();
        menuMonitor();
    }
    
    public void menuMonitor()
    {
        VistaMenus vistaMenus = new VistaMenus();
        Scanner sc = new Scanner(System.in);
        String opc=" ";
        
        do{
            vistaMenus.menuMonitor();
            opc= sc.nextLine();
            
            switch(opc)
            {
                case "1":
                    mostrarActividadesMonitor();
                    break;
                case "2":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Introduce uns opción correcta");
            }
        }while(!opc.equals("2"));
    }
    
    public void mostrarActividadesMonitor()
    {
        Session sesion = null;
        Transaction tr = null;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce el DNI del monitor: ");
        String dni = sc.nextLine();
        
        try{
            sesion = sessionFactory.openSession();
            tr = sesion.beginTransaction();
            
            List<Actividad> actividades = monitorDAO.actividadesMonitorDNI(sesion, dni);
            
            vistaActividades.mostrarActividadesMonitor(actividades);
            
            tr.commit();
        }
        catch (NoResultException e)
        {
            if(tr != null && tr.isActive()) tr.rollback();
            vistaMensajes.mensajeConsola("No se ha encontrado ningun monitor con DNI " + dni);
        }
        catch (HibernateException e)
        {
            if(tr != null && tr.isActive()) tr.rollback();
            vistaMensajes.mensajeConsola("Error al acceder a la base de datos");
            
        }
        finally
        {
            if(sesion != null && sesion.isOpen()) sesion.close();
        }
    }
}
*/