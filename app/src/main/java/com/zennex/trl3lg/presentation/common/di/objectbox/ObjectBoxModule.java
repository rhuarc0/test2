package com.zennex.trl3lg.presentation.common.di.objectbox;

import com.zennex.trl3lg.presentation.common.di.app.AppModule;

import dagger.Module;


/**
 * Created by nikita on 25.12.2016.
 */
@Module(includes = AppModule.class)
public class ObjectBoxModule {

    private final static String MY_OBJECT_BOX = "myhomeobjectbox";
    public final static String QUALIFER_MY_HOME_REALM_CONFIG = "my_home_config";


   /* @NonNull
    @Provides
    public BoxStore provideObjectBox(Context context) {
        return MyObjectBox.builder()
                .androidContext(context)
                .name(MY_OBJECT_BOX)
                .build();
    }
*/

}
