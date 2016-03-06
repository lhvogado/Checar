package br.ufpi.es.checar.Visao;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.ufpi.es.checar.Persistencia.ControleBanco;
import br.ufpi.es.checar.R;
public class TelaCarregaDadosVeiculo extends AppCompatActivity {
    private ListView lista;
    private static final String TAG = "Checar.java";
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tela_carrega_dados_veiculo);
        // Recebe o parâmetro enviado pela tela anterior e armazena na String placa
            String placa = this.getIntent().getStringExtra("placa");
        // Cria uma nova conexão com o banco de dados
            ControleBanco bd = new ControleBanco(getBaseContext());
        // Declaração do cursor
        //String teste = "APL234";
            //Cursor cursor1 = bd.BuscaPlaca(placa);
        Cursor cursor1 = bd.ListarCarros();
        // Array de strings com os campos do banco de dados a serem mostrados
            String[] nomeCampos = new String[]{"Placa","Fabricante","Modelo","Versao","Ano","Cor","Motor"};
        // Conecta as Id's de cada campo com a lista a ser exibida
            int[] idViews = new int[]{
                    R.id.idPlacaLista, R.id.idFabricanteLista,R.id.idModeloLista,
                    R.id.idVersaoLista, R.id.idAnoLista, R.id.idCorLista,
                    R.id.idMotorLista
            };
            SimpleCursorAdapter adp = new SimpleCursorAdapter(getBaseContext(),R.layout.placa_layout, cursor1, nomeCampos, idViews, 0);
            lista = (ListView) findViewById(R.id.idListaVeiculos);
            lista.setAdapter(adp);
        }
    }

//
//