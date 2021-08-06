package juegoTexto;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import civitas.Respuestas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.Jugador;
import civitas.TituloPropiedad;
import civitas.GestionesInmobiliarias;

public class VistaTextual {
  
  CivitasJuego juegoModel; 
  private int iGestion=-1;
  private int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }
  //INICIO EXAMEN
  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado", "Cobrando")));
    return (SalidasCarcel.values()[opcion]);
  }
  //FIN EXAMEN
  Respuestas comprar()
  {
    int opcion = menu ("Desea realizar la compra",
      new ArrayList<> (Arrays.asList("SI","NO")));
    return (Respuestas.values()[opcion]);
  }

  void gestionar ()
  {
      String titulo = "\nGestión";
      // Creamos el array de las gestiones
      ArrayList<String> lista_gestiones;
      lista_gestiones = new ArrayList<>();
      // Creamos el array de las propiedades
      ArrayList<String> lista_propiedades;
      lista_propiedades = new ArrayList<>();
      // Inicializamos el array de gestiones
      lista_gestiones.add("Vender");
      lista_gestiones.add("Hipotecar");
      lista_gestiones.add("Cancelar Hipoteca");
      lista_gestiones.add("Construir Casa");
      lista_gestiones.add("Construir Hotel");
      lista_gestiones.add("Terminar");
      
      // Inicializamos el array de las propiedades
      for(int i = 0; i < juegoModel.getJugadorActual().getPropiedades().size(); i++)
      {
          lista_propiedades.add(juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
      }
      
      iGestion = menu(titulo,lista_gestiones);
      
      if(!juegoModel.getJugadorActual().getPropiedades().isEmpty() && iGestion!= lista_gestiones.indexOf("Terminar"))
      {
          iPropiedad = menu("Propiedad:", lista_propiedades);
      }
      else if(iGestion != lista_gestiones.indexOf("Terminar"))
      {
          System.out.println("\nNo posees ninguna propiedad");
      }
  }
  
  public int getGestion()
  {
      return iGestion;
  }
  
  public int getPropiedad()
  {
      return iPropiedad;
  }

  void mostrarSiguienteOperacion(OperacionesJuego operacion)
  {
      System.out.println(operacion.toString());
  }

  void mostrarEventos()
  {
      while(Diario.getInstance().eventosPendientes())
      {
          System.out.println(Diario.getInstance().leerEvento()); 
      }
  }
  
  public void setCivitasJuego(CivitasJuego civitas)
  { 
      juegoModel=civitas;
      actualizarVista();
  }
  
  public void actualizarVista()
  {
      System.out.println(juegoModel.infoJugadorTexto());
      System.out.println(juegoModel.getCasillaActual().toString());
  } 
}
