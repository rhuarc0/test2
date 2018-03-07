package com.zennex.trl3lg.presentation.common.di.activitybindings;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;

/**
 * Created by nikita on 07.07.17.
 */

public interface ActivityComponentBuilder <M extends ActivityModule, C extends ActivityComponent> {

    ActivityComponentBuilder<M, C> presenterModule(M module);

    C build();
}
