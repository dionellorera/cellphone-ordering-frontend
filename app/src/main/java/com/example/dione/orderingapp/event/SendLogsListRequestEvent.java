package com.example.dione.orderingapp.event;

import com.example.dione.orderingapp.api.response_models.Logs.Logs;

/**
 * Created by dione on 11/08/2016.
 */
public class SendLogsListRequestEvent {
    private Logs mLogs;
    public SendLogsListRequestEvent(Logs logs) {
        this.mLogs = logs;
    }
    public Logs getLogs() {
        return mLogs;
    }
}
