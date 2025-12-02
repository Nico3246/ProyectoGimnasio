/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtil;
import org.hibernate.SessionFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Vista.VistaConexion;
import Vista.VistaMensajes;
/**
 *
 * @author Nicolás
 */
public class ControladorConexion implements ActionListener{
    
    private VistaConexion vConexion;
    private VistaMensajes vMensaje;
    private SessionFactory sessionFactory;
    private String user;
    private String pass;
    
    public ControladorConexion() {//Se crea una única SessionFactory para establecer la conexión con la BD, y se mantiene abierta hasta que termina la aplicación 
        
        vConexion = new VistaConexion();
        vMensaje = new VistaMensajes();
        
        addListeners();
        
        vConexion.pack();//ajusta el tamaño
        vConexion.setLocationRelativeTo(null); //centra la ventana
        vConexion.setVisible(true); // la muestra
        vConexion.setResizable(false); // evita redimensionar
    }
    
    private void addListeners()
    {
        vConexion.BotonEntrar.addActionListener(this);
        vConexion.BotonCancelar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "EntrarAplicacion":
                
                //leer usuario y contraseña
                user = vConexion.CuadroUsuario.getText().trim();
                pass = new String(vConexion.CuadroContrasena.getPassword());
                
                try
                {
                    //creamos la sesion con el usuario y la contraseña
                    sessionFactory = HibernateUtil.buildSessionFactory(user,pass);
                    
                    if(sessionFactory != null && !sessionFactory.isClosed())
                    {
                        vMensaje.Mensaje(
                            vConexion,
                            "Conexion correcta con la base de datos",
                            null,
                            "info"
                        );
                        
                        //cerrar ventana de conexion y abrir la principal
                        vConexion.dispose();
                        new ControladorPrincipal(sessionFactory);
                    }
                    else
                    {
                        vMensaje.Mensaje(
                            vConexion,
                            "No se ha podido establecer la conexion",
                            null,
                            "error"
                        );
                    }
                    
                    
                }catch (Exception ex)
                {
                    vMensaje.Mensaje(
                        vConexion,
                        "Error al conectar: " + ex.getMessage(),
                        null,
                        "error"
                    );
                }
                
                break;
            
            case "Cancelar":
                vConexion.dispose();
                System.exit(0);
                break;
                    
        }
    }
    
}
