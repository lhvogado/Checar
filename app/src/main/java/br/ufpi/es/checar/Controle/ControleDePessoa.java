package br.ufpi.es.checar.Controle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by na7an on 01/02/2016.
 */
public class ControleDePessoa {

    private File myFile;

    private static final String TAG = "Checar.java";


    /**
     * Cria uma arquivo pdf contendo as informações passadas ao método
     * @param CPF
     * @param placa
     * @param context
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    private void createPdf(String CPF, String placa, Context context) throws FileNotFoundException, DocumentException {

        Log.i(TAG, "CREATE PDF");


        File pdfFolder = new File(Environment
                .getExternalStorageDirectory().toString() + "/Checar/PDF/");

        Log.i(TAG, "Diretorio: " + pdfFolder);


        if (!pdfFolder.exists()) {
            if(!pdfFolder.mkdir()){
                Log.i(TAG, "Pdf Directory not created");
                return;
            }
            else{
                Log.i(TAG, "Pdf Directory created");
            }

        }

        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

        myFile = new File(pdfFolder + "/" + timeStamp + ".pdf");

        OutputStream output = new FileOutputStream(myFile);

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, output);

        document.open();

        document.add(new Paragraph(gerarTextoContrato(CPF, placa)));

        document.close();

        viewPdf(context);

    }

    /**
     * Passa o arquivo pdf para ser aberto por uma aplicação compatível para visualização de arquivos pdf
     * @param context
     */
    private void viewPdf(Context context){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(myFile), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);
    }

    /**
     * Gera um arquivo pdf
     * @param CPF
     * @param placa
     * @param context
     */
    public void generatePDF(String CPF, String placa, Context context) {
        Log.i(TAG, "ENTROU");

        try {
            createPdf(CPF, placa, context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gera um texto padrão para o contrato
     * @param CPF
     * @param placa
     * @return
     */
    public String gerarTextoContrato(String CPF, String placa){
        return "CONTRATO DE LOCAÇÃO DE VEICULOS\n" +
                "\n" +
                "CLÁUSULA PRIMEIRA - Pelo presente instrumento particular de Contrato de Locação de Veículos tendo de um lado doravante como CONTRATANTE o Sr Luis Henrique Silva Vogado, CPF: "+  CPF +", residente à Rua Universitária, convenciona e contrata o seguinte: LOCAÇÃO DE UM VEÍCULO modelo Pálio Sporting – PLACA "+ placa +" cidade Teresina, " +
                "por um período de 3 dias, tendo inicio no dia 07 de março de 2016 à 10 de março de 2016, podendo o referido contrato ser renovado, caso haja interesse de ambas as partes. Todas as despesas para manutenção e conservação do referido veículo correrá por conta do contratante.\n" +
                "\n" +
                "CLÁUSULA SEGUNDA – O LOCATÁRIO declara aceitar este contrato de locação nas condições mencionadas, confessando ter recebido o referido veículo em perfeito estado de conservação e funcionamento, responsabilizando-se pela sua restituição no mesmo estado em que recebeu e respondendo por todo e qualquer dano que a mesma sofra," +
                " e indenizando em dinheiro, a vista, ao LOCADOR, no caso de falta, inutilização ou dano ao referido veículo o qual para este efeito é dado o valor de R$ 100,00 ).\n" +
                "CLÁUSULA TERCEIRA – As partes contratadas elegem o foro de Brasília-DF, para dirimir qualquer ação oriunda deste contrato.\n" +
                "\n" +
                "E por estarem justo e contratados, assinam o presente instrumento em 02 (duas) vias de igual teor e forma, na presença de 02 (duas) testemunhas abaixo assinadas.\n\n"+

                "Teresina – PI, 07 de março de 2016.\n\n\n\n\n\n"+
                "Assinatura do Locador :____________________________________________________\n"+
                "Assinatura do dono da Locadora:________________________________________________\n";
    }

}
