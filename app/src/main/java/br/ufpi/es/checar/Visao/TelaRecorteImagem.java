package br.ufpi.es.checar.Visao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.edmodo.cropper.CropImageView;

import Catalano.Imaging.Concurrent.Filters.BernsenThreshold;
import Catalano.Imaging.Concurrent.Filters.BradleyLocalThreshold;
import Catalano.Imaging.Concurrent.Filters.Erosion;
import Catalano.Imaging.Concurrent.Filters.Grayscale;
import Catalano.Imaging.Concurrent.Filters.MaximumEntropyThreshold;
import Catalano.Imaging.Concurrent.Filters.Opening;
import Catalano.Imaging.Concurrent.Filters.OtsuThreshold;
import Catalano.Imaging.Concurrent.Filters.SauvolaThreshold;
import Catalano.Imaging.Concurrent.Filters.SobelEdgeDetector;
import Catalano.Imaging.FastBitmap;
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

        Bitmap bitmap = BitmapFactory.decodeFile(fachadaControle.getDiretorioImagem(), options);

        FastBitmap fb = new FastBitmap(bitmap);

        Grayscale g = new Grayscale();
        g.applyInPlace(fb);

        //SobelEdgeDetector sobelEdgeDetector = new SobelEdgeDetector();
        //sobelEdgeDetector.applyInPlace(fb);

        //BradleyLocalThreshold bradleyLocalThreshold = new BradleyLocalThreshold();
        //bradleyLocalThreshold.applyInPlace(fb);

        //SauvolaThreshold sauvolaThreshold = new SauvolaThreshold();
        //sauvolaThreshold.applyInPlace(fb);

        //BernsenThreshold bernsenThreshold = new BernsenThreshold();
        //bernsenThreshold.applyInPlace(fb);

        //MaximumEntropyThreshold maximumEntropyThreshold = new MaximumEntropyThreshold();
        //maximumEntropyThreshold.applyInPlace(fb);

        OtsuThreshold otsu = new OtsuThreshold();
        otsu.applyInPlace(fb);

        Opening erosion = new Opening();
        erosion.applyInPlace(fb);


        cropImageView.setImageBitmap(fb.toBitmap());

    }


    public void recortarImagem(View view){

        Bitmap bitmap = cropImageView.getCroppedImage();

        fachadaControle.setImagem(bitmap);

        String cod = this.getIntent().getStringExtra("codigo");

        if(cod.equals("a")){
            Intent intent = new Intent(this, TelaAlterarDadosCNH.class);
            startActivity(intent);

        }else if(cod.equals("b")){
            Intent intent = new Intent(this, TelaAlterarDadosPlaca.class);
            startActivity(intent);

        }
        //cropImageView.setImageBitmap(bitmap);
    }
}
