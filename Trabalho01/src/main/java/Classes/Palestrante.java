/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author 11941578900
 */
public class Palestrante extends Pessoa{
    
    private String titulo;
    private String conteudoPalestra;

    public Palestrante(String titulo, String conteudoPalestra, String cpf, String nome, String endereco) {
        super(cpf, nome, endereco);
        this.titulo = titulo;
        this.conteudoPalestra = conteudoPalestra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudoPalestra() {
        return conteudoPalestra;
    }

    public void setConteudoPalestra(String conteudoPalestra) {
        this.conteudoPalestra = conteudoPalestra;
    }
}
