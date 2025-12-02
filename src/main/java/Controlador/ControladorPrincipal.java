/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import org.hibernate.SessionFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import Vista.VistaMensajes;
import Vista.VistaPrincipal;
/**
 *
 * @author Nicolás
 */
public class ControladorPrincipal implements ActionListener{
    
    private VistaPrincipal vPrincipal;
    private VistaMensajes vMensaje;
    private SessionFactory sessionFactory;
    
    
    public ControladorPrincipal(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
        
        vPrincipal=new VistaPrincipal();
        vMensaje = new VistaMensajes();
        addListeners();
        
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setResizable(false);
        vPrincipal.setVisible(true);
    }
    
    
    private void addListeners()
    {
        vPrincipal.menuItemInicio.addActionListener(this);
        vPrincipal.menuItemMonitores.addActionListener(this);
        vPrincipal.menuItemSocios.addActionListener(this);
        vPrincipal.menuItemActividades.addActionListener(this);
        vPrincipal.menuItemSalir.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "MostrarInicio":
                muestraPanel(vPrincipal.panelInicio);
                break;
            
            case "MostrarMonitores":
                muestraPanel(vPrincipal.panelMonitores);
                break;
               
            case "MostrarSocios":
                muestraPanel(vPrincipal.panelSocios);
                break;
                
            case "MostrarActividades":
                muestraPanel(vPrincipal.panelActividades);
                break;
                
            case "SalirAplicacion":
                if(sessionFactory != null && !sessionFactory.isClosed()){
                    sessionFactory.close();
                }
                vMensaje.Mensaje(
                vPrincipal,
                "Saliendo de la aplicación",
                null,
                "info"
                );
                
                System.exit(0);
                break;
        }
    }
    
    
    private void muestraPanel(JPanel panel)
    {
        vPrincipal.panelInicio.setVisible(false);
        vPrincipal.panelMonitores.setVisible(false);
        vPrincipal.panelSocios.setVisible(false);
        vPrincipal.panelActividades.setVisible(false);
        
        panel.setVisible(true);
    }
    

}
