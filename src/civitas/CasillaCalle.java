package civitas;

import java.util.ArrayList;

public class CasillaCalle extends Casilla
{
    /*Creamos una clase que extiende a la clase padre (Casilla)*/
    private TituloPropiedad titulo;

    /*Creamos un constructor para la clase*/
    CasillaCalle(TituloPropiedad titulo)
    {
        super(titulo.getNombre()); //Se hace uso del constructor de la clase padre
        this.titulo = titulo;
    }
    
    /*Metodo que devuelve el titulo*/
    TituloPropiedad getTituloPropiedad()
    {
        return titulo;
    }  

    /*Metodo que recibe el jugador*/
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
        if (jugadorCorrecto(actual,todos))
        {
            informe(actual, todos);
            Jugador jugador = todos.get(actual);
            
            if (!titulo.tienePropietario())
            {
                jugador.puedeComprarCasilla();
            }
            else
            {
                titulo.tramitarAlquiler(jugador);
            }
        }
    }  

    /*Metodo string que hace uso del string de la clas padre (Casilla)*/
    @Override
    public String toString(){
        String salida = super.toString() + "\nTipo: CALLE";
                
        if (titulo != null)
        {
            salida += "\nTitulo de propiedad: " + titulo.toString();
        }
                
        return salida;
    }


}
