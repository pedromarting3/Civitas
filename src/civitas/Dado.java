package civitas;

import java.util.Random;

public class Dado {
    
    static final private Dado instance = new Dado();
    static final private int SALIDACARCEL=5;
    
    private int ultimoResultado;
    private Boolean debug;
    private Random rand;

    Dado()
    {
        rand = new Random();
        ultimoResultado=0;
        debug = false; 
    }
      
    static public Dado getInstance()
    {
        return instance;
    }
    
    public int tirar(){
        int auxiliar;
        if (!debug)
        {
            auxiliar = 1;
        }
        else
        {
            int val = rand.nextInt(6-1) + 1;
            auxiliar = val;
        }
        ultimoResultado = auxiliar;
        return ultimoResultado;   
    }
    
    public Boolean salgoDeLaCarcel()
    {
        return (ultimoResultado >= SALIDACARCEL);
    } 

    public int quienEmpieza(int n)
    {
        int val = rand.nextInt(n);
        return val;
    }

    public void setDebug(Boolean d)
    {
        debug = d;
        if(debug)
        {
            Diario.getInstance().ocurreEvento("El debug está activado");
        }
        else
        {
            Diario.getInstance().ocurreEvento("El debug está desactivado");
        }   
    }

    public int getUltimoResultado()
    {
        return ultimoResultado;
    }
}