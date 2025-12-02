/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Actividad;

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
}
