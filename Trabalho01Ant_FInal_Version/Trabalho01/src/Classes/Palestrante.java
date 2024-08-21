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

    public Palestrante(String titulo, String cpf, String nome, String endereco) {
        super(cpf, nome, endereco);
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return  "Palestrante - " + super.toString() + " - " + titulo;
    }
}
