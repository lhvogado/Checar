package br.ufpi.es.checar.Visao;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.ufpi.es.checar.Persistencia.ControleBanco;
import br.ufpi.es.checar.R;

public class carregaDados extends AppCompatActivity {
    private static final String TAG = "Checar.java";
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrega_dados);
        String cpf = this.getIntent().getStringExtra("CPF");
        ControleBanco bd = new ControleBanco(getBaseContext());

        //Cursor cursor = bd.carregaDados();
        Cursor cursor1 = bd.carregaDadoById(cpf);

        String[] nomeCampos = new String[]{"CPF","RG","Nome"};

        int[] idViews = new int[]{
               R.id.idCPFLista, R.id.idRGLista, R.id.idNome
        };
        SimpleCursorAdapter adp = new SimpleCursorAdapter(getBaseContext(),R.layout.cpf_layout, cursor1, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.idLista);

        lista.setAdapter(adp);
    }
}
