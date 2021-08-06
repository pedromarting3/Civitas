package civitas;

import java.util.ArrayList;

public class SorpresaPagarCobrar extends Sorpresa{
    
    /*Necesitamos el atributo valor para hacer operaciones*/
    private int valor;
    
    SorpresaPagarCobrar(String texto , int valor)
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
            todos.get(actual).modificarSaldo(valor);
        }
    }
    
    @Override    
    public String toString()
    {
        return super.toString() +"Pagar Cobrar \nValor: " + valor;   
    }
}