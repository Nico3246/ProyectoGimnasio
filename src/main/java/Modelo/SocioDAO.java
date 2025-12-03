/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import org.hibernate.Session;

import Modelo.Socio;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Nicolás
 */
public class SocioDAO {
    
    public SocioDAO() {
  
    }
    
    public void insertaSocio(Session sesion, Socio socio) throws Exception{
        sesion.persist(socio);
    }
    
    
    public boolean comprobarExisteSocio(Session sesion, String numeroSocio, String dni)
    {
        String consulta = "FROM Socio s WHERE s.numeroSocio = : numero OR s.dni= :dni";
        
        Socio s = sesion.createQuery(consulta, Socio.class).setParameter("numero", numeroSocio).setParameter("dni", dni).uniqueResult();
        
        return s!=null; //devovlera true si existe alguno
    }
    
    
    public List<Socio> listaSocios(Session sesion)
    {
        TypedQuery<Socio> q = sesion.createNamedQuery("Socio.findAll", Socio.class);
        return q.getResultList();
    }
}
