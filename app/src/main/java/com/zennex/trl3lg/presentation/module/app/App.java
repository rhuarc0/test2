package com.zennex.trl3lg.presentation.module.app;

import android.app.Application;
import android.content.Context;

import com.zennex.trl3lg.presentation.common.di.activitybindings.ActivityComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.activitybindings.HasActivitySubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.di.app.AppModule;
import com.zennex.trl3lg.presentation.common.di.app.DaggerIAppComponent;
import com.zennex.trl3lg.presentation.common.di.network.NetworkModule;
import com.zennex.trl3lg.presentation.common.di.objectbox.ObjectBoxModule;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.repository.LocalRepositoryModule;
import com.zennex.trl3lg.presentation.common.di.repository.WebRepositoryModule;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.BaseActivity;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class App extends Application implements HasPresenterSubcomponentBuilders, HasActivitySubcomponentBuilders {

    public static final String TAG = "App";

    @Inject
    Map<Class<? extends ViperBasePresenter>, Provider<PresenterComponentBuilder>> mPresenterComponentBuilders;

    @Inject
    Map<Class<? extends BaseActivity>, Provider<ActivityComponentBuilder>> mActivityComponentBuilders;

    @Override
    public void onCreate() {
        super.onCreate();
        inject(this);
    }

    public static HasPresenterSubcomponentBuilders getHasPresenterSubcomponentBuilders(Context context) {
        return ((HasPresenterSubcomponentBuilders) context.getApplicationContext());
    }

    public static HasActivitySubcomponentBuilders getHasActivitySubcomponentBuilders(Context context) {
        return ((HasActivitySubcomponentBuilders) context.getApplicationContext());
    }



    public void inject(Context context) {
        DaggerIAppComponent.builder()
                .appModule(new AppModule(context))
                .networkModule(new NetworkModule())
                .localRepositoryModule(new LocalRepositoryModule())
                .webRepositoryModule(new WebRepositoryModule())
                .objectBoxModule(new ObjectBoxModule())
                .rxSchedulerModule(new RxSchedulerModule())
                .build()
                .inject(this);
    }


    @Override
    public PresenterComponentBuilder getPresenterComponentBuilder(Class<? extends ViperBasePresenter> presenterClass) {
        return mPresenterComponentBuilders.get(presenterClass).get();
    }

    @Override
    public ActivityComponentBuilder getActivityComponentBuilder(Class<? extends BaseActivity> presenterClass) {
        return mActivityComponentBuilders.get(presenterClass).get();
    }
}
