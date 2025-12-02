/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Socio;
import java.util.List;
/**
 *
 * @author Nicolás
 */
public class VistaSocios {
    
    public void muestasDatosSocios(List<Socio> listSocios)
    {
        System.out.println("------------------------------------------");
        System.out.println("NumSocio\tNombre\t\tTelefono\tCorreo");
        System.out.println("------------------------------------------");
        
        for(Socio s: listSocios)
        {
            System.out.println(s.getNumeroSocio() + "\t" + s.getNombre() + "\t" + s.getTelefono() + "\t" + s.getCorreo());
        }
    }
            
}
