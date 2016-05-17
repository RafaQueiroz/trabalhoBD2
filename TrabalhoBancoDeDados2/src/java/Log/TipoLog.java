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
public abstract class TipoLog {
    public Tipo tipo;

    @Override
    public String toString() {
        return "TipoLog{" + "tipo=" + tipo + '}';
    }

    public Tipo getTipo() {
        return tipo;
    }

    public TipoLog(Tipo tipo) {
        this.tipo = tipo;
    }

    public enum Tipo {
        StartTransaction, Update, Commit, CheckPoint
    }

}
