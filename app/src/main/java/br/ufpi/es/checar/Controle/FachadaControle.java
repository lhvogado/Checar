package br.ufpi.es.checar.Controle;
/**
 * Created by na7an on 13/01/2016.
 */
public class FachadaControle {

    private ControleDeCaptura meuControleDeCaptura;
    private ControleDePessoa meuControleDePessoa;

    public FachadaControle (){
        this.meuControleDeCaptura = new ControleDeCaptura();
        this.meuControleDePessoa = new ControleDePessoa();

    }

}
