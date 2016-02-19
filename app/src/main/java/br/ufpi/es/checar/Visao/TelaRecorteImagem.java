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
import Catalano.Imaging.Concurrent.Filters.Closing;
import Catalano.Imaging.Concurrent.Filters.Dilatation;
import Catalano.Imaging.Concurrent.Filters.Erosion;
import Catalano.Imaging.Concurrent.Filters.Grayscale;
import Catalano.Imaging.Concurrent.Filters.HysteresisThreshold;
import Catalano.Imaging.Concurrent.Filters.ImageNormalization;
import Catalano.Imaging.Concurrent.Filters.MaximumEntropyThreshold;
import Catalano.Imaging.Concurrent.Filters.NiblackThreshold;
import Catalano.Imaging.Concurrent.Filters.Opening;
import Catalano.Imaging.Concurrent.Filters.OtsuThreshold;
import Catalano.Imaging.Concurrent.Filters.RosinThreshold;
import Catalano.Imaging.Concurrent.Filters.SISThreshold;
import Catalano.Imaging.Concurrent.Filters.SauvolaThreshold;
import Catalano.Imaging.Concurrent.Filters.SobelEdgeDetector;
import Catalano.Imaging.Concurrent.Filters.Threshold;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.AdaptiveContrastEnhancement;
import Catalano.Imaging.Filters.ArtifactsRemoval;
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

        OtsuThreshold otsu = new OtsuThreshold();
        otsu.applyInPlace(fb);

        //MaximumEntropyThreshold maximumEntropyThreshold = new MaximumEntropyThreshold();
        //maximumEntropyThreshold.applyInPlace(fb);

        //SISThreshold sisThreshold = new SISThreshold();
        //sisThreshold.applyInPlace(fb);


        //HysteresisThreshold hysteresisThreshold = new HysteresisThreshold();
        //hysteresisThreshold.applyInPlace(fb);

        //Opening opening = new Opening();
        //opening.applyInPlace(fb);

        //Closing closing = new Closing();
        //closing.applyInPlace(fb);


        cropImageView.setImageBitmap(fb.toBitmap());
        //cropImageView.setImageBitmap(bitmap);

    }


    /**
     * Realiza o recorte de uma imagem da camera do dispositivo móvel
     * @author Natanael
     * @param view
     * @return void
     */
    public void recortarImagem(View view){

        Bitmap bitmap = cropImageView.getCroppedImage();

        fachadaControle.setImagem(bitmap);

        String cod = this.getIntent().getStringExtra("codigo");

        if(cod.equals("telaNovoCNH")){
            Intent intent = new Intent(this, TelaAlterarDadosCNH.class);
            startActivity(intent);

        }else if(cod.equals("telaNovaPlaca")){
            Intent intent = new Intent(this, TelaAlterarDadosPlaca.class);
            startActivity(intent);

        }
        //cropImageView.setImageBitmap(bitmap);
    }
}
