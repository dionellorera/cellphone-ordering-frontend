package com.example.dione.orderingapp.application;

import android.app.Application;

import com.example.dione.orderingapp.bus.BusProvider;
import com.example.dione.orderingapp.manager.OrderingManager;
import com.orm.SugarContext;
import com.squareup.otto.Bus;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by dione on 11/08/2016.
 */
public class OrderingApplication extends Application {
    private OrderingManager mOrderingManager;
    public Bus mBus = BusProvider.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();
        mOrderingManager = new OrderingManager(this, mBus);
        mBus.register(mOrderingManager);
        mBus.register(this);
        SugarContext.init(this);
        JodaTimeAndroid.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
