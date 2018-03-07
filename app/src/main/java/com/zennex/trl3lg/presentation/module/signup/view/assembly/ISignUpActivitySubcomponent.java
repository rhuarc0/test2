package com.zennex.trl3lg.presentation.module.signup.view.assembly;

import com.zennex.trl3lg.presentation.common.di.activitybindings.ActivityComponent;
import com.zennex.trl3lg.presentation.common.di.activitybindings.ActivityComponentBuilder;
import com.zennex.trl3lg.presentation.module.signup.view.SignUpActivity;

import dagger.Subcomponent;

/**
 * Created by nikita on 07.07.17.
 */
@Subcomponent(modules = SignUpActivityModule.class)
public interface ISignUpActivitySubcomponent extends ActivityComponent<SignUpActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<SignUpActivityModule, ISignUpActivitySubcomponent> {

    }


}
