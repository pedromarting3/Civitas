package civitas;

public class TestP1
{
    public static void main(String[] args)
    {
        Dado dado = Dado.getInstance();
        dado.getClass();
        int numero_jugadores = 4; 
        System.out.println("QUIEN EMPIEZA: ");
        int valor0 = 0, valor1 = 0, valor2 = 0,valor3 = 0;
        int valor;
        for(int i = 0; i < 100; i++)
        {
            valor = dado.quienEmpieza(numero_jugadores);
            switch(valor)
            {
                case 0:
                    valor0++;
                break;
                
                case 1:
                    valor1++;
                break;
                
                case 2: 
                    valor2++;
                break;
                
                case 3: 
                    valor3++;
                break;
            }
            
        } 
        System.out.println("El valor 0 aparece :" + valor0 + "veces.");
        System.out.println("El valor 1 aparece :" + valor1 + "veces.");
        System.out.println("El valor 2 aparece :" + valor2 + "veces.");
        System.out.println("El valor 3 aparece :" + valor3 + "veces.");
        
              
        System.out.println("ACTIVAMOS EL DEBUG Y REALIZAMOS TIRADA: ");
        dado.setDebug(Boolean.TRUE);
        for(int i = 0; i < 10; i++)
        {
            System.out.println(dado.tirar());
        } 
        System.out.println("DESACTIVAMOS EL DEBUG Y REALIZAMOS TIRADA: ");
        dado.setDebug(Boolean.FALSE);
        for(int i = 0; i < 10; i++)
        {
            System.out.println(dado.tirar());
        } 
        System.out.println("Vemos el último resultado: " + dado.getUltimoResultado() + "\n");
        System.out.println("Comprobamos 100 veces si salimos de la cárcel: \n");
        Boolean respuesta;
        int respuestatrue = 0, respuestafalse = 0;
        for(int i = 0; i < 100; i++)
        {
            respuesta = dado.salgoDeLaCarcel();
            if(respuesta == true)
            {
                respuestatrue++;
            }
            else
            {
                respuestafalse++;
            }
            
        }
        System.out.println("Hemos salido: " + respuestatrue + " veces.");
        System.out.println("No hemos salido: " + respuestafalse + " veces.\n");
        System.out.println("Mostramos un valor de cada tipo de enumerado:");
        System.out.println(EstadosJuego.INICIO_TURNO);
        System.out.println(OperacionesJuego.AVANZAR);
        System.out.println(TipoCasilla.CALLE);
        System.out.println(TipoSorpresa.IRCARCEL + "\n");
        System.out.println("Creamos 2 sorpresas y comprobamos la ejecución de mazosorpresa y de dado ");
        
        MazoSorpresas m = new MazoSorpresas();
        /*
        Sorpresa sorpresa1 = new Sorpresa(TipoSorpresa.IRCARCEL,m);
        Sorpresa sorpresa2 = new Sorpresa(TipoSorpresa.IRCARCEL,m);
        
        m.alMazo(sorpresa1);
        m.alMazo(sorpresa2);
        System.out.println(m.siguiente().toString());
        Diario diario = Diario.getInstance();
        
        m.inhabilitarCartaEspecial(sorpresa2);
        System.out.println(diario.leerEvento());

        m.habilitarCartaEspecial(sorpresa2);
        System.out.println(diario.leerEvento());
        
        System.out.println(diario.eventosPendientes());
        */
    }

}

    
