package com.example.daggertest.di;

import android.app.Application;

import com.example.daggertest.BaseApplicaton;
import com.example.daggertest.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules =  {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        ViewModelFactoryModule.class
})
public interface AppComponant extends AndroidInjector<BaseApplicaton> {

    SessionManager getSessionManager();

    @Component.Builder
    interface  Builder{
        @BindsInstance
        Builder application(Application application);

        AppComponant build();
    }
}
