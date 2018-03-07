package com.zennex.trl3lg.presentation.common.di.activitybindings;

import com.zennex.trl3lg.presentation.common.view.BaseActivity;

import dagger.MapKey;

/**
 * Created by nikita on 07.07.17.
 */
@MapKey
public @interface ActivityKey {

    Class<? extends BaseActivity> value();

}
