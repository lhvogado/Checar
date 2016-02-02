package br.ufpi.es.checar.Controle;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

import br.ufpi.es.checar.Controle.ControleUtil.CameraUtil;

/**
 * Created by na7an on 01/02/2016.
 */
public class ControladorCamera {

    private boolean on;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private Camera.Parameters parameters;

    private static final String TAG = "DBG_" + ControladorCamera.class.getName();


    //On = Camera Ligada Off  = Camera Desligada
    public boolean isOn() {

        return on;
    }

    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {

        }
    };

    private ControladorCamera(SurfaceHolder surfaceHolder){

        this.surfaceHolder = surfaceHolder;
    }

    static public ControladorCamera New(SurfaceHolder surfaceHolder){
        Log.d(TAG, "Creating camera engine");
        return  new ControladorCamera(surfaceHolder);
    }

    public void start() {

        Log.d(TAG, "Entered CameraEngine - start()");
        this.camera = CameraUtil.getCamera();

        if (this.camera == null)
            return;

        Log.d(TAG, "Got camera hardware");

        try {

            this.camera.setPreviewDisplay(this.surfaceHolder);
            //this.camera.setDisplayOrientation(90);
            //this.camera.enableShutterSound(false);


            Camera.Parameters parameters = this.camera.getParameters();

            // Check what resolutions are supported by your camera
            List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
            Camera.Size cSize = sizes.get(0);
            for (Camera.Size size : sizes) {
                //Log.i(TAG, "Resolution: "+size.width+" "+size.height);
                if((size.width == 2592) || (size.width == 3840)){
                    if((size.height == 1944) || (size.height == 2160)){
                        cSize = size;
                        break;
                    }
                }
            }


            //Camera.Size cSize = sizes.get(sizes.size() - 1);


            Log.i(TAG, "Chosen resolution: " + cSize.width + " " + cSize.height);
            parameters.setPictureSize(cSize.width, cSize.height);
            this.camera.setParameters(parameters);


            this.camera.startPreview();



            //parameters = this.camera.getParameters();
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                this.camera.setParameters(parameters);
            }

            on = true;

            Log.d(TAG, "CameraEngine preview started");

        } catch (IOException e) {
            Log.e(TAG, "Error in setPreviewDisplay");
        }
    }

    public void stop(){

        if(camera != null){
            //this.autoFocusEngine.stop();
            //parameters = this.camera.getParameters();
            //parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            //camera.cancelAutoFocus();
            camera.release();
            camera = null;
        }

        on = false;

        Log.d(TAG, "CameraEngine Stopped");
    }

    public void requestFocus() {
        if (camera == null)
            return;

        if (isOn()) {
            camera.autoFocus(autoFocusCallback);
        }
    }

    public void takeShot(Camera.ShutterCallback shutterCallback,
                         Camera.PictureCallback rawPictureCallback,
                         Camera.PictureCallback jpegPictureCallback ){
        if(isOn()){
            camera.takePicture(shutterCallback, rawPictureCallback, jpegPictureCallback);
        }
    }
}