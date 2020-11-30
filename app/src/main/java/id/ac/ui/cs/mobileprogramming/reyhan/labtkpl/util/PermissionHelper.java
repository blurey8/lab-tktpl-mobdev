package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

public class PermissionHelper {
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 123;

    public static void askLocationPermission(Context context, Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    requestCode);
        }
    }

    public static void askLocationPermission(Context context, Activity activity) {
        askLocationPermission(context, activity, PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
    }
}
