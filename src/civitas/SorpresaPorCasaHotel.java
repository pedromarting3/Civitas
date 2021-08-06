package civitas;

import java.util.ArrayList;


public class SorpresaPorCasaHotel extends Sorpresa
{
    private int valor;
    
    SorpresaPorCasaHotel (String texto,int valor)
    {
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos)
    {
        if(jugadorCorrecto(actual, todos))
        {
            informe(actual, todos);
            todos.get(actual).modificarSaldo(valor*(todos.get(actual).cantidadCasasHoteles()));   
        }
    }
    
    @Override    
    public String toString()
    {
        return super.toString() +"Por Casa Hotel \nValor: " + valor;   
    }
}