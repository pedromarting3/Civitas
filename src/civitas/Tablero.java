package civitas;

import java.util.ArrayList;

public class Tablero 
{
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int porSalida;
    private boolean tieneJuez;

    public Tablero( int n )
    {
        if( n >= 1)
        {
            numCasillaCarcel = n;
        }
        else
        {
            numCasillaCarcel = 1;
        }
        casillas = new ArrayList<>();
        casillas.add(new Casilla("Salida"));
        porSalida = 0;
        tieneJuez = false;
    }

    private boolean correcto()
    {
        boolean respuesta = false;
        if ((casillas.size() > numCasillaCarcel) && (tieneJuez))
        {
            respuesta = true;
        }
        return respuesta;
    }

    private boolean correcto(int numCasilla)
    {
        boolean respuesta = false;
        if(correcto() && (casillas.size() >= numCasilla))
        {
            respuesta = true;
        }
        
        return respuesta;
    }
    
    int getCarcel()
    {
        return numCasillaCarcel;    
    }

    int getPorSalida()
    {
        int resultado = 0;
        if(porSalida > 0)
        {
            resultado = porSalida;
            porSalida = porSalida - 1 ; 
        }
        return resultado;
    }

    void añadeCasilla(Casilla casilla)
    {
        if(casillas.size() == numCasillaCarcel)
        {
            casillas.add(new CasillaJuez("Cárcel",numCasillaCarcel));
        }  
        
        casillas.add(casilla);
        
        if(casillas.size() == numCasillaCarcel)
        {
            casillas.add(new CasillaJuez("Cárcel",numCasillaCarcel));
        }     
    }

    void añadeJuez()
    {
        if(!tieneJuez)
        {
            casillas.add(new CasillaJuez("Juez",numCasillaCarcel));
            tieneJuez = true;
        }
       
    }

    Casilla getCasilla(int numCasilla)
    {
        if(correcto(numCasilla))
        {
           
            return casillas.get(numCasilla);
            
        }
        else
        {
            return null;
        } 
    }

    int nuevaPosicion(int actual, int tirada)
    {
        if (correcto())
        { 
           porSalida += (actual + tirada)/casillas.size(); 
           return (actual+tirada)%casillas.size();
            
        }
           return -1;
    }

    int calcularTirada (int origen, int destino)
    {
        int aux = destino - origen;
        if((aux) <= 0)
        {
            return (casillas.size() + aux);
        }
        else
        {
            return aux;
        }
    }
    
    public int size(){
        return casillas.size();
    }
}
