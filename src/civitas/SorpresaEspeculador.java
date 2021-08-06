package civitas;

import java.util.ArrayList;

public class SorpresaEspeculador extends Sorpresa{
    
    private int valor;
    
    SorpresaEspeculador(int fianza){
        super("El jugador se ha convertido en un jugador especulador");
        valor = fianza;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            todos.set(actual, new JugadorEspeculador(todos.get(actual),valor));
        }
    }
    
    @Override    
    public String toString()
    {
        return super.toString() +"Especulador \nFianza: "+valor;   
    } 
}