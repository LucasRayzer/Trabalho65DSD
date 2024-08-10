/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author 11941578900
 */
public class Participante extends Pessoa{
    
    private static int inscricaoCounter = 1;

    private int inscricao;
    private boolean contribuicao;
    private String valorContribuicao;

    public Participante(boolean contribuicao, String valorContribuicao, String cpf, String nome, String endereco) {
        super(cpf, nome, endereco);
        this.inscricao = inscricaoCounter++;
        this.contribuicao = contribuicao;
        this.valorContribuicao = valorContribuicao;
    }

    
    public int getInscricao() {
        return inscricao;
    }

    public void setInscricao(int inscricao) {
        this.inscricao = inscricao;
    }

    public boolean isContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(boolean contribuicao) {
        this.contribuicao = contribuicao;
    }

    public static int getInscricaoCounter() {
        return inscricaoCounter;
    }

    public static void setInscricaoCounter(int inscricaoCounter) {
        Participante.inscricaoCounter = inscricaoCounter;
    }

    public String getValorContribuicao() {
        return valorContribuicao;
    }

    public void setValorContribuicao(String valorContribuicao) {
        this.valorContribuicao = valorContribuicao;
    }

  
}
