package br.ufpi.es.checar.Visao;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.ufpi.es.checar.Persistencia.ControleBanco;
import br.ufpi.es.checar.R;

public class TelaCarregaDados extends AppCompatActivity {
    private static final String TAG = "Checar.java";
    private ListView lista1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrega_dados);
        String cpf = this.getIntent().getStringExtra("cpf");
        // Cria uma nova conexão com o banco de dados
        ControleBanco bd = new ControleBanco(getBaseContext());
        // Declaração do cursor
        String dado = "ABC";
        Cursor cursor1 = bd.carregaDadoById(cpf);
        // Array de strings com os campos do banco de dados a serem mostrados
        String[] nomeCampos = new String[]{"CPF","RG","Nome"};
        // Conecta as Id's de cada campo com a lista a ser exibida
        int[] idViews = new int[]{
               R.id.idCPFLista, R.id.idRGLista, R.id.idNome
        };
        SimpleCursorAdapter adap = new SimpleCursorAdapter(getBaseContext(),R.layout.cpf_layout, cursor1, nomeCampos, idViews, 0);
        lista1 = (ListView) findViewById(R.id.idLista);
        lista1.setAdapter(adap);
    }

}
