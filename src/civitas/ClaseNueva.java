//INICIO DE EXAMEN
package civitas;

import java.util.ArrayList;
import java.util.Random;

public class ClaseNueva extends CivitasJuego
{

    private int indiceJugadorActual;
    
    // Atributos de referencia
    private ArrayList<Jugador> jugadores;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    private Tablero tablero;
    private MazoSorpresas mazo;

    Random numeroAleatorio = new Random();

    int n = numeroAleatorio.nextInt(1);

    public ClaseNueva(ArrayList<String> nombres)
    {

        super(nombres);

    }

    @Override
    public boolean construirCasa(int ip)
    {
        if(n == 0)
        {

            return super.construirCasa(ip);

        }
        else
        {

            return (super.construirCasa(ip) && super.construirCasa(ip));

        }
    }

    public boolean salirCobrar()
    {
        boolean operacion = true;
        if(super.salirCarcelPagando())
        {

            jugadores.get(indiceJugadorActual).recibe(3 *jugadores.get(indiceJugadorActual).getPrecioLibertad());


        }
        else
        {

            operacion = false;

        }
        return operacion;
    }




}
//FIN DE EXAMEN