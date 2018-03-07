package com.zennex.trl3lg.presentation.common.di.presenterbindings;

/**
 * Created by Nikita on 05.05.2017.
 */

public interface PresenterComponentBuilder<M extends PresenterModule, C extends PresenterComponent> {

    PresenterComponentBuilder<M, C> presenterModule(M module);

    C build();

}
