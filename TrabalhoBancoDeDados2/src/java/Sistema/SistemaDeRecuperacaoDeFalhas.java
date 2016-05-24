/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Log.*;
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
public class SistemaDeRecuperacaoDeFalhas {

    static final String FOLDER = "/tmp/";

    private List<Integer> UNDO = new ArrayList<>();
    private List<Integer> REDO = new ArrayList<>();
    
    private List<Integer> StartTransaction;
    private List<Integer> ignorar = new ArrayList<>();
    
    public List<Produto> dadosDisco = new ArrayList<>();
    public List<TipoLog> logDisco = new ArrayList<>();

    public List<Produto> getDadosDisco() {
        return dadosDisco;
    }

    public List<TipoLog> getLogDisco() {
        return logDisco;
    }

    public SistemaDeRecuperacaoDeFalhas() {
        System.out.println("||||| SISTEMA DE RECUPERAÇÃO DE FALHAS");
        logLoaderParaVerDisco();
        dbLoaderParaVerDisco();
        
        boolean teveCheckpoint = false;

        //percorre o logDisco de tras pra frente
        for(int i=logDisco.size()-1;i>=0;i--) {
            //uma variavel pra cada tipo de log
            final boolean logTipoCommit = logDisco.get(i).getTipo() == TipoLog.Tipo.Commit;
            final boolean logTipoStartTransaction = logDisco.get(i).getTipo() == TipoLog.Tipo.StartTransaction;
            final boolean logTipoCheckpoint = logDisco.get(i).getTipo() == TipoLog.Tipo.CheckPoint;
            
            
            if( logTipoCheckpoint ) {
                teveCheckpoint = true;
            } else if ( logTipoCommit ) {
                Commit c = (Commit) logDisco.get(i);
                if( teveCheckpoint == false ) {
                    REDO.add(c.getIdTransacao());
                } else {
                    ignorar.add(c.getIdTransacao());
                }
            } else if ( logTipoStartTransaction ) {
                StartTransaction s = (StartTransaction) logDisco.get(i);
                
                //quando encontra um startTransaction tem q verificar se ja tinha um commit
                //pra essa transacao. Se não tinha, entao tem q fazer UNDO.
                
                final int idTransacao = s.getIdTransacao();
                System.out.println("esse aqui: "+idTransacao);
                boolean jaEstaNoRedo = verificaSeTransacaoJaEstaNoRedo(idTransacao);
                if( jaEstaNoRedo == false ) {
                    if( verificaSeEstaNaListaDeIgnorar(idTransacao) == false )
                        UNDO.add(idTransacao);
                }
            }
            
        }
        
//        desfaz as operações
        for (int j = UNDO.size() - 1; j >= 0 ; j--){
            System.out.println("interando");
            for(int i= 0; i<= logDisco.size()-1;i++) {
                System.out.println("interando 2");
                final boolean logTipoUpdate = logDisco.get(i).getTipo() == TipoLog.Tipo.Update;
                
                if(logTipoUpdate){
                    System.out.println("Achou onde atualizar");

                    Update update = (Update) logDisco.get(i);
                    
                    if(update.getIdTransacao() == UNDO.get(j)){
                        System.out.println("Id: "+ update.getIdLinha());
                        System.out.println("interando "+ update.getBi());
                        
                        updateRecuperacao(update.getIdLinha(), update.getBi());
                    }
                }
            }
        }
//        refaz as operações
        for(int j= 0; j <= REDO.size() - 1; j++){
            for(int i=0; i<=logDisco.size()-1; i++ ){
                System.out.println("interando 2");
                final boolean logTipoUpdate = logDisco.get(i).getTipo() == TipoLog.Tipo.Update;
                
                if(logTipoUpdate){
                    System.out.println("Achou onde atualizar");

                    Update update = (Update) logDisco.get(i);
                    
                    if(update.getIdTransacao() == REDO.get(j)){
                        System.out.println("Id: "+ update.getIdLinha());
                        System.out.println("interando "+ update.getBi());
                        
                        updateRecuperacao(update.getIdLinha(), update.getAi());
                        logWriter();
                        dataWriter();
                    }
                }
            }
        }      

    }
    
    public void updateRecuperacao(int idLinha, String ai) {
        Iterator<Produto> iteratorExame = dadosDisco.iterator();
        Produto p = null;
        while (iteratorExame.hasNext()) {
            p = iteratorExame.next();
            if (p.getId() == idLinha) {
                System.out.println("Valor Log:"+p.getId());
                break;
            }
        }
        p.setNome(ai);
    }

    
    private void mostraUNDO() {
        Iterator<Integer> iteratorInteger = UNDO.iterator();
        Integer i = null;
        while (iteratorInteger.hasNext()) {
            i = iteratorInteger.next();
            System.out.println("--->>>>VALOR i "+i);
        }
    }
    
    private boolean verificaSeEstaNaListaDeIgnorar(int t) {
        boolean retorno = false;
        Iterator<Integer> iteratorInteger = ignorar.iterator();
        Integer i = null;
        while (iteratorInteger.hasNext()) {
            i = iteratorInteger.next();
            if (i == t) {
                retorno = true;
                break;
            }
            //System.out.println("VALOR i "+i+" t: "+t);
            //System.out.println("retorno: "+retorno);
        }
        return retorno;
    }
    
    private boolean verificaSeTransacaoJaEstaNoRedo(int t) {
        boolean retorno = false;
        Iterator<Integer> iteratorInteger = REDO.iterator();
        Integer i = null;
        while (iteratorInteger.hasNext()) {
            i = iteratorInteger.next();
            if (i == t) {
                retorno = true;
                break;
            }
            //System.out.println("VALOR i "+i+" t: "+t);
            //System.out.println("retorno: "+retorno);
        }
        return retorno;
    }

    public List<Integer> getUNDO() {
        return UNDO;
    }

    public List<Integer> getREDO() {
        return REDO;
    }

    public void dbLoaderParaVerDisco() {
        try {
            System.out.println("Começou a abrir -> dbLoaderParaVerDisco");
            FileInputStream arquivo = new FileInputStream(FOLDER + "DadosDisco.rplb");
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

    public void logLoaderParaVerDisco() {
        try {
            //abre o arquivo de log q ta no disco e bota no aux
            System.out.println("Começou a executar logWriter");
            FileInputStream arquivo = new FileInputStream(FOLDER + "LogDisco.rplb");
            ObjectInputStream in = new ObjectInputStream(arquivo);
            logDisco = (List<TipoLog>) in.readObject();
            in.close();
            arquivo.close();
            System.out.println("Aberto de arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Objeto não encontrado");
            c.printStackTrace();
            return;
        }
    }
    
    public void logWriter() {
        
        try {
            
            FileOutputStream arquivo2 = new FileOutputStream(FOLDER+"LogDisco.rplb");
            ObjectOutputStream out = new ObjectOutputStream(arquivo2);
            
            //grava o aux no disco substituindo o q tava la
            out.writeObject(logDisco);
            out.close();
            arquivo2.close();
            
            System.out.println("Salvo para arquivo com sucesso!");
         
        } catch (IOException i) {
            i.printStackTrace();
        }
    }    
    public void dataWriter() {

        try {
            FileOutputStream arquivo2 = new FileOutputStream(FOLDER+"LogDisco.rplb");
            ObjectOutputStream out = new ObjectOutputStream(arquivo2);

            //grava o aux no disco substituindo o q tava la
            out.writeObject(dadosDisco);
            out.close();
            arquivo2.close();

            System.out.println("Salvo para arquivo com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        }   
    }

}
