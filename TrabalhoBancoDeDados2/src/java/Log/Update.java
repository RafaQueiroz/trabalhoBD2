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
public class Update extends TipoLog {
    public int idTransacao;
    public String tabela;
    public int idLinha;
    public String atributo;
    public String bi;
    public String ai;

    public Update(int idTransacao, String tabela, int idLinha, String atributo, String bi, String ai) {
        super(Tipo.Update);
        
        this.idTransacao = idTransacao;
        this.tabela = tabela;
        this.idLinha = idLinha;
        this.atributo = atributo;
        this.bi = bi;
        this.ai = ai;
    }

    @Override
    public String toString() {
        return "[Update "+"idTransacao="+idTransacao+", tabela="+tabela+", idLinha="+idLinha+", atributo="+atributo+", bi="+bi+", ai="+ai+']';
    }
    
}
