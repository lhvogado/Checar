package br.ufpi.es.checar.Visao;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import br.ufpi.es.checar.Controle.FachadaControle;
import br.ufpi.es.checar.R;

public class TelaPrincipal extends AppCompatActivity {

    FachadaControle fachadaControle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fachadaControle = new FachadaControle();

        fachadaControle.criarDiretorioOCR();

        fachadaControle.criarArquivoLinguagemOCR(this);




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        // TOOLBAR
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        //Disabilitar Back Icon ActionBar
        assert ab != null;
        ab.setDefaultDisplayHomeAsUpEnabled(false);
        //Disabilitar nome App ActionBar
        ab.setDisplayShowTitleEnabled(false);

        fachadaControle.setDiretorioImagem();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView =
//                (SearchView) MenuItemCompat.getActionView(searchItem);

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_settings:
//                // User chose the "Settings" item, show the app settings UI...
//                return true;

            case R.id.action_favorite:

                Toast.makeText(TelaPrincipal.this, "Sou um Button sem Configuracao :(", Toast.LENGTH_SHORT).show();

                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void Telacnh(View view){
        Intent intent = new Intent(this, TelaNovoCNH.class);
        startActivity(intent);
    }


    public void TesteButton(View view){
        Toast.makeText(TelaPrincipal.this, "Sou um Button sem Configuracao :(", Toast.LENGTH_SHORT).show();
    }
}
