package civitas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class CivitasJuego {
    // Atributos de instancia
    private int indiceJugadorActual;
    
    // Atributos de referencia
    private ArrayList<Jugador> jugadores;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    private Tablero tablero;
    private MazoSorpresas mazo;

    public CivitasJuego(ArrayList<String> nombres)
    {
        
        jugadores = new ArrayList<>();
        
        for(int i = 0; i < nombres.size(); i++)
        {
            jugadores.add(new Jugador(nombres.get(i)));
        }

        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        mazo = new MazoSorpresas();
        inicializarTablero(mazo);
        inicializarMazoSorpresas(tablero);
    }
    
    private void avanzaJugador()
    {
        Jugador jugadorActual=jugadores.get(indiceJugadorActual);

       	int posicionActual=jugadorActual.getNumCasillaActual();
       	int tirada=Dado.getInstance().tirar();
       	int posicionNueva=tablero.nuevaPosicion(posicionActual,tirada);

       	Casilla casilla=tablero.getCasilla(posicionNueva);
       	contabilizarPasosPorSalida(jugadorActual);
       	jugadorActual.moverACasilla(posicionNueva);
       	casilla.recibeJugador(indiceJugadorActual, jugadores);
       	contabilizarPasosPorSalida(jugadorActual);
    }
    
    public boolean cancelarHipoteca(int ip)
    {
        return jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
    }
    
    public boolean comprar()
    {
       boolean resultado = false;
       
       Jugador jugadorActual = getJugadorActual();
       int numCasillaActual = jugadorActual.getNumCasillaActual();
       Casilla casilla = tablero.getCasilla(numCasillaActual);
       
       if (casilla instanceof CasillaCalle){
           CasillaCalle casillacalle = (CasillaCalle)casilla;
           TituloPropiedad titulo = casillacalle.getTituloPropiedad();
           resultado = jugadorActual.comprar(titulo);
       }
       
       return resultado;
    }
    
    public boolean construirCasa(int ip)
    {
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip)
    {
        return jugadores.get(indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual)
    {
        while (tablero.getPorSalida()>0)
        {
            jugadorActual.pasaPorSalida();
        }
    }
    
    public boolean finalDelJuego()
    {
        boolean respuesta = false;
        
        for (int i=0; i<jugadores.size(); i++)
        {
            if (jugadores.get(i).enBancarrota())
            {
                respuesta = true;
            }
        }
        
        return respuesta;
    }

    public String getEstado(){
        return estado.toString();
    }

    public Jugador getJugadorActual()
    {
        return jugadores.get(indiceJugadorActual);
    }
    
    public Casilla getCasillaActual()
    {
        int indice = getJugadorActual().getNumCasillaActual();
        return tablero.getCasilla(indice);
    }
    
    public boolean hipotecar(int ip)
    {
        return getJugadorActual().hipotecar(ip);
    }
    
    public String infoJugadorTexto()
    {
        return getJugadorActual().toString();
    }
    
    private void inicializarMazoSorpresas(Tablero tablero)
    {
        Sorpresa irCarcel = new SorpresaIrCarcel(tablero);
        mazo.alMazo(irCarcel);
        
        Sorpresa salirCarcel = new SorpresaSalirCarcel(mazo);
        mazo.alMazo(salirCarcel);
        
        Sorpresa irSalida = new SorpresaIrCasilla(tablero, 0, "Vuelves a la salida");
        mazo.alMazo(irSalida);
        
        Sorpresa aLaCarcel = new SorpresaIrCasilla(tablero, tablero.getCarcel(), "Te vas a la carcel de visita");
        mazo.alMazo(irSalida);
        
        Sorpresa irHuelva = new SorpresaIrCasilla(tablero, 1, "Te vas de vacaciones a Huelva");
        mazo.alMazo(irHuelva);
        
        Sorpresa cumpleaños = new SorpresaPorJugador("Felicidades, es tu cumpleaños, cobra 200 de cada jugador",200);
        mazo.alMazo(cumpleaños);
        
        Sorpresa moroso = new SorpresaPorJugador("Debes dinero moroso, paga 200 a cada jugador",-200);
        mazo.alMazo(moroso);
        
        Sorpresa hacienda = new SorpresaPorCasaHotel("Paga 200 por cada casa u hotel que tengas",-200);
        mazo.alMazo(hacienda);
        
        Sorpresa subvencion = new SorpresaPorCasaHotel("Recibes una subvencion del Estado, cobra 200 por cada casa u hotel que tengas",-200);
        mazo.alMazo(subvencion);
        
        Sorpresa loteria = new SorpresaPagarCobrar("¡Te ha tocado la lotería! Cobra 10000",10000);
        mazo.alMazo(loteria);
        
        Sorpresa accidente = new SorpresaPagarCobrar("Has sufrido un accidente y te ha costado 1000",1000);
        mazo.alMazo(accidente);
        
        Sorpresa especulador = new SorpresaEspeculador(1000);
        mazo.alMazo(especulador);
    }
    
    private void inicializarTablero(MazoSorpresas mazo)
    {
        tablero = new Tablero(5);
        
        TituloPropiedad titulo1 = new TituloPropiedad("Huelva",400,2,1500,2000,500);
        CasillaCalle casilla1 = new CasillaCalle(titulo1);
        tablero.añadeCasilla(casilla1);
        
        TituloPropiedad titulo2 = new TituloPropiedad("Sevilla",400,2,1500,2000,500);
        CasillaCalle casilla2 = new CasillaCalle(titulo2);
        tablero.añadeCasilla(casilla2);
        
        TituloPropiedad titulo3 = new TituloPropiedad("Cádiz",400,2,1500,2000,500);
        CasillaCalle casilla3 = new CasillaCalle(titulo3);
        tablero.añadeCasilla(casilla3);
        
        TituloPropiedad titulo4 = new TituloPropiedad("Málaga",400,2,1500,2000,500);
        CasillaCalle casilla4 = new CasillaCalle(titulo4);
        tablero.añadeCasilla(casilla4);
        
        TituloPropiedad titulo5 = new TituloPropiedad("Córdoba",400,2,1500,2000,500);
        CasillaCalle casilla6 = new CasillaCalle(titulo5);
        tablero.añadeCasilla(casilla6);
        
        TituloPropiedad titulo6 = new TituloPropiedad("Jaén",400,2,1500,2000,500);
        CasillaCalle casilla7 = new CasillaCalle(titulo6);
        tablero.añadeCasilla(casilla7);
        
        TituloPropiedad titulo7 = new TituloPropiedad("Granada",400,2,1500,2000,500);
        CasillaCalle casilla8 = new CasillaCalle(titulo7);
        tablero.añadeCasilla(casilla8);
        
        TituloPropiedad titulo8 = new TituloPropiedad("Almería",400,2,1500,2000,500);
        CasillaCalle casilla9 = new CasillaCalle(titulo8);
        tablero.añadeCasilla(casilla9);
        
        TituloPropiedad titulo9 = new TituloPropiedad("Toledo",400,2,1500,2000,500);
        CasillaCalle casilla10 = new CasillaCalle(titulo9);
        tablero.añadeCasilla(casilla10);
        
        TituloPropiedad titulo10 = new TituloPropiedad("Ciudad Real",400,2,1500,2000,500);
        CasillaCalle casilla11 = new CasillaCalle(titulo10);
        tablero.añadeCasilla(casilla11);
        
        TituloPropiedad titulo11 = new TituloPropiedad("Cuenca",400,2,1500,2000,500);
        CasillaCalle casilla12 = new CasillaCalle(titulo11);
        tablero.añadeCasilla(casilla12);
        
        TituloPropiedad titulo12 = new TituloPropiedad("Albacete",400,2,1500,2000,500);
        CasillaCalle casilla13 = new CasillaCalle(titulo12);
        tablero.añadeCasilla(casilla13);
        
        CasillaSorpresa casilla14 = new CasillaSorpresa("Sorpresa1",mazo);
        tablero.añadeCasilla(casilla14);
        
        CasillaSorpresa casilla15 = new CasillaSorpresa("Sorpresa1",mazo);
        tablero.añadeCasilla(casilla15);
        
        CasillaSorpresa casilla16 = new CasillaSorpresa("Sorpresa1",mazo);
        tablero.añadeCasilla(casilla16);
        
        tablero.añadeJuez();
        
        CasillaImpuesto casilla18 = new CasillaImpuesto("Impuesto", 200);
        tablero.añadeCasilla(casilla18);
        
        Casilla casilla19 = new Casilla ("Parking");
        tablero.añadeCasilla(casilla19);
    }
    
    private void pasarTurno()
    {
        if(indiceJugadorActual < jugadores.size() -1 )
        {
            indiceJugadorActual++;
        }
        else
        {
            indiceJugadorActual = 0;
        }
    }
    
    public ArrayList<Jugador> ranking()
    {
      Collections.sort(jugadores);
      return jugadores;
    }
    
    public boolean salirCarcelPagando()
    {
        return jugadores.get(indiceJugadorActual).salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando()
    {
        return jugadores.get(indiceJugadorActual).salirCarcelTirando();
    }
    
    public OperacionesJuego siguientePaso()
    {
        Jugador jugadorActual = getJugadorActual();
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual, estado);
        
        if (operacion==OperacionesJuego.PASAR_TURNO)
        {
            pasarTurno();
        }
        else 
        {
            if (operacion==OperacionesJuego.AVANZAR)
            {
                avanzaJugador();
            }
        }   
        siguientePasoCompletado(operacion);
        return operacion;
    }
    
    public void siguientePasoCompletado(OperacionesJuego operacion)
    {
        Jugador actual = jugadores.get(indiceJugadorActual);
        estado = gestorEstados.siguienteEstado(actual, estado, operacion);
    }
    
    public boolean vender(int ip)
    {
        return jugadores.get(indiceJugadorActual).vender(ip);
    }  
}
