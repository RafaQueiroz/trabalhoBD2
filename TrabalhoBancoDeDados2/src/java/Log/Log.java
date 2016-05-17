/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import Dados.Dados;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rhuanbarros
 */
public class Log {

    static List<TipoLog> logBuffer = new ArrayList<>();
    static List<TipoLog> logDisco = new ArrayList<>();
    
    private static int totalTransacoes = 0;
    
    static {
        /*
        int i = startTransaction();
        update(i, 0, "teste1");
        commit(i);
        checkPoint();*/
    }

    public static int startTransaction() {
        StartTransaction startTransaction = new StartTransaction();
        logBuffer.add(startTransaction);
        totalTransacoes++;
        return startTransaction.getIdTransacao();
    }

    public static int getTotalTransacoes() {
        return totalTransacoes;
    }

    public static void update(int idTransacao, int idLinha, String ai) {
        //tem q ir no dadosDisco e pegar o valor pra q ta no disco pra colocar no BI
        String bi = Dados.getNomeProdutoById(idLinha);

        Update update = new Update(idTransacao, "Produto", idLinha, "Nome", bi, ai);
        logBuffer.add(update);
    }

    public static void commit(int idTransacao) {
        logBuffer.add(new Commit());
    }

    public static void checkPoint() {
        logBuffer.add(new CheckPoint());
    }

    /* Este método deve deve fazer um append do LogBuffer ao Log que ta no disco,
        e não sobrescreve o que há la. Por isso eu abro o arquivo antes.
    */
    
    public static void logWriter() {
        
        try {
            
            System.out.println("Começou a executar logWriter");
            FileInputStream arquivo = new FileInputStream("c:\\temp\\LogDisco.rplb");
            ObjectInputStream in = new ObjectInputStream(arquivo);
            List<TipoLog> aux = (List<TipoLog>) in.readObject();
            in.close();
            arquivo.close();
            System.out.println("Aberto de arquivo com sucesso!");

            aux.addAll(logBuffer);
            
            FileOutputStream arquivo2 = new FileOutputStream("c:\\temp\\LogDisco.rplb");
            ObjectOutputStream out = new ObjectOutputStream(arquivo2);
            out.writeObject( aux );
            out.close();
            arquivo2.close();
            
            System.out.println("Salvo para arquivo com sucesso!");
            
            logBuffer.clear(); //limpa logBuffer
            logDisco.clear();
            logDisco.addAll(aux);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Objeto não encontrado");
            c.printStackTrace();
            return;
        }
    }
    
    /*serve simplesmente pra criar o arquivo no disco no inicio
    para não termos problemas de tentarmos abrir o arquivo e ele não existir*/
    
    public static void inicializaArquivoNoDisco() {
        try {
            System.out.println("Começou a executar logWriter");
            FileOutputStream arquivo = new FileOutputStream("c:\\temp\\LogDisco.rplb");
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            List<TipoLog> aux = new ArrayList<>();
            out.writeObject( aux );
            out.close();
            arquivo.close();
            System.out.println("Salvo para arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public static List<TipoLog> getLogBuffer() {
        return logBuffer;
    }

    public static List<TipoLog> getLogDisco() {
        return logDisco;
    }

}
