/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author 11941578900
 */
public class Participante {
    
    private int inscricao;
    private boolean contribuicao;
    private double valorContribuicao;

    public Participante(int inscricao, boolean contribuicao, double valorContribuicao) {
        this.inscricao = inscricao;
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

    public double getValorContribuicao() {
        return valorContribuicao;
    }

    public void setValorContribuicao(double valorContribuicao) {
        this.valorContribuicao = valorContribuicao;
    }
}
