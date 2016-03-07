package br.ufpi.es.checar.Visao;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufpi.es.checar.R;

public class TelaTeste extends AppCompatActivity {

    private static final String TAG = "Checar.java";

    private EditText mSubjectEditText, mBodyEditText;

    File myFile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_teste);


        mSubjectEditText = (EditText) findViewById(R.id.edit_text_subject);
        mBodyEditText = (EditText) findViewById(R.id.edit_text_body);
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        Log.i(TAG, "CREATE PDF");


        File pdfFolder = new File(Environment
                .getExternalStorageDirectory().toString() + "/Checar/PDF/");


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

        myFile = new File(pdfFolder + timeStamp + ".pdf");

        OutputStream output = new FileOutputStream(myFile);

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, output);

        document.open();

        document.add(new Paragraph(mSubjectEditText.getText().toString()));
        document.add(new Paragraph(mBodyEditText.getText().toString()));

        document.add(new Paragraph(gerarTextoContrato()));

        document.close();

        viewPdf();

    }

    private void viewPdf(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(myFile), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void emailNote()
    {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_SUBJECT,mSubjectEditText.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, mBodyEditText.getText().toString());
        Uri uri = Uri.parse(myFile.getAbsolutePath());
        email.putExtra(Intent.EXTRA_STREAM, uri);
        email.setType("message/rfc822");
        startActivity(email);
    }

    public void salvarPDF(View v) {
        Log.i(TAG, "ENTROU");


        if (mSubjectEditText.getText().toString().isEmpty()){
            mSubjectEditText.setError("Subject is empty");
            mSubjectEditText.requestFocus();
            return;
        }

        if (mBodyEditText.getText().toString().isEmpty()){
            mBodyEditText.setError("Body is empty");
            mBodyEditText.requestFocus();
            return;
        }

        try {
            createPdf();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public String gerarTextoContrato(){
        return "CONTRATO DE LOCAÇÃO DE VEICULOS\n" +
                "\n" +
                "Pelo presente instrumento particular de Contrato de Locação de Veículos tendo de um lado doravante como CONTRATANTE o Sr.———————,CI: n.º ————e CPF: —————, residente à Rua —————————————–, convenciona e contrata o seguinte: LOCAÇÃO DE UM VEÍCULO modelo xxxx – PLACA xxx 0000 cidade\n" +
                "– por um período de xx (xxxxxxxx ) dias, no valor de R$ (xxxxxx reais) mensais, tendo inicio no dia (dia) de (mês) de (ano) à (dia) de (mês) de (ano), podendo o referido contrato ser renovado, caso haja interesse de ambas as partes. Todas as despesas para manutenção e conservação do referido veículo correrá por conta do contratante. Para firmeza e prova de assim haverem contratado os serviços acima, assinam em 02(Duas) vias de igual teor.\n" +
                "\n" +
                "(cidade – UF), (dia) de (mês) de (ano).\n";
    }
}
