package br.ufpi.es.checar.Controle;

import android.hardware.Camera;
import android.view.SurfaceHolder;

/**
 * Created by na7an on 13/01/2016.
 */
public class FachadaControle {

    public ControladorCamera meuControladorCamera;
    //private ControleDePessoa meuControleDePessoa;

    public void getInstanciaControladorCamera(SurfaceHolder holder){

        this.meuControladorCamera = ControladorCamera.New(holder);
    }

    public boolean isCameraLigada(){

        if(this.meuControladorCamera != null){
            if(this.meuControladorCamera.isOn()) {
                return true;
            }
        }

        return false;
    }

    public boolean isCameraDesligada(){

        if(this.meuControladorCamera != null){
            if(this.meuControladorCamera.isOn()) {
                return true;
            }
        }

        return false;
    }

    public void solicitarFoco(){

        this.meuControladorCamera.requestFocus();

    }

    public void capturarFoto(Camera.ShutterCallback shutterCallback,
                             Camera.PictureCallback rawPictureCallback,
                             Camera.PictureCallback jpegPictureCallback){

        this.meuControladorCamera.takeShot(shutterCallback, rawPictureCallback, jpegPictureCallback);

    }

    public void iniciarCamera (){

        this.meuControladorCamera.start();
    }

    public void pararCamera (){

        this.meuControladorCamera.stop();
    }
}