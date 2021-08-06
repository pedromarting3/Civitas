package civitas;


public class TituloPropiedad {
    
    private static final float factorInteresesHipoteca = 1.1f;
    
    private float alquilerBase;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    
    private Jugador propietario;
    

    TituloPropiedad(String nom, int alquiler_base, int factor_revalorizacion, int precio_base_hipo, int precio_compra, int precio_edificar)
    {
        
        nombre = nom;
        alquilerBase = alquiler_base;
        factorRevalorizacion = factor_revalorizacion;
        hipotecaBase = precio_base_hipo;
        precioCompra = precio_compra;
        precioEdificar = precio_edificar;
        propietario = null;
        numCasas = 0;
        numHoteles = 0;
        hipotecado = false;
        
    }
    
    void actualizaPropietarioPorConversion(Jugador jugador)
    {      
        propietario = jugador;
    }
   
    public String getNombre()
    {
        
        return nombre;
        
    }
    
    float getPrecioCompra()
    {
        
        return precioCompra;
        
    }
    
    boolean tienePropietario()
    {
        
        return propietario!=null;
        
    }
    
    int getNumCasas()
    {
        return numCasas;
    }
    
    int getNumHoteles()
    {
        return numHoteles;
    }
    
    float getPrecioEdificar()
    {
        return precioEdificar;
    }
    
    Jugador getPropietario(){
        
        return propietario;
    }
    
    public boolean getHipotecado()
    {
        
        return true;
        
    }
    
    boolean comprar(Jugador jugador)
    {
        
        boolean result=false;
        if(!tienePropietario())
        {
            propietario=jugador;
            result=true;
            propietario.paga(precioCompra);
        }
        return result;
    
    }
    
    boolean hipotecar(Jugador jugador)
    {
        boolean salida=false;
        if(! hipotecado && esEsteElPropietario(jugador))
        {
            propietario.recibe(getImporteHipoteca());
            hipotecado=true;
            salida=true;
        }
        return salida;

    }
  
    boolean construirCasa(Jugador jugador){
        
        boolean result = false;
        if(esEsteElPropietario(jugador))
        {
            propietario.paga(precioEdificar);
            result = true;
            numCasas++;
        }
        
        return result;
        
    }
   
    boolean construirHotel(Jugador jugador){
        
        boolean result=false;
        
        if(esEsteElPropietario(jugador))
        {
            propietario.paga(precioEdificar);
            numHoteles++;
            result=true;
        }
               
        return result;
    }
    
    @Override
    
    public String toString()
    {
        String HIPOTECADO = "No";
        if(hipotecado)
        {
            
            HIPOTECADO = "Si";
            
        }
        
        return "\nNombre: " + nombre + "\nAlquiler Base: " + alquilerBase + "\nFactor de Revalorizacion: " + 
                factorRevalorizacion + "\nHiopoteca Base: " + hipotecaBase + "\n¿Esta Hipotecado?: " + HIPOTECADO +
                "\nNumero de Casas: " + numCasas + "\nNumero de Hoteles: " + numHoteles + "\nPrecio de Compra: " +
                precioCompra + "\nPrecio por Edificar: " + precioEdificar;
        
    }        
    
    boolean propietarioEncarcelado()
    {
        
        boolean respuesta = false;
        
        
        if (tienePropietario())
        {
            if (propietario.encarcelado)
            {
                respuesta = true;
            }
        }
        else
        {
            respuesta = false;
        }

        return respuesta;
    }
    
    
    float getPrecioAlquiler()
    {
        
        float resultado;
        
        if(!hipotecado && !propietario.encarcelado)
        {
            
            resultado = alquilerBase*(1+(numCasas*0.5f)+(numHoteles*2.5f));
            
        }
        else
        {
            
            resultado = 0;
            
        }
        
        return resultado;
        
    }
    
    public float getImporteHipoteca()
    {
        
        return (hipotecaBase*(1+(numCasas*0.5f)+(numHoteles*2.5f)));
        
    }
    
    boolean cancelarHipoteca(Jugador jugador)
    {
        
        boolean result = false;
        if(hipotecado)
        {
            if(esEsteElPropietario(jugador))
            {
                propietario.paga(getImporteCancelarHipoteca());
                hipotecado = false;
                result = true;
            }
        }
        
        return result;
    }
    
    float getImporteCancelarHipoteca()
    {
        
        return( this.getImporteHipoteca() * factorInteresesHipoteca);
        
    }
    
    boolean esEsteElPropietario(Jugador jugador)
    {
        
        boolean respuesta = false;
        
        if(jugador == propietario)
        {
            
            respuesta = true;
            
        }
        
        return respuesta;
        
    }
    
    void tramitarAlquiler(Jugador jugador)
    {
        
        if(tienePropietario() && !esEsteElPropietario(jugador))
        {
            
            float precio = getPrecioAlquiler();
            jugador.pagaAlquiler(precio);
            propietario.recibe(precio);
            
        }
        
    }
    
    int cantidadCasasHoteles()  
    {
        
        return(numCasas + numHoteles);
        
    }
    
    float getPrecioVenta()
    {
        
        return precioCompra+((cantidadCasasHoteles() * precioEdificar) * factorRevalorizacion );
        
    }
    
    boolean derruirCasas(int n, Jugador jugador)
    {
        
        boolean operacion_realizada = false;
        
        if(esEsteElPropietario(jugador) && numCasas >= n)   
        {
            
            numCasas = numCasas - n;
            operacion_realizada = true;
            
        }
        
        return operacion_realizada;
        
    }
    
    boolean vender(Jugador jugador)
    {
        
        Boolean respuesta = false;
        
        if(esEsteElPropietario(jugador) && !hipotecado)
        {
            
            propietario.recibe(this.getPrecioVenta());
            propietario = null;
            numCasas = 0;
            numHoteles = 0;
            respuesta = true;
            
        }
        
        return respuesta;
        
    }
        
}