package civitas;

public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInmobiliarias gestion;
    
    public OperacionInmobiliaria(GestionesInmobiliarias gest, int ip)
    {
        numPropiedad = ip;
        gestion = gest;
    }
    
    public OperacionInmobiliaria()
    {
        numPropiedad = 0;
        gestion = null;
    }

    public GestionesInmobiliarias getGestion()
    {
        return gestion;
    }
    
    public int getNumPropiedad()
    {
        return numPropiedad;
    }
}
