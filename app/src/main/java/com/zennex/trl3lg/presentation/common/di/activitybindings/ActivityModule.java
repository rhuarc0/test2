package com.zennex.trl3lg.presentation.common.di.activitybindings;

import com.zennex.trl3lg.presentation.common.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nikita on 07.07.17.
 */
@Module
public abstract class ActivityModule<Activity> {

    protected final Activity mActivity;

    protected ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
