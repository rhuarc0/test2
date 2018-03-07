package com.zennex.trl3lg.presentation.common.di.activitybindings;

import com.zennex.trl3lg.presentation.common.view.BaseActivity;

/**
 * Created by nikita on 07.07.17.
 */

public interface HasActivitySubcomponentBuilders {

    ActivityComponentBuilder getActivityComponentBuilder(Class<? extends BaseActivity> presenterClass);

}
