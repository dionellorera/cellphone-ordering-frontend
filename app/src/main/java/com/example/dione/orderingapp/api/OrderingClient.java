package com.example.dione.orderingapp.api;

import com.example.dione.orderingapp.api.interfaces.ILogin;
import com.example.dione.orderingapp.api.interfaces.ILogs;
import com.example.dione.orderingapp.api.response_models.Login.Login;
import com.example.dione.orderingapp.api.response_models.Logs.Logs;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by dione on 11/08/2016.
 */
public class OrderingClient {
    private static final String BASE_URL = "http://192.168.1.70:8080/";

    private static OrderingClient mOrderingClient;
    private static RestAdapter mRestAdapter;

    public static OrderingClient getClient() {
        if (mOrderingClient == null)
            mOrderingClient = new OrderingClient();
        return mOrderingClient;
    }

    private OrderingClient() {
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build(); 
    }

    public void getLogsList(String token, Callback<Logs> callback) {
        ILogs iLogs = mRestAdapter.create(ILogs.class);
        iLogs.getLogs(token, callback);
    }

    public void getLoginResponse(String username, String password, Callback<Login> callback) {
        ILogin iLogs = mRestAdapter.create(ILogin.class);
        iLogs.getLogin(username, password, callback);
    }


}
