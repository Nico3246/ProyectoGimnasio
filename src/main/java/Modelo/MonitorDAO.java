/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Actividad;
import Modelo.Monitor;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;

import java.util.List;

/**
 *
 * @author Nicolás
 */
public class MonitorDAO {
    
    public List<Actividad> actividadesMonitorDNI(Session sesion, String dni) throws NoResultException
    {
        TypedQuery<Actividad> q = sesion.createQuery("SELECT a FROM Actividad a WHERE a.monitorResponsable.dni = : dni" , Actividad.class);
        q.setParameter("dni", dni);
        
        List<Actividad> actividades = q.getResultList();
        
        if(actividades.isEmpty())
            throw new NoResultException("No existe monitor con ese DNI o no tiene actividades asignadas");
        
        return actividades;
    }
    
    
    public List<Monitor> listaMonitores(Session sesion)
    {
        TypedQuery<Monitor> q = sesion.createNamedQuery("Monitor.findAll", Monitor.class);
        return q.getResultList();
    }
    
    
    public String SigCodigoMonitor(Session sesion)
    {
        TypedQuery<String> q = sesion.createQuery("SELECT MAX(m.codMonitor) FROM Monitor m", String.class);
        
        String ultimoCodigo = q.getSingleResult();
        
        if(ultimoCodigo==null)
        {
            return "M001";
        }
        
        int numero = Integer.parseInt(ultimoCodigo.substring(1));//quito la m
        numero++;
        
        if(numero<100)
            return String.format("M0%2d",numero);//m+3 digitos
        else
            return String.format("M",numero);
    }
    
    
    public void insertarMonitor(Session sesion, Monitor m)
    {
        sesion.persist(m);
    }
    
    
    public void bajaMonitor(Session sesion, String codMonitor)
    {
        Monitor m = sesion.get(Monitor.class, codMonitor);
        if(m != null)
        {
            sesion.remove(m);
        }
    }
    
    
    public Monitor obtenerMonitorCodigo(Session sesion, String codMonitor) {
        return sesion.get(Monitor.class, codMonitor);
    }

    public void actualizarMonitor(Session sesion, Monitor m) {
        sesion.merge(m);
    }

}
