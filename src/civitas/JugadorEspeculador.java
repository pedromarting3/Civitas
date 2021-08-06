package civitas;

public class JugadorEspeculador extends Jugador {
    
    private static final int FactorEspeculador = 2;
    private float fianza;
    
    public JugadorEspeculador(Jugador otro, float fianza) {
        super(otro);
        
        this.fianza = fianza;
        
        for (int i=0; i<propiedades.size(); i++)
           propiedades.get(i).actualizaPropietarioPorConversion(this);
    }
    
        public static int getFactorEspeculador(){
        return FactorEspeculador;
    }
    
    public float getFianza(){
        return fianza;
    }
    
    @Override
    protected boolean debeSerEncarcelado(){
        boolean resultado = super.debeSerEncarcelado();
        
        if (resultado){
            if (fianza <= getSaldo()){
                resultado = false;
                modificarSaldo(-fianza);
                
                Diario.getInstance().ocurreEvento("El jugador "+getNombre()+" ha pagado la fianza");
            }     
        }

        return resultado;
    }
    
    @Override
    boolean pagaImpuesto(float cantidad){
        float pago = cantidad/FactorEspeculador;
        return super.pagaImpuesto(pago);
    }
    
    @Override
    protected int getCasasMax(){
        return super.getCasasMax()*FactorEspeculador;
    }
    
    @Override
    protected int getHotelesMax(){
        return super.getHotelesMax()*FactorEspeculador;
    }
    
    @Override
    public String toString(){
        String cad = "Jugador especulador\nFianza: "+fianza+"\n"+super.toString();
        
        return cad;
    }   
}
