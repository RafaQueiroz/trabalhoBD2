/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.io.Serializable;

/**
 *
 * @author rhuanbarros
 */
public class Commit extends TipoLog implements Serializable {
    public int idTransacao;
    public int getIdTransacao() {
        return idTransacao;
    }

    public Commit(int idTransacao) {
        super(Tipo.Commit);
        this.idTransacao = idTransacao;        
    }

    @Override
    public String toString() {
        return "[Commit "+"idTransacao="+idTransacao+']';
    }
    
}
