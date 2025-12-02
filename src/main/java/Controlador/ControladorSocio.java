/*/*
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

import Modelo.SocioDAO;
import Modelo.Socio;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nicolás
 *//*
public class ControladorSocio 
{
    private final SessionFactory sessionFactory;
    private SocioDAO socioDAO = null;
    private Scanner sc = new Scanner(System.in);
    
    ControladorSocio(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
        socioDAO = new SocioDAO();
        menuSocio();
    }

    public void menuSocio()
    {
        VistaMenus vistaMenus = new VistaMenus();
        
        String opc=" ";
        
        do{
            vistaMenus.menuSocio();
            opc= sc.nextLine();
            
            switch(opc)
            {
                case "1":
                    altaSocio();
                    break;
                case "2":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Introduce uns opción correcta");
            }
        }while(!opc.equals("2"));
    }

    public void altaSocio()
    {
        Session sesion=null;
        Transaction tr=null;
        VistaMensajes vistaMensajes=new VistaMensajes();
        
        System.out.print("Inserta el numero de socio: ");
        String numSocio= sc.nextLine();
        
        System.out.print("Inserta el dni del socio: ");
        String dniSocio= sc.nextLine();
        
        
        try
        {
            sesion =sessionFactory.openSession();
            tr = sesion.beginTransaction();
            
            
            boolean existe=socioDAO.comprobarExisteSocio(sesion,numSocio,dniSocio);
            if (existe==true)
            {
                vistaMensajes.mensajeConsola("Ya existe un socio con ese numero/DNI");
                tr.rollback();
                return;
            }
            
            System.out.print("Inserte el nombre del socio:");
            String nombre= sc.nextLine();
            
            System.out.print("Inserte la fecha de nacimiento del socio (DD/MM/YYYY:");
            String fechaNac= sc.nextLine();
            
            System.out.print("Inserte el telefono del socio:");
            String telefono= sc.nextLine();
            
            System.out.print("Inserte el correo del socio:");
            String correo= sc.nextLine();
            
            System.out.print("Inserte la fecha de entrada del socio (DD/MM/YYYY):");
            String fechaEntrada= sc.nextLine();
            
            System.out.print("Inserte la categoria del socio:");
            Character categoria = sc.nextLine().charAt(0);//categoria es un character no un string
            
            //creo el socio nuevo
            Socio socio=new Socio(numSocio,nombre,dniSocio,fechaEntrada, categoria);
            
            //inserto datos opcionales
            socio.setFechaNacimiento(fechaNac);
            socio.setTelefono(telefono);
            socio.setCorreo(correo);
            
            //inserto el socio
            socioDAO.insertaSocio(sesion, socio);
            tr.commit();
            
            vistaMensajes.mensajeConsola("Se ha dado de al al socio correctamente");
            
        }
        catch(HibernateException e)
        {
            if(tr != null && tr.isActive()) tr.rollback();
            vistaMensajes.mensajeConsola("Se ha producido un error al consultar las inscripciones");
        }
        catch(Exception e)
        {
            if(tr != null && tr.isActive()) tr.rollback();
            vistaMensajes.mensajeConsola("Error inesperado: " + e.getMessage());
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