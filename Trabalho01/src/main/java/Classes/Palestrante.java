package Classes;

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
        return super.toString() + " - " + titulo;
    }
}
