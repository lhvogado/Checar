package br.ufpi.es.checar.Entidade;

import java.util.Date;

/**
 * Created by lucas on 30/01/2016.
 */
public class Pessoa {
    private String nome;
    private Date data_Nascimento;
    private String CPF;
    private String RG;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_Nascimento() {
        return data_Nascimento;
    }

    public void setData_Nascimento(Date data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }
}
