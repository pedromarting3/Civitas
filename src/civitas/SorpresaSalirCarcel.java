package civitas;

import java.util.ArrayList;

public class SorpresaSalirCarcel extends Sorpresa
{
    private MazoSorpresas mazo;
    
    SorpresaSalirCarcel (MazoSorpresas mazo)
    {
        super("¡Has salido de prision!");
        this.mazo = mazo;
    }
    
    void salirDelMazo()
    {
        mazo.inhabilitarCartaEspecial(this);  
    }
    
    void usada()
    {
        mazo.habilitarCartaEspecial(this);
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos)
    {
        
        if(jugadorCorrecto(actual, todos))
        {
            boolean haySalvoconducto = false;
            informe(actual, todos);
            
            for(int i = 0 ; i < todos.size() ; i++)
            {
                if (todos.get(i).tieneSalvoconducto())
                    haySalvoconducto = true;
            }
            
            if(!haySalvoconducto)
            {
                todos.get(actual).obtenerSalvoconducto(this);
                salirDelMazo();
            } 
        }
    }
    
    @Override    
    public String toString()
    {
        return super.toString() +"SalirCarcel";   
    }
}