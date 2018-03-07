package com.zennex.trl3lg.presentation.common.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Nikita on 12.04.2017.
 */
@Scope
@Retention(RUNTIME)
public @interface CurrentUserScope {
}
