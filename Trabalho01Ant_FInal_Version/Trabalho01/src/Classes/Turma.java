/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 11941578900
 */
public class Turma {
     private static int inscricaoCounter = 1;
    private int codigo;
    private String descricao;
    private List<Pessoa> pessoas; 

    public Turma(String descricao) {
        this.descricao = descricao;
        pessoas = new ArrayList<>();
        this.codigo = inscricaoCounter++;

    }
    
    public static int getInscricaoCounter() {
        return inscricaoCounter;
    }

    public static void setInscricaoCounter(int inscricaoCounter) {
        Turma.inscricaoCounter = inscricaoCounter;
    }
    
    public void addPessoa(Pessoa p){
        pessoas.add(p);
    }
    
    public void removePessoa(Pessoa p){
        pessoas.remove(p);
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao + " - " + pessoas;
    }
}
