package br.ufpi.es.checar.Entidade;

import java.util.Date;

/**
 * Created by lucas on 30/01/2016.
 */
public class CNH {
    private String numero_Registro;
    private String categoria;
    private Date validade;
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNumero_Registro() {
        return numero_Registro;
    }

    public void setNumero_Registro(String numero_Registro) {
        this.numero_Registro = numero_Registro;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }
}
