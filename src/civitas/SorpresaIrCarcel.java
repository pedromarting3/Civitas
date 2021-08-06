package civitas;

import java.util.ArrayList;

public class SorpresaIrCarcel extends Sorpresa
{

    /*Creamos los atributos necesarios de la clase*/
    
    private Tablero tablero;
    private static int valor;

    SorpresaIrCarcel(Tablero tablero) 
    {
        super("Dirijase a prision");
        this.tablero = tablero;
        valor = tablero.getCarcel();
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos)
    {
        if(jugadorCorrecto(actual,todos))
        {
            informe(actual, todos);
            todos.get(actual).encarcelar(valor);
        }
    }
    
    @Override    
    public String toString()
    {
        return super.toString() +"IrCarcel \nCasilla carcel: "+valor;   
    }
}