package com.zennex.trl3lg.presentation.common.router;

import android.support.v4.app.FragmentTransaction;

/**
 * Created by Nikita on 10.04.2017.
 */

public interface IActivityRouteConductor extends IRouterAdapter {

    void commitTransaction(FragmentTransaction fragmentTransaction);

}
