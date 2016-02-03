package br.ufpi.es.checar.Visao;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import br.ufpi.es.checar.Controle.FachadaControle;
import br.ufpi.es.checar.R;

public class TelaNovoCNH extends AppCompatActivity {


    private static final String TAG = "Checar.java";
    FachadaControle fachadaControle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fachadaControle = new FachadaControle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_novo_cnh);

//        // TOOLBAR
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        //Disabilitar Back Icon ActionBar
        //ab.setDefaultDisplayHomeAsUpEnabled(false);
        //Disabilitar nome App ActionBar
        ab.setDisplayShowTitleEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Log.i(TAG, "resultCode: " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(this, TelaRecorteImagem.class);
            startActivity(intent);
        } else {
            Log.v(TAG, "Cancelado Pelo Usuário");
        }
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
                Toast.makeText(TelaNovoCNH.this, "Sou um Button sem Configuracao :(", Toast.LENGTH_SHORT).show();
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    public void cameraCNH(View view){
        //Toast.makeText(TelaNovoCNH.this, "Sou um Button sem Configuracao :(", Toast.LENGTH_SHORT).show();

        File file = new File(fachadaControle.getDiretorioImagem());
        Uri outputFileUri = Uri.fromFile(file);

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(intent, 0);
    }

}