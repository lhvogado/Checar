package br.ufpi.es.checar.Visao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import br.ufpi.es.checar.Controle.FachadaControle;
import br.ufpi.es.checar.R;

public class TelaAlterarDadosCNH extends AppCompatActivity {

    private static final String TAG = "Checar.java";
    FachadaControle fachadaControle;
    String nome;
    private EditText _nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fachadaControle = new FachadaControle();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alterar_dados_cnh);

        nome = fachadaControle.OCR();


        Log.v(TAG, "TEXTO RECONHECIDO: " + nome);


        _nome = (EditText) findViewById(R.id.editText_nome);
        _nome.setText(nome);

    }
}
