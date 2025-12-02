/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Actividad;
import java.util.List;
/**
 *
 * @author Nicolás
 */
public class VistaActividades {
    
    public void mostrarActividadesMonitor(List<Actividad> actividades)
    {
        System.out.println("Nombre\t\tDia\tHora");
        System.out.println("----------------------------------------");
        for(Actividad a : actividades)
        {
            System.out.println(a.getNombre() + "\t" + a.getDia() + "\t" + a.getHora());
        }
    }
}
