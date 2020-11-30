package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.service;

import id.ac.ui.cs.mobileprogramming.reyhan.labtkpl.model.WifiData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PipedreamService {
    @POST("/")
    Call<WifiData> postData(@Body WifiData wifiData);
}
