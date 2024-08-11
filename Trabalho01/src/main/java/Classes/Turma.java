package Classes;

import java.util.List;

public class Turma {
    
    private int codigo;
    private String descricao;
    private List<Pessoa> pessoas; 

    public Turma(int codigo, String descricao, List<Pessoa> pessoas) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.pessoas = pessoas;
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
        return "Turma{" + "codigo=" + codigo + ", descricao=" + descricao + ", pessoas=" + pessoas + '}';
    }
}
