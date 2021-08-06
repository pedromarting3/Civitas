package civitas;

import java.util.ArrayList;

public class CasillaJuez extends Casilla
{
    //Definimos de nuevo el numero de la casilla de carcel
    private static int carcel = 5;
    
    CasillaJuez(String nombre , int numCasillaCarcel)
    {
        super(nombre);//Se hace uso del metodo de la clase padre
        carcel = numCasillaCarcel;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos )
    {
        if (jugadorCorrecto(actual, todos))
        {
            informe(actual, todos);
            todos.get(actual).encarcelar(carcel);
        }
    }
    
    static int getCarcel()
    {
        return carcel;
    }
    
    /*Metodo string que hace uso del string de la clas padre (Casilla)*/
    @Override
    public String toString(){
        String salida = super.toString() + "\nTipo: JUEZ";
                
        if (carcel != -1)
        {
            salida += "\nCasilla carcel: " + carcel;
        }
                
        return salida;
    }

}