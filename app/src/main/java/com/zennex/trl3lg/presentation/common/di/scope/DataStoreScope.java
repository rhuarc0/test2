package com.zennex.trl3lg.presentation.common.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nikita on 26.12.2016.
 */
@Scope
@Retention(RUNTIME)
public @interface DataStoreScope {
}
