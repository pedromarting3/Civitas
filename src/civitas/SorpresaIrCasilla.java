package civitas;

import java.util.ArrayList;

public class SorpresaIrCasilla extends Sorpresa{
    
    /*Necesitamos un tablero y valor para la clase*/
    private Tablero tablero;
    private int valor;
    
    SorpresaIrCasilla (Tablero tablero, int valor, String texto)
    {
        super(texto);
        this.tablero = tablero;
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos)
    {
        if(jugadorCorrecto(actual, todos))
        { 
            informe(actual, todos);
            int casact = todos.get(actual).getNumCasillaActual();
            int tirada = tablero.calcularTirada(casact, valor);
            int nuevapos = tablero.nuevaPosicion(casact, tirada);
            todos.get(actual).moverACasilla(nuevapos);
            tablero.getCasilla(nuevapos).recibeJugador(actual, todos);
        }
    }
    
    @Override    
    public String toString()
    {
        return super.toString() +"Ir Casilla \nCasilla: " + valor;   
    }
}