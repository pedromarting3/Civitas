package civitas;

import java.util.ArrayList;

public class Casilla {
    
    // Atributo de clase
    //private static int carcel;
    
    // Atributos de instancia
    //private float importe;
    private String nombre;
    
    // Atributos de referencia
    //private MazoSorpresas mazo;
    //private Sorpresa sorpresa;
    //private TituloPropiedad tituloPropiedad;
    //private TipoCasilla tipo;
    
    //Constructor para descanso    
    Casilla(String n)
    {
        init();
        nombre=n;
        //tipo=TipoCasilla.DESCANSO;
    }
    
    /*
    //Constructor para calle
    Casilla(TituloPropiedad titulo)
    {
        init();
        nombre=titulo.getNombre();
        tipo=TipoCasilla.CALLE;
        importe=titulo.getPrecioCompra();
        tituloPropiedad = titulo;
    }
    //Constructor para impuesto
    Casilla(float cantidad, String n)
    {
        init();
        nombre=n;
        tipo=TipoCasilla.IMPUESTO;
        importe=cantidad;   
    }
    //Constructor para juez
    Casilla(int numCasillaCarcel, String n)
    {
        init();
        nombre=n;
        tipo=TipoCasilla.JUEZ;
        carcel=numCasillaCarcel;
    }
    //Constructor para sorpresa
    Casilla(MazoSorpresas mazo, String nombre)
    {
        init();
        this.nombre=nombre;
        tipo=TipoCasilla.SORPRESA;
        this.mazo=mazo;
    }
    */

    public String getNombre()
    {
        return nombre;
    }
    /*
    public static int getCarcel()
    {
        return carcel;
    }
    
    TituloPropiedad getTituloPropiedad()
    {
        return tituloPropiedad;
    }
    */
    
    protected void informe(int iactual, ArrayList<Jugador> todos)
    {
        if (jugadorCorrecto(iactual, todos))
        {
            Diario.getInstance().ocurreEvento("El jugador "+todos.get(iactual).getNombre()+" se encuentra en la casilla: "+ nombre);
            toString();
        }
    }
    
    private void init()
    {
        nombre="";
        //importe=0f;    
        //tituloPropiedad = null;
        //mazo = null;
        //sorpresa = null;         
    }
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos)
    {
        boolean correcto=false;
        
        if (iactual >= 1 && iactual<=todos.size())
        {
            correcto=true;
        }
        
        return correcto;
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos)
    {
        /*switch(tipo)
        {
            case CALLE:
                recibeJugador_calle(iactual,todos);
                break;
            case IMPUESTO:
                recibeJugador_impuesto(iactual,todos);
                break;
            case JUEZ:
                recibeJugador_juez(iactual,todos);
                break;
            case SORPRESA:
                recibeJugador_sorpresa(iactual,todos);
                break;
            default:
                informe(iactual,todos);
                        
        }
        */
        
        informe(iactual,todos);
    }
    
    /*
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos)
    {
        if (jugadorCorrecto(iactual,todos))
        {
            informe(iactual, todos);
            Jugador jugador = todos.get(iactual);
            
            if (!tituloPropiedad.tienePropietario())
            {
                jugador.puedeComprarCasilla();
            }
            else
            {
                tituloPropiedad.tramitarAlquiler(jugador);
            }
        }
       
    }
    
    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos)
    {
        if (jugadorCorrecto(iactual, todos))
        {
            informe(iactual, todos);
            todos.get(iactual).pagaImpuesto(importe);
        }
    }
    
    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos)
    {
        if (jugadorCorrecto(iactual, todos))
        {
            informe(iactual, todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }
    
    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos)
    {
        if (jugadorCorrecto(iactual,todos))
        {
            sorpresa = mazo.siguiente();
            informe(iactual,todos);
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }
    */
    @Override
    public String toString()
    {
        String cad = "\nCasilla: "+nombre;
        
        /*
        if(tipo == TipoCasilla.IMPUESTO)
        {
            cad +="\nImporte: " + importe;
            
        }
        */
        
        return cad;
 
    }    
    public static void main(String[]args)
    {
        Casilla c = new Casilla("Parking");
        
        System.out.println(c.toString());
        
    }
}
