package com.zennex.trl3lg.presentation.common.di.app;

import android.content.Context;

import com.zennex.trl3lg.presentation.common.di.activitybindings.ActivityBindingModule;
import com.zennex.trl3lg.presentation.common.di.activitybindings.ActivityComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.datastore.DataStoreModule;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterBindingModule;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.module.app.App;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        PresenterBindingModule.class,
        DataStoreModule.class,
        RxSchedulerModule.class})

public interface IAppComponent {

    Context context();

    App inject(App application);

}
