package com.zennex.trl3lg.presentation.common.di.activitybindings;

import com.zennex.trl3lg.presentation.module.signup.view.SignUpActivity;
import com.zennex.trl3lg.presentation.module.signup.view.assembly.ISignUpActivitySubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by nikita on 07.07.17.
 */

@Module(subcomponents = {ISignUpActivitySubcomponent.class})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(SignUpActivity.class)
    public abstract ActivityComponentBuilder bindSignUpActivityComponent(ISignUpActivitySubcomponent.Builder impl);

}
