package br.ufpi.es.checar.Controle;

import android.content.Context;

/**
 * Created by na7an on 13/01/2016.
 */
public class FachadaControle {

    private ControleDeCaptura meuControleDeCaptura;
    //private ControleDePessoa meuControleDePessoa;



    public FachadaControle(){
        meuControleDeCaptura = new ControleDeCaptura();
    }


    public void criarDiretorioOCR(){
        meuControleDeCaptura.createOCRDirectory();
    }

    public void criarArquivoLinguagemOCR(Context context){
        meuControleDeCaptura.createOCRTrainedLang(context);
    }

    public void setDiretorioImagem(){
        meuControleDeCaptura.setImagePath();
    }

    public String getDiretorioImagem(){
        return meuControleDeCaptura.getImagePath();
    }

}