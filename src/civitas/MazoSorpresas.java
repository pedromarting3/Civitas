package civitas;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class MazoSorpresas
{
    private ArrayList<Sorpresa> sorpresas;
    private Boolean barajada;
    private int usadas;
    private Boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;

    private void init()
    {
        sorpresas = new ArrayList<>();
        cartasEspeciales = new ArrayList<>();
        barajada = false;
        usadas = 0;
    }

    MazoSorpresas(Boolean d)
    {
        
        debug = d;
        init();
        if(debug)
        {
            Diario.getInstance().ocurreEvento("El debug está activado");
        }
    }
    
    MazoSorpresas()
    {
        init();
        debug = false;
    }
    void alMazo(Sorpresa s)
    {
        if (!barajada)
        {
            sorpresas.add(s);
        }
    }

    Sorpresa siguiente()
    {
        if(!barajada || (usadas == sorpresas.size()))
        {
            if(!debug)
            {
                Collections.shuffle(sorpresas);
                usadas = 0;
                barajada = true;
            }

        
        }
        usadas = usadas + 1;
        ultimaSorpresa = sorpresas.get(0);
        sorpresas.remove(0); 
        sorpresas.add(ultimaSorpresa); 
            
            
           
        return ultimaSorpresa;
        
    }

    void inhabilitarCartaEspecial (Sorpresa sorpresa)
    {
        if (sorpresas.contains(sorpresa))
        {
            sorpresas.remove(sorpresa);
            cartasEspeciales.add(sorpresa);
        }
        
        Diario.getInstance().ocurreEvento("Se ha inhabilitado la carta y se ha pasado al mazo especial ");
    }

    void habilitarCartaEspecial(Sorpresa sorpresa)
    {
        if (cartasEspeciales.contains(sorpresa))
        {
            cartasEspeciales.remove(sorpresa);
            sorpresas.add(sorpresa);
        }
       
        Diario.getInstance().ocurreEvento("Se ha habilitado la carta ");
    }
}


