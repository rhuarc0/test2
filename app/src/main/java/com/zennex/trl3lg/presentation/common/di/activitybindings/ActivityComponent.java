package com.zennex.trl3lg.presentation.common.di.activitybindings;

import com.zennex.trl3lg.presentation.common.view.BaseActivity;

import dagger.MembersInjector;

/**
 * Created by nikita on 07.07.17.
 */

public interface ActivityComponent<Activity extends BaseActivity> extends MembersInjector<Activity> {
}
