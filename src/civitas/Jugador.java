package civitas;

import java.util.ArrayList;

public class Jugador implements Comparable<Jugador>{
    
    // Atributos de clase
    protected static int CasasMax = 4;
    protected static int CasasPorHotel = 4;
    protected static int HotelesMax = 4;
    protected static float PasoPorSalida = 1000.0f; 
    protected static float PrecioLibertad = 200.0f;
    private static final float SaldoInicial = 7500.0f;
    
    // Atributos de instancia
    protected boolean encarcelado;
    private String nombre;
    private int numCasillaActual;
    private boolean puedeComprar;
    private float saldo;
    
    // Atributos de referencia
    protected ArrayList<TituloPropiedad> propiedades; 
    protected SorpresaSalirCarcel salvoconducto; 
    
    // Constructores
    Jugador(String n)
    {
        nombre=n;
        encarcelado=false;
        numCasillaActual=0;
        puedeComprar=false;
        saldo=SaldoInicial;
        propiedades = new ArrayList<>();
        salvoconducto = null;
    }
    
    protected Jugador (Jugador otro)
    {
        this.encarcelado = otro.encarcelado;
        this.nombre = otro.nombre;
        this.numCasillaActual = otro.numCasillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = otro.propiedades;
        this.salvoconducto = otro.salvoconducto;  
    }
    
    
    // En vez de privado, lo pongo de paquete porque lo necesito para el metodo
    //cancelarHipoteca de CivitasJuego
    boolean cancelarHipoteca(int ip)
    {
        boolean result=false;
        if(!encarcelado)
        {
            if(existeLaPropiedad(ip))
            {
               TituloPropiedad propiedad=propiedades.get(ip);
               float cantidad=propiedad.getImporteCancelarHipoteca();
               boolean puedoGastar=puedoGastar(cantidad);
               if(puedoGastar)
               {
                   result=propiedad.cancelarHipoteca(this);
                                      
                   if(result)
                   {
                       Diario.getInstance().ocurreEvento("El jugador "+nombre+" cancela la hipoteca de la propiedad "+ip);
                   }  
               }
            }
        }
        
        return result;
    }
    
    int cantidadCasasHoteles()
    {
        int total=0;
        
        for(int i=0; i<propiedades.size(); i++)
        {
            total =+ propiedades.get(i).cantidadCasasHoteles();
           
        }
        
        return total;
    }
    
    @Override
    public int compareTo(Jugador otro)
    {
        int resultado = Float.compare(otro.saldo, saldo);
        return resultado;
    }
    //Hemos modificado esto después de la entrega ya que era incorrecto
    boolean comprar(TituloPropiedad titulo)
    {
        boolean result=false;
            if(!encarcelado)
            {
                if(puedeComprar)
                {
                    float precio=titulo.getPrecioCompra();
                    if(puedoGastar(precio))
                    {
                        result=titulo.comprar(this);
                        if(result)
                        {
                            propiedades.add(titulo);
                            Diario.getInstance().ocurreEvento("El jugador"+this+ " compra la propiedad "+titulo.toString());
                        }
                        puedeComprar=false;
                    }
                }
            }
        return result;
    }
    
    boolean construirCasa(int ip)
    {
        boolean result = false;
        boolean puedoEdificarCasa = false;
        
        if (!encarcelado)
        {
            boolean existe = existeLaPropiedad(ip);
            
            if (existe){
                TituloPropiedad propiedad = propiedades.get(ip);
                puedoEdificarCasa = puedoEdificarCasa(propiedad);
                float precio = propiedad.getPrecioEdificar();
                if (puedoGastar(precio) && puedoEdificarCasa)
                {
                    result = propiedad.construirCasa(this);
                    Diario.getInstance().ocurreEvento( nombre + " construye casa en la propiedad " + ip);
                }      
            }
        }
        
        return result;
    }
    
    boolean construirHotel(int ip)
    {
        boolean result=false;
        if(!encarcelado)
        {
            if(existeLaPropiedad(ip))
            {
                TituloPropiedad propiedad=propiedades.get(ip);
                boolean puedoEdificarHotel=puedoEdificarHotel(propiedad);
                float precio=propiedad.getPrecioEdificar();
                
                if(puedoGastar(precio) && puedoEdificarHotel && propiedad.getNumCasas()>=getCasasPorHotel())
                {
                       puedoEdificarHotel=true;
                }
                
                if(puedoEdificarHotel)
                {
                    result = propiedad.construirHotel(this);
                    int casasPorHotel = getCasasPorHotel();
                    propiedad.derruirCasas(casasPorHotel,this);
                    Diario.getInstance().ocurreEvento("El jugador "+nombre+ " construye hotel en la propiedad "+ip);
                }
            }
        }
        
        return result;
    }
    
    protected boolean debeSerEncarcelado()
    {
        boolean respuesta=false;
        
        if (!encarcelado)
        {
            if (!tieneSalvoconducto())
            {
                respuesta=true;
            }
            else
            {
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador "+nombre+" se libra de la carcel");
                respuesta = false;
            }
        }
        
        return respuesta;
    }
    
    boolean enBancarrota()
    {
        return (saldo == 0 && propiedades.isEmpty());
    }
            
    boolean encarcelar(int numCasillaCarcel)
    {
        if (debeSerEncarcelado())
        {
            moverACasilla(numCasillaCarcel);
            encarcelado=true;
            Diario.getInstance().ocurreEvento("El jugador: "+nombre+" ha sido encarcelado");
        }
        return encarcelado;
    }
    
    private boolean existeLaPropiedad(int ip)
    {
        boolean respuesta = false;
        if(!propiedades.isEmpty())
        {
            if(propiedades.get(ip).getPropietario() == this)
            {
                respuesta = true;
            }
        }
        
        return respuesta;
    }
    
    //Métodos get
    protected int getCasasMax()
    {
        return CasasMax;
    }
    
    int getCasasPorHotel()
    {
        return CasasPorHotel;
    }
    
    protected int getHotelesMax()
    {
        return HotelesMax;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    int getNumCasillaActual()
    {
        return numCasillaActual;
    }
    
    public float getPrecioLibertad()
    {
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida()
    {
        return PasoPorSalida;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades()
    {
        return propiedades;
    }
    
    boolean getPuedeComprar()
    {
        return puedeComprar;
    }
    
    public float getSaldo()
    {
        return saldo;
    }
    
    public boolean isEncarcelado()
    {
        return encarcelado;
    }
    
    // Más métodos
    boolean hipotecar(int ip)
    {
        boolean result=false;
        if(!encarcelado)
        {
            if(existeLaPropiedad(ip))
            {
                TituloPropiedad propiedad=propiedades.get(ip);
                result=propiedad.hipotecar(this);
            }
            
        }
        if(result)
        {
            Diario.getInstance().ocurreEvento("El jugador "+nombre+ " hipoteca la propiedad "+ip);
        }
        return result;
    }
    
    // Métodos set
    boolean modificarSaldo(float cantidad)
    {
        saldo += cantidad;
        
        Diario.getInstance().ocurreEvento("El saldo del jugador "+nombre+" ha sido modificado (+"+cantidad+")");
        
        return true;
    }
    
    boolean moverACasilla(int numCasilla)
    {
        boolean respuesta = false;
        
        if (!encarcelado)
        {
            numCasillaActual = numCasilla;
            puedeComprar = false;
            
            Diario.getInstance().ocurreEvento("El jugador "+nombre+" se ha movido a la casilla" + numCasilla);
            
            respuesta = true;
        }
        
        return respuesta;
    }
    
    boolean obtenerSalvoconducto(SorpresaSalirCarcel sorpresa)
    {
        if(!encarcelado)
        {
            salvoconducto = sorpresa; 
        }

        return encarcelado;
    }
    
    boolean paga(float cantidad)
    {
        boolean resultado = modificarSaldo(cantidad *-1);
        
        return resultado;
    }
    
    boolean pagaAlquiler(float cantidad)
    {
        boolean respuesta = false;
        
        if (!encarcelado)
        {
            respuesta = paga(cantidad);
        }
        
        return respuesta;
    }
    
    boolean pagaImpuesto(float cantidad)
    {
        boolean respuesta = false;
        
        if (!encarcelado)
        {
            respuesta = paga(cantidad);
        }
        
        return respuesta;
    }
    
    boolean pasaPorSalida()
    {
        modificarSaldo(PasoPorSalida);
        Diario.getInstance().ocurreEvento("El jugador "+nombre+" ha pasado por salida");
        
        return true;        
    }
    
    private void perderSalvoconducto()
    {
        salvoconducto.usada();
        salvoconducto=null;
    }
    
    boolean puedeComprarCasilla()
    {
        puedeComprar=true;
        
        if(encarcelado)
        {
           puedeComprar=false; 
        }
        
        return puedeComprar;
    }
    
    private boolean puedeSalirCarcelPagando()
    {
        return (saldo >= PrecioLibertad);
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad)
    {
        boolean resultado = false;
        if (propiedad.getNumCasas() < CasasMax)
        {
            resultado = true;
        }
        
        return resultado;
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad)
    {
        
        Boolean resultado=false;
        if(propiedad.getNumHoteles()<HotelesMax)
        {
            resultado=true;
        }
        
        return resultado;    
    
    }

    private boolean puedoGastar(float precio)
    {
        boolean respuesta = false;
        
        if (!encarcelado && (saldo >= precio))
        {
            respuesta = true;
        }
        
        return respuesta;
    }
    
    boolean recibe(float cantidad)
    {
        boolean respuesta = false;
        
        if (!encarcelado)
        {
            respuesta = modificarSaldo(cantidad);
        }
        
        return respuesta;
    }
    
    boolean salirCarcelPagando()
    {
        boolean respuesta = false;
        
        if(puedeSalirCarcelPagando()){
            paga(PrecioLibertad);
            encarcelado = false;
            Diario.getInstance().ocurreEvento(nombre+ " sale de la cárcel");
            respuesta = true;
        }
        return respuesta;
    }
    
    boolean salirCarcelTirando()
    {
        boolean respuesta;
        respuesta = Dado.getInstance().salgoDeLaCarcel();
        if(respuesta)
        {
            encarcelado = false;
            Diario.getInstance().ocurreEvento( nombre + " sale de la carcel");
            respuesta = true;
        }
        
        return respuesta;
    }
    
    boolean tieneAlgoQueGestionar()
    {
        return (propiedades.size() > 0);
    }
    
    boolean tieneSalvoconducto()
    {
        boolean respuesta = false;
        
        if(salvoconducto!=null)
        {
            respuesta = true;
        }
        
        return respuesta;
    }
    
    boolean vender(int ip)
    {
        boolean respuesta = false;
        
        if (!encarcelado)
        {
            if (existeLaPropiedad(ip))
            {
                if (propiedades.get(ip).vender(this))
                {
                    Diario.getInstance().ocurreEvento("El jugador "+nombre+" ha vendido "
                            +"la propiedad "+propiedades.get(ip).getNombre());
                    
                    propiedades.remove(ip);
                    respuesta = true;
                }
            }
        }
        
        return respuesta;
    }
    
    @Override
    public String toString()
    {
        String cad = "Nombre: "+nombre+
                "\nEncarcelado: "+encarcelado+
                "\nCasilla actual: "+numCasillaActual+
                "\nPuede comprar: "+puedeComprar+
                "\nSaldo: "+saldo+
                "\nTiene salvoconducto: "+ tieneSalvoconducto();
        
        return cad;
    }
    
     public static void main(String [] args){
        
        Jugador j1=new Jugador("Roberto");
        Jugador j2=new Jugador("Andres");
                j1.compareTo(j2);
        
        System.out.print(j1.toString());
        
        j1.cantidadCasasHoteles();
        j1.debeSerEncarcelado();
        j1.enBancarrota();
        j1.existeLaPropiedad(2);
        j1.getSaldo();
        j1.recibe(SaldoInicial);
        j1.puedoGastar(328943953);
    }
}
