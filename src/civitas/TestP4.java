package civitas;

public class TestP4 {
    public static void main(String args[]){
        Jugador j = new Jugador("Rafa");
        TituloPropiedad t = new TituloPropiedad("Huelva",400,2,1500,2000,500);
        j.comprar(t);
        
        JugadorEspeculador je = new JugadorEspeculador(j, 1000);
        System.out.println(je.toString());
    }
}

