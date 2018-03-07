package com.zennex.trl3lg.presentation.common.view;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.zennex.trl3lg.presentation.common.moxy.strategy.BaseViewPresenterStrategy;

/**
 * Created by nikita on 25.12.2016.
 */

public interface IBaseView extends MvpView {

    @StateStrategyType(value = BaseViewPresenterStrategy.class, tag = BaseViewPresenterStrategy.TAG_SHOW_PENDING)
    void showPending(String message);

    @StateStrategyType(value = BaseViewPresenterStrategy.class, tag = BaseViewPresenterStrategy.TAG_SHOW_PENDING)
    void showPending(@StringRes int id);

    @StateStrategyType(value = BaseViewPresenterStrategy.class, tag = BaseViewPresenterStrategy.TAG_HIDE_PENDING)
    void hidePending();

    @StateStrategyType(value = SkipStrategy.class)
    void prepareScreen();

    @StateStrategyType(value = SkipStrategy.class)
    void showLongToast(String message);

    @StateStrategyType(value = SkipStrategy.class)
    void showShortToast(String message);

    @StateStrategyType(value = SkipStrategy.class)
    void showLongToast(@StringRes int stringRes);

    @StateStrategyType(value = SkipStrategy.class)
    void showShortToast(@StringRes int stringRes);

    // region title

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setTitleText(@StringRes int titleText);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setTitleText(String title);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setTitleImage(String url);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setVisibilityBackButton(boolean value);

    // endregion

}
