package com.zennex.trl3lg.presentation.common.moxy.strategy;

import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

import java.util.Iterator;
import java.util.List;

/**
 * Created by nikita on 02.07.17.
 */

public class BaseViewPresenterStrategy implements StateStrategy {

    public static final String TAG_SHOW_PENDING = "show_pending";
    public static final String TAG_HIDE_PENDING = "hide_pending";

    @Override
    public <View extends MvpView> void beforeApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {

        if (incomingCommand.getTag().equals(TAG_SHOW_PENDING)) {
            removePendingViewCommand(currentState);
            currentState.add(incomingCommand);
        } else if (incomingCommand.getTag().contains(TAG_HIDE_PENDING)) {
            removePendingViewCommand(currentState);
        }

    }

    @Override
    public <View extends MvpView> void afterApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {
        //do nothing
    }

    private <View extends MvpView> void removePendingViewCommand(List<ViewCommand<View>> currentState) {
        for (Iterator<ViewCommand<View>> iterator = currentState.iterator(); iterator.hasNext(); ) {
            ViewCommand viewCommand = iterator.next();
            if ((viewCommand.getTag().equals(TAG_SHOW_PENDING)) ||
                    (viewCommand.getTag().equals(TAG_HIDE_PENDING))) {
                iterator.remove();
                break;
            }
        }
    }
}
