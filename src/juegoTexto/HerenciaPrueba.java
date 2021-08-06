//INICIO EXAMEN
package juegoTexto;

import civitas.CivitasJuego;
import civitas.Dado;
import java.util.ArrayList;

public class HerenciaPrueba
{
    
    public static void main (String args[]){
        
        //Pasos:
	//1.Creamos Jugadores 2.Seleccionamos el modo debuj 3.Comenzamos a jugar
        ArrayList<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Range Rover");
        nombresJugadores.add("Tesla");
        
        
        CivitasJuego juego = new CivitasJuego(nombresJugadores);
        Dado.getInstance().setDebug(true);
        
        VistaTextual interfaz = new VistaTextual();
        interfaz.setCivitasJuego(juego);
        Controlador controlador = new Controlador(juego,interfaz);
        controlador.juega();
    }
}
//FIN DE EXAMEN