/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

/**
 *
 * @author rhuanbarros
 */
public class StartTransaction extends TipoLog {
    public int idTransacao;
    public int getIdTransacao() {
        return idTransacao;
    }

    public StartTransaction() {
        super(Tipo.StartTransaction);
        this.idTransacao = getIdProximo();
    }

    @Override
    public String toString() {
        return "[StartTransaction "+"idTransacao="+idTransacao+']';
    }
    
    private static int idProximo=0;
    private static int getIdProximo() {
        return idProximo++;
    }  
}
