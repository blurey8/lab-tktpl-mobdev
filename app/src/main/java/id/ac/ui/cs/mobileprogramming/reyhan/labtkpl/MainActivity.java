package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.model.WifiData;
import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.service.ApiClient;
import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.service.PipedreamService;
import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.util.PermissionHelper;
import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.view.ScanResultAdapter;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LAB 5";
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 123;
    WifiManager wifiManager;
    PipedreamService service = ApiClient.getRetrofitInstance().create(PipedreamService.class);
    List<ScanResult> results;

    BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context c, Intent intent) {
            boolean success = intent.getBooleanExtra(
                    WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanSuccess();
            } else {
                Log.d(TAG, "FROM EXTRA_RESULTS_UPDATED");
                scanFailure();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Wifi Scanner");
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();

        PermissionHelper.askLocationPermission(context, this,
                PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);

        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            Log.d(TAG, "FROM START SCAN");
            scanFailure();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "FROM onRequestPermissionsResult");
            scanSuccess();
        }
    }

    private void scanSuccess() {
        List<ScanResult> newResults = wifiManager.getScanResults();
        generateDataList(newResults);
        if (results != null && newResults.size() == results.size()) {
            results = newResults;
            Log.d(TAG, "Still " + results.size());
            return;
        }

        results = newResults;

        Log.d(TAG, "SCAN SUCCEED | " + results.size() + " found");
        for (final ScanResult result : results) {
            Log.d(TAG, result.SSID);
            int level = WifiManager.calculateSignalLevel(result.level, 5);
            Call<WifiData> call = service.postData(new WifiData(result.SSID, result.BSSID, level));
            call.enqueue(new Callback<WifiData>() {
                @Override
                public void onResponse(Call<WifiData> call, retrofit2.Response<WifiData> response) {
                    Log.d(TAG, result.SSID + " SENT");
                    Log.d(TAG, String.valueOf(response.body()));
                }

                @Override
                public void onFailure(Call<WifiData> call, Throwable t) {
                    Log.d(TAG, result.SSID + " FAILED");
                    Log.d(TAG, Arrays.toString(t.getStackTrace()));
                }
            });
        }
    }

    private void scanFailure() {
        Log.d(TAG, "SCAN FAILED");
        List<ScanResult> results = wifiManager.getScanResults();
        for (ScanResult result : results) {
            Log.d(TAG, result.toString());
        }
    }

    private void generateDataList(List<ScanResult> scanResults) {
        RecyclerView recyclerView = findViewById(R.id.wifiRecyclerView);
        ScanResultAdapter adapter = new ScanResultAdapter(this, scanResults);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
