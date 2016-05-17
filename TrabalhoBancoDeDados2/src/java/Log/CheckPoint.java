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
public class CheckPoint extends TipoLog {

    @Override
    public String toString() {
        return "[CheckPoint]";
    }

    public CheckPoint() {
        super(Tipo.CheckPoint);
    }
    
}
