package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.model;

import com.google.gson.annotations.SerializedName;

public class WifiData {
    private String SSID;
    private String BSSID;
    private int level;

    public WifiData(String SSID, String BSSID, int level) {
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.level = level;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
