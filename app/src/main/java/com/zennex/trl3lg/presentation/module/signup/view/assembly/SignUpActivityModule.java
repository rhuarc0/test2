package com.zennex.trl3lg.presentation.module.signup.view.assembly;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.di.activitybindings.ActivityModule;
import com.zennex.trl3lg.presentation.module.signup.view.SignUpActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nikita on 07.07.17.
 */
@Module
public class SignUpActivityModule extends ActivityModule<SignUpActivity> {

    public SignUpActivityModule(SignUpActivity signUpActivity) {
        super(signUpActivity);
    }


    @NonNull
    @Provides
    AppCompatActivity provideAppAcompactActivity() {
        return mActivity;
    }

    @NonNull
    @Provides
    @Named("ContentInputInit")
    Animation provideContentInputInitAnim() {
        return AnimationUtils.loadAnimation(mActivity, R.anim.act_sign_up_content_input_init);
    }


    @NonNull
    @Provides
    @Named("BtnInit")
    Animation provideBtnInitAnim() {
        return AnimationUtils.loadAnimation(mActivity, R.anim.act_sign_up_btn_init);
    }


}
