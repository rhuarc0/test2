package com.zennex.trl3lg.presentation.common.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Created by nikita on 11.03.2017.
 */

public interface IBaseFragmentListener {

    void changeTitle(@StringRes int title);

    void changeTitle(String title);

    void changeTitleImage(@NonNull String url);

    void changeVisibilityBackButton(boolean value);

}
