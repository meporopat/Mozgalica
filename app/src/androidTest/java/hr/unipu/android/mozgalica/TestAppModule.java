package hr.unipu.android.mozgalica;

import android.app.Application;
import android.content.Context;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import hr.unipu.android.mozgalica.BrainPhaserApplication;
import hr.unipu.android.mozgalica.activities.playchallenge.AnswerFragmentFactory;
import hr.unipu.android.mozgalica.database.CompletionDataSource;
import hr.unipu.android.mozgalica.database.UserDataSource;
import hr.unipu.android.mozgalica.logic.CompletionLogic;
import hr.unipu.android.mozgalica.logic.SettingsLogic;
import hr.unipu.android.mozgalica.logic.UserLogicFactory;
import hr.unipu.android.mozgalica.logic.UserManager;


@Module
public class TestAppModule {
    BrainPhaserApplication mApplication;

    public TestAppModule(BrainPhaserApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    BrainPhaserApplication providesBpApp() {
        return mApplication;
    }

    @Provides
    @Singleton
    UserManager providesUserManager(Application application, UserDataSource userDataSource) {
        return new UserManager(application, userDataSource);
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    UserLogicFactory providesUserLogic(BrainPhaserApplication app) {
        return Mockito.mock(UserLogicFactory.class);
    }

    @Provides
    @Singleton
    SettingsLogic providesSettingsLogic() {
        return new SettingsLogic();
    }

    @Provides
    @Singleton
    AnswerFragmentFactory providesFragmentFactory() { return new AnswerFragmentFactory(); }

    @Provides
    @Singleton
    CompletionLogic providesCompletionLogic(CompletionDataSource ds) {
        return new CompletionLogic(ds);
    }
}
