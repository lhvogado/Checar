package br.ufpi.es.checar.Controle.ControleUtil;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

/**
 * Created by na7an on 01/02/2016.
 */
public class CameraUtil {

    private static final String TAG = "DBG_" + CameraUtil.class.getName();

    public static boolean hasCameraHardware(Context context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS);
    }

    public static Camera getCamera(){
        try{
            return Camera.open();
        }
        catch(Exception e) {
            Log.e(TAG, "Não foi possível iniciar a câmera");
            return null;
        }
    }
}