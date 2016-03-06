package br.ufpi.es.checar.Visao;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.ufpi.es.checar.Controle.FachadaControle;
import br.ufpi.es.checar.Persistencia.ControleBanco;
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


        //Buscar informações específicas no banco de dados a partir de um determinado CPF

    }

    public void telaNovaPlaca(View view){
        Intent intent = new Intent(this,TelaNovaPlaca.class);
        intent.putExtra("cpf", nome);
        startActivity(intent);
    }
}