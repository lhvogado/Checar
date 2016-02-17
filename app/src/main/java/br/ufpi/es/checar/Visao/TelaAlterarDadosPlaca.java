package br.ufpi.es.checar.Visao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpi.es.checar.Controle.FachadaControle;
import br.ufpi.es.checar.R;

public class TelaAlterarDadosPlaca extends AppCompatActivity {

    private static final String TAG = "Checar.java";
    FachadaControle fachadaControle;
    String nome;
    private EditText _nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fachadaControle = new FachadaControle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alterar_dados_placa);




        nome = fachadaControle.OCR();
        Log.v(TAG, "TEXTO RECONHECIDO: " + nome);


        _nome = (EditText) findViewById(R.id.editText_nome2);
        _nome.setText(nome);
        //Buscar informações específicas no banco de dados a partir de um determinado CPF

    }

    public void teste(View view){
        Intent intent = new Intent(this,TelaPrincipal.class);
        startActivity(intent);
    }
}
