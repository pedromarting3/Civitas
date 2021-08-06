package civitas;

import java.util.ArrayList;

public class CasillaImpuesto extends Casilla
{

    //Creamos el importe de la casilla impuesto
    private float importe;
    
    //Constructor 
    CasillaImpuesto (String nombre , float cantidad){
        super(nombre);
        importe = cantidad;
    }
    
    //Metodo recibeJugador de casilla
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
        
        if (jugadorCorrecto(actual, todos))
        {
            informe(actual, todos);
            todos.get(actual).pagaImpuesto(importe);
        }
    }
    
    float getImporte()
    {
        return importe;
    }
    
    /*Metodo string que hace uso del string de la clas padre (Casilla)*/
    @Override
    public String toString(){
        String salida = super.toString() + "\nTipo: CALLE";
                
        if (importe != 0)
        {
            salida += "\nImporte: " + importe;
        }
                
        return salida;
    }

}