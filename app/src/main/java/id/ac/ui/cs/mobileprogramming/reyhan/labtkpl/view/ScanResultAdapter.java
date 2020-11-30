package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.view;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.R;

public class ScanResultAdapter extends RecyclerView.Adapter<ScanResultAdapter.CustomViewHolder> {

    private List<ScanResult> dataList;
    private Context context;

    public ScanResultAdapter(Context context, List<ScanResult> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView ssidView;
        private TextView bssidView;
        private TextView levelView;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            ssidView = mView.findViewById(R.id.ssid);
            bssidView = mView.findViewById(R.id.bssid);
            levelView = mView.findViewById(R.id.level);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.wifi_data_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ScanResult wifiData = dataList.get(position);
        holder.ssidView.setText(wifiData.SSID);
        holder.bssidView.setText(wifiData.BSSID);
        int level = WifiManager.calculateSignalLevel(wifiData.level, 5);
        String levelBar = String.format("%0" + level + "d", 0).replace("0", " █") + " \uD83D\uDCF6";
        holder.levelView.setText(levelBar);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
