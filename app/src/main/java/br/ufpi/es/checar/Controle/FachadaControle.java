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
    private ControleDePessoa meuControleDePessoa;



    public FachadaControle(){
        meuControleDeCaptura = new ControleDeCaptura();
        meuControleUtil = new ControleUtil();
        meuControleDePessoa = new ControleDePessoa();
    }


    /**
     * Cria o diretório do arquivo de treinamento do tesseract no dispositivo móvel
     * @author Natanael
     * @return void
     */
    public void criarDiretorioOCR(){
        meuControleDeCaptura.createOCRDirectory();
    }

    /**
     * Cria o arquivo de treinamento do tesseract
     * @author Natanael
     * @param context
     * @return void
     */
    public void criarArquivoLinguagemOCR(Context context){
        meuControleDeCaptura.createOCRTrainedLang(context);
    }

    /**
     * Define o caminho a salvar a imagem retornada pela camera do dispositivo móvel
     * @author Natanael
     * @return void
     */
    public void setDiretorioImagem(){
        meuControleDeCaptura.setImagePath();
    }

    /**
     * Retorna o caminho da última imagem salva no dispositivo móvel
     * @author Natanael
     * @return String
     */
    public String getDiretorioImagem(){
        return meuControleDeCaptura.getImagePath();
    }

    /**
     * Utiliza a biblioteca tesseract para o reconhecimento de caracteres de uma imagem
     * @author Natanael
     * @return String
     */
    public String OCR(){
        return meuControleDeCaptura.OCR();
    }

    /**
     * Define a imagem de trabalho atual
     * @author Natanael
     * @param imagem
     * @return void
     */
    public void setImagem(Bitmap imagem){
        meuControleDeCaptura.setImage(imagem);
    }

    /**
     * Solicita uma permissão de uso de recursos ao sistema
     * @author Natanael
     * @param context
     * @param activity
     * @param PERMISSION
     * @param REQUEST_CODE
     * @return void
     */
    public void solicitarPermissao(Context context, Activity activity, final String PERMISSION, final int REQUEST_CODE){
        meuControleUtil.requestUserPermission(context, activity, PERMISSION, REQUEST_CODE);
    }

    public void gerarPDF(String CPF, String placa, Context context){
        meuControleDePessoa.generatePDF(CPF, placa, context);
    }

}