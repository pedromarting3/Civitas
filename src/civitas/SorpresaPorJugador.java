package civitas;

import java.util.ArrayList;

public class SorpresaPorJugador extends Sorpresa
{
    private int valor;
    
    SorpresaPorJugador (String texto,int valor)
    {
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        informe(actual, todos);
        SorpresaPagarCobrar cobrar, pagar;
        String txt = "Aplicamos sorpresa JugadorPorJugador\n";

        if (valor > 0){
            cobrar = new SorpresaPagarCobrar(txt, -valor);
            for (int i = 0; i < todos.size(); i++){
                if (i != actual){
                    cobrar.aplicarAJugador(i, todos);
                }
            }

            pagar = new SorpresaPagarCobrar(txt, valor*3);
            pagar.aplicarAJugador(actual, todos);
        }
        else {
            cobrar = new SorpresaPagarCobrar(txt, valor*-3);
            cobrar.aplicarAJugador(actual, todos);

            pagar = new SorpresaPagarCobrar(txt, valor);
            for (int i = 0; i < todos.size(); i++){
                if (i != actual){
                    pagar.aplicarAJugador(i, todos);
                }
            }
        }
    }        
    
    @Override    
    public String toString()
    {
        return super.toString() +"Por Jugador \nValor: " + valor;   
    }
}