package br.ufpi.es.checar.Controle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

/**
 * Created by na7an on 13/01/2016.
 */
public class FachadaControle {

    private ControleDeCaptura meuControleDeCaptura;
    private ControleUtil meuControleUtil;
    //private ControleDePessoa meuControleDePessoa;



    public FachadaControle(){
        meuControleDeCaptura = new ControleDeCaptura();
        meuControleUtil = new ControleUtil();
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

    public String OCR(){
        return meuControleDeCaptura.OCR();
    }

    public Intent configurarIntentRecorte(Context context){
        return meuControleDeCaptura.configCropIntent(context);
    }

    public void setImagem(Bitmap imagem){
        meuControleDeCaptura.setImage(imagem);
    }

    public void solicitarPermissao(Context context, Activity activity, final String PERMISSION, final int REQUEST_CODE){
        meuControleUtil.requestUserPermission(context, activity, PERMISSION, REQUEST_CODE);
    }

}