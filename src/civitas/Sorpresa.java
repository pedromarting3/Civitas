package civitas;

import java.util.ArrayList;

public abstract class Sorpresa {
    
    protected String texto;
    
    Sorpresa(String text)
    {
        texto = text;
    }
    
    abstract void aplicarAJugador(int actual , ArrayList<Jugador> todos);
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos)
    {
        return (actual >= 0 && actual < todos.size()); 
    }
    
    protected void informe(int actual, ArrayList<Jugador> todos)
    {
        Diario.getInstance().ocurreEvento("Se ha aplicado la sorpresa a " + todos.get(actual).getNombre());   
    }
           
    @Override    
    public String toString()
    {
        return "\nSorpresa ";
    }  
}
