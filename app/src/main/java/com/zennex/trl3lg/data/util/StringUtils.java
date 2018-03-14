package com.zennex.trl3lg.data.util;

import android.support.annotation.Nullable;

public class StringUtils {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

}
