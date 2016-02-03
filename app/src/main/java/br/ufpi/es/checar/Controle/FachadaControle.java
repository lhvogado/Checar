package br.ufpi.es.checar.Controle;

/**
 * Created by na7an on 13/01/2016.
 */
public class FachadaControle {

    private ControleDeCaptura meuControleDeCaptura;
    //private ControleDePessoa meuControleDePessoa;

    public FachadaControle(){
        meuControleDeCaptura = new ControleDeCaptura();
    }


    public void setImagePath(){

        meuControleDeCaptura.setImagePath();

    }

    public String getImagePath(){

        return meuControleDeCaptura.getImagePath();
    }


}