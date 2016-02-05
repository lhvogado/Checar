package br.ufpi.es.checar.Controle;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by na7an on 05/02/2016.
 */
public class ControleUtil {

    public void requestUserPermission(Context context, Activity activity, final String PERMISSION, final int REQUEST_CODE){

        String strPermission = "Manifest.permission." + PERMISSION;

        if (ContextCompat.checkSelfPermission(context,
                strPermission)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(activity,
                    new String[]{strPermission},
                    REQUEST_CODE);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.

        }


    }



}
