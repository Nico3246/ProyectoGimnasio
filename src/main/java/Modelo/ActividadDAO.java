/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Nicolás
 */
import org.hibernate.Session;
import jakarta.persistence.TypedQuery;

public class ActividadDAO {
    
    public ActividadDAO()
    {
       
    } 
    
    public Actividad buscarPorID(Session sesion, String idActividad)
    {
        TypedQuery<Actividad> q = sesion.createNamedQuery("Actividad.findByIdActividad",Actividad.class);
         
        q.setParameter("idActividad", idActividad);
         
        return q.getResultStream().findFirst().orElse(null);
    }
}
