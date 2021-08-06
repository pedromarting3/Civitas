package juegoTexto;

import civitas.CivitasJuego;
import civitas.Dado;
import java.util.ArrayList;

public class Test 
{
    
    public static void main (String args[]){
        
        //Pasos:
	//1.Creamos Jugadores 2.Seleccionamos el modo debuj 3.Comenzamos a jugar
        ArrayList<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Alex");
        nombresJugadores.add("Rafa");
        nombresJugadores.add("Carlos");
        nombresJugadores.add("Antonio");
        
        CivitasJuego juego = new CivitasJuego(nombresJugadores);
        Dado.getInstance().setDebug(true);
        
        VistaTextual interfaz = new VistaTextual();
        interfaz.setCivitasJuego(juego);
        Controlador controlador = new Controlador(juego,interfaz);
        controlador.juega();
    }
}
