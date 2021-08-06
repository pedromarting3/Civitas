package civitas;

import java.util.ArrayList;

public class CasillaSorpresa extends Casilla
{
    MazoSorpresas mazo;
    Sorpresa sorpresa;
    
    CasillaSorpresa(String nombre , MazoSorpresas mazo)
    {
        super(nombre);//Hacemos uso del metodo de la clase padre
        this.mazo = mazo;
        sorpresa = null;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos )
    {   
        if (jugadorCorrecto(actual,todos))
        {
            sorpresa = mazo.siguiente();
            informe(actual,todos);
            sorpresa.aplicarAJugador(actual, todos);
        }
    }
    
    Sorpresa getSorpresa(){
        return sorpresa;
    }
    
    /*Metodo string que hace uso del string de la clas padre (Casilla)*/
    @Override
    public String toString(){
        String salida = super.toString() + "\nTipo: SORPRESA";
                
        if (sorpresa != null)
        {
            salida += "\nSorpresa: " + sorpresa.toString();
        }
                
        return salida;
    }
}