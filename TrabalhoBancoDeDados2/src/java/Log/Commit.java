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
public class Commit extends TipoLog {
    public int idTransacao;
    public int getIdTransacao() {
        return idTransacao;
    }

    public Commit() {
        super(Tipo.Commit);
        this.idTransacao = getIdProximo();
    }

    private static int idProximo=0;
    private static int getIdProximo() {
        return idProximo++;
    }  

    @Override
    public String toString() {
        return "[Commit "+"idTransacao="+idTransacao+']';
    }
    
}
