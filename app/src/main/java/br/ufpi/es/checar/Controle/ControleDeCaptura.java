package br.ufpi.es.checar.Controle;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * Created by na7an on 01/02/2016.
 */
public class ControleDeCaptura {


    private static final String DATA_PATH = Environment
            .getExternalStorageDirectory().toString() + "/Checar/";

    private static final String lang = "por";

    private static final String TAG = "Checar.java";

    private static Bitmap image;

    private static String imagePath;

    /**
     * Cria o diretório do arquivo de treinamento do tesseract no dispositivo móvel
     * @author Natanael
     * @return void
     */
    public void createOCRDirectory(){

        String[] paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };

        for (String path : paths) {
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.v(TAG, "ERROR: Creation of directory " + path + " on sdcard failed");
                    return;
                } else {
                    Log.v(TAG, "Created directory " + path + " on sdcard");
                }
            }

        }

    }

    /**
     * Cria o arquivo de treinamento do tesseract
     * @author Natanael
     * @param context
     * @return void
     */
    public void createOCRTrainedLang(Context context){

        if (!(new File(DATA_PATH + "tessdata/" + lang + ".traineddata")).exists()) {
            try {

                AssetManager assetManager = context.getAssets();
                InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
                //GZIPInputStream gin = new GZIPInputStream(in);
                OutputStream out = new FileOutputStream(DATA_PATH
                        + "tessdata/" + lang + ".traineddata");

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                //while ((lenf = gin.read(buff)) > 0) {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                //gin.close();
                out.close();

                Log.v(TAG, "Copied " + lang + " traineddata");
            } catch (IOException e) {
                Log.e(TAG, "Was unable to copy " + lang + " traineddata " + e.toString());
            }
        }



    }

    /**
     * Define o caminho a salvar a imagem retornada pela camera do dispositivo móvel
     * @author Natanael
     * @return void
     */

    public void setImagePath() {

        Log.v(TAG, "Caminho imagem: " + DATA_PATH + "ocr.jpg");


        ControleDeCaptura.imagePath = DATA_PATH + "ocr.jpg";
    }

    /**
     * Retorna o caminho da última imagem salva no dispositivo móvel
     * @author Natanael
     * @return String
     */

    public String getImagePath() {

        return ControleDeCaptura.imagePath;
    }

    /**
     * Cria uma versão simplificada da imagem original para economizar memória do dispositivo móvel
     * @author Natanael
     * @return Bitmap
     */
    public Bitmap decodeBitmap(){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap bitmap = BitmapFactory.decodeFile(ControleDeCaptura.imagePath, options);

        try {
            ExifInterface exif = new ExifInterface(ControleDeCaptura.imagePath);
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            Log.v(TAG, "Orient: " + exifOrientation);

            int rotate = 0;

            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
            }

            Log.v(TAG, "Rotation: " + rotate);

            if (rotate != 0) {

                // Getting width & height of the given image.
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                // Setting pre rotate
                Matrix mtx = new Matrix();
                mtx.preRotate(rotate);

                // Rotating Bitmap
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
            }

            // Convert to ARGB_8888, required by tess
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        } catch (IOException e) {
            Log.e(TAG, "Couldn't correct orientation: " + e.toString());
        }

        return bitmap;
    }

    /**
     * Utiliza a biblioteca tesseract para o reconhecimento de caracteres de uma imagem
     * @author Natanael
     * @return String
     */

    public String OCR(){


        //Bitmap bitmap = decodeBitmap();

        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        baseApi.init(DATA_PATH, lang);
        baseApi.setImage(ControleDeCaptura.image);

        String recognizedText = baseApi.getUTF8Text();

        baseApi.end();

        // You now have the text in recognizedText var, you can do anything with it.
        // We will display a stripped out trimmed alpha-numeric version of it (if lang is eng)
        // so that garbage doesn't make it to the display.

        Log.v(TAG, "OCRED TEXT: " + recognizedText);

        if ( lang.equalsIgnoreCase("por") ) {
            recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
        }

        recognizedText = recognizedText.trim();

        //if ( recognizedText.length() != 0 ) {


            //_field.setText(_field.getText().toString().length() == 0 ? recognizedText : _field.getText() + " " + recognizedText);
            //_field.setSelection(_field.getText().toString().length());


        //}

        return recognizedText;

    }

    /**
     * Define a imagem de trabalho atual
     * @author Natanael
     * @param image
     * @return void
     */
    public void setImage(Bitmap image){
        ControleDeCaptura.image = image;
    }

}