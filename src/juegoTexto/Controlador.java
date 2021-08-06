package juegoTexto;

import civitas.CivitasJuego;
import civitas.ClaseNueva;
import civitas.OperacionesJuego;
import civitas.OperacionInmobiliaria;
import civitas.GestionesInmobiliarias;
import civitas.SalidasCarcel;
import civitas.Respuestas;
import civitas.Jugador;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

import org.graalvm.compiler.lir.phases.EconomyPostAllocationOptimizationStage;
public class Controlador 
{
     
    //Atributos de instancia
    private CivitasJuego juego;
    //INICIO EXAMEN
    private ClaseNueva juegonuevo;
    //FIN EXAMEN
    private VistaTextual vista = new VistaTextual();
    
    //Constructor
    Controlador(CivitasJuego juego, VistaTextual vista){
        this.juego = juego;
        this.vista = vista;
    }    
    
    public void juega()
    {
        vista.setCivitasJuego(juego);
        while(!juego.finalDelJuego())
        {
            vista.actualizarVista();
            vista.pausa();
            
            OperacionesJuego operacion = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            System.out.println("Siguiente operacion: ");
            vista.mostrarSiguienteOperacion(operacion);
            if(operacion!=OperacionesJuego.PASAR_TURNO)
            {
                vista.mostrarEventos();
            }
            if(!juego.finalDelJuego())
            {
                switch(operacion)
                {
                    case COMPRAR:
                        if(vista.comprar()==Respuestas.SI)
                        {
                            juego.comprar();
                        }
                        break;
                        
                    case GESTIONAR:
                        Boolean salir = false;
                        //do
                        
                            vista.gestionar();
                            int gestion=vista.getGestion();
                            int propiedad=vista.getPropiedad();
                            OperacionInmobiliaria operacion_nueva = new OperacionInmobiliaria(GestionesInmobiliarias.values()[gestion], propiedad);
                            switch(operacion_nueva.getGestion())
                            {
                                case VENDER:
                                    juego.vender(operacion_nueva.getNumPropiedad());
                                    break;

                                case HIPOTECAR:
                                    juego.hipotecar(operacion_nueva.getNumPropiedad());
                                    break;

                                case CANCELAR_HIPOTECA:
                                    juego.cancelarHipoteca(operacion_nueva.getNumPropiedad());
                                    break;

                                case CONSTRUIR_CASA:
                                    juego.construirCasa(operacion_nueva.getNumPropiedad());
                                    break;

                                case CONSTRUIR_HOTEL:
                                    juego.construirHotel(operacion_nueva.getNumPropiedad());
                                    break;

                                case TERMINAR:
                                    salir=true;                              
                                    juego.siguientePaso(); 

                            }
                        
                            //while(!salir);
                            break;
                    case SALIR_CARCEL:
                        //INICIO DE EXAMEN
                        if(vista.salirCarcel()==SalidasCarcel.PAGANDO) 
                        {
                            juego.salirCarcelPagando();
                        }
                        if(vista.salirCarcel()==SalidasCarcel.TIRANDO)
                        {
                            juego.salirCarcelTirando();
                        }
                        else
                        {

                            juegonuevo.salirCobrar();

                        }

                        
                        
                        juego.siguientePaso();
                        //FIN DE EXAMEN
                }
              
            }
            else
            {
                ArrayList<Jugador> jugadores =(juego.ranking());
                System.out.println("---------------FIN DEL JUEGO---------------\n");
                System.out.println("Posiciones: \n");
                for(int i = 0; i < jugadores.size(); i++)
                {
                    
                    System.out.println(i +"." +jugadores.get(i).getNombre() + " con: " + jugadores.get(i).getSaldo());
                }
            }
                 
        }
      
    }   
   
}



