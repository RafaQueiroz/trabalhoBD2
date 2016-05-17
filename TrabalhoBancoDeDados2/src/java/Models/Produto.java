/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author rhuanbarros
 */
public class Produto implements Serializable {

    private int id;
    public int getId() {
        return id;
    }

    private String nome;
    public String getNome() {
        return nome;
    }
    
    public Produto(String nome) {
        this.id = getIdProximo();
        this.nome = nome;
    }

    private static int idProximo=0;

    public void setNome(String nome) {
        this.nome = nome;
    }
    private static int getIdProximo() {
        return idProximo++;
    }
    
}
