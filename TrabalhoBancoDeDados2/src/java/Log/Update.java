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
public class Update extends TipoLog implements Serializable {
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

    public int getIdTransacao() {
        return idTransacao;
    }

    public String getTabela() {
        return tabela;
    }

    public int getIdLinha() {
        return idLinha;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getBi() {
        return bi;
    }

    public String getAi() {
        return ai;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public void setIdLinha(int idLinha) {
        this.idLinha = idLinha;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    

    @Override
    public String toString() {
        return "[Update "+"idTransacao="+idTransacao+", tabela="+tabela+", idLinha="+idLinha+", atributo="+atributo+", bi="+bi+", ai="+ai+']';
    }
    
}
