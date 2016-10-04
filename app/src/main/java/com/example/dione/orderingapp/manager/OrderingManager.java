package com.example.dione.orderingapp.manager;

import android.content.Context;

import com.example.dione.orderingapp.api.OrderingClient;
import com.example.dione.orderingapp.api.response_models.Login.Login;
import com.example.dione.orderingapp.api.response_models.Logs.Logs;
import com.example.dione.orderingapp.event.GetLoginResponseEvent;
import com.example.dione.orderingapp.event.GetLogsListResponseEvent;
import com.example.dione.orderingapp.event.SendLoginRequestEvent;
import com.example.dione.orderingapp.event.SendLogsListRequestEvent;
import com.example.dione.orderingapp.event.SendWeatherEventError;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by dione on 11/08/2016.
 */
public class OrderingManager {
    private Context mContext;
    private Bus mBus;
    private OrderingClient sOrderingClient;
    public OrderingManager(Context context, Bus bus) {
        this.mContext = context;
        this.mBus = bus;
        sOrderingClient = OrderingClient.getClient();
    }

    @Subscribe
    public void onGetAttendanceList(GetLogsListResponseEvent getLogsListResponseEvent) {
        Callback<Logs> callback = new Callback<Logs>() {
            @Override
            public void success(Logs weather, retrofit.client.Response response) {
                mBus.post(new SendLogsListRequestEvent(weather));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new SendWeatherEventError(error));
            }
        };
        sOrderingClient.getLogsList(getLogsListResponseEvent.getToken(), callback);
    }

    @Subscribe
    public void onGetLoginResponse(GetLoginResponseEvent getLoginResponseEvent) {
        Callback<Login> callback = new Callback<Login>() {
            @Override
            public void success(Login login, retrofit.client.Response response) {
                mBus.post(new SendLoginRequestEvent(login));
            }
            @Override
            public void failure(RetrofitError error) {
                Login login = new Login();
                SendLoginRequestEvent sendLoginRequestEvent = new SendLoginRequestEvent(login);
                sendLoginRequestEvent.getmLogin().setMessage("Failed to Login");
                mBus.post(sendLoginRequestEvent);
            }
        };
        sOrderingClient.getLoginResponse(getLoginResponseEvent.getUsername(), getLoginResponseEvent.getPassword(), callback);
    }

}
