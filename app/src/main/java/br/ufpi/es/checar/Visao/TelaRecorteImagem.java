package br.ufpi.es.checar.Visao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.edmodo.cropper.CropImageView;

import br.ufpi.es.checar.Controle.FachadaControle;
import br.ufpi.es.checar.R;

public class TelaRecorteImagem extends AppCompatActivity {

    CropImageView cropImageView;
    FachadaControle fachadaControle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_recorte_imagem);

        fachadaControle = new FachadaControle();
        cropImageView = (CropImageView) findViewById(R.id.imageView_recorte);
        //cropImageView.setFixedAspectRatio(false);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        cropImageView.setImageBitmap(BitmapFactory.decodeFile(fachadaControle.getDiretorioImagem(), options));

    }


    public void recortarImagem(View view){

        Bitmap bitmap = cropImageView.getCroppedImage();

        fachadaControle.setImagem(bitmap);

        //cropImageView.setImageBitmap(bitmap);

        Intent intent = new Intent(this, TelaAlterarDadosCNH.class);
        startActivity(intent);



    }
}
