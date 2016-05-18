/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import Models.Produto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rhuanbarros
 */
public class Dados {

    static public List<Produto> dadosBuffer = new ArrayList<>();
    static public List<Produto> dadosDisco = new ArrayList<>();
    static final String FOLDER = "c:\\temp\\";
    
    public static void update(int idLinha, String ai) {
        Iterator<Produto> iteratorExame = Dados.dadosBuffer.iterator();
        Produto p = null;
        while (iteratorExame.hasNext()) {
            p = iteratorExame.next();
            if (p.getId() == idLinha) {
                break;
            }
        }
        p.setNome(ai);
    }

    public static void dbLoaderParaProBuffer() {
        try {
            System.out.println("Começou a abrir -> dbLoaderParaVerDisco");
            FileInputStream arquivo = new FileInputStream(FOLDER+"DadosDisco.rplb");
            ObjectInputStream in = new ObjectInputStream(arquivo);
            dadosBuffer = (List<Produto>) in.readObject();
            in.close();
            arquivo.close();
            System.out.println("Aberto de arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Objeto não encontrado");
            c.printStackTrace();
            return;
        }
    }    
    
    public static void dbLoaderParaVerDisco() {
        try {
            System.out.println("Começou a abrir -> dbLoaderParaVerDisco");
            FileInputStream arquivo = new FileInputStream(FOLDER+"DadosDisco.rplb");
            ObjectInputStream in = new ObjectInputStream(arquivo);
            dadosDisco = (List<Produto>) in.readObject();
            in.close();
            arquivo.close();
            System.out.println("Aberto de arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Objeto não encontrado");
            c.printStackTrace();
            return;
        }
    }

    public static int getTotalDadosDisco() {
        return dadosDisco.size();
    }

    public static void criaArquivoDadosDiscoParaApresentacao() {
        try {
            System.out.println("Começou a salvar");
            FileOutputStream arquivo = new FileOutputStream(FOLDER+"DadosDisco.rplb");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);

            List<Produto> aux = new ArrayList<>();

            aux.add(new Produto("Mouse"));
            aux.add(new Produto("Teclado"));
            aux.add(new Produto("Monitor LCD"));

            out.writeObject(aux);

            out.close();
            arquivo.close();
            System.out.println("Salvo para arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void addProduto(Produto produto) {
        dadosBuffer.add(produto);
    }
 

    public static List<Produto> getDadosBuffer() {
        return dadosBuffer;
    }

    public static List<Produto> getDadosDisco() {
        return dadosDisco;
    }

    public static void setDadosBuffer(List<Produto> dadosBuffer) {
        Dados.dadosBuffer = dadosBuffer;
    }

    public static void setDadosDisco(List<Produto> dadosDisco) {
        Dados.dadosDisco = dadosDisco;
    }
    
    public static String getNomeProdutoById(int id) {
        Iterator<Produto> iteratorExame = Dados.dadosDisco.iterator();
        Produto p = null;
        while (iteratorExame.hasNext()) {
            p = iteratorExame.next();
            if (p.getId() == id) {
                break;
            }
        }
        return p.getNome();
    }
    
    public static void checkpoint() {
        dadosDisco.clear();
        dadosDisco.addAll(Dados.dadosBuffer);
        
        try {
            System.out.println("Começou a salvar");
            FileOutputStream arquivo = new FileOutputStream(FOLDER+"DadosDisco.rplb");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);

            out.writeObject(dadosDisco);

            out.close();
            arquivo.close();
            System.out.println("Salvo para arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
