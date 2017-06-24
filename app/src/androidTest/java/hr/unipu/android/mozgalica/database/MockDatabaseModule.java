package hr.unipu.android.mozgalica.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.unipu.android.mozgalica.database.AnswerDataSource;
import hr.unipu.android.mozgalica.database.CategoryDataSource;
import hr.unipu.android.mozgalica.database.ChallengeDataSource;
import hr.unipu.android.mozgalica.database.CompletionDataSource;
import hr.unipu.android.mozgalica.database.SettingsDataSource;
import hr.unipu.android.mozgalica.database.UserDataSource;
import hr.unipu.android.mozgalica.model.DaoMaster;
import hr.unipu.android.mozgalica.model.DaoSession;

@Module
public class MockDatabaseModule {
    DaoMaster.DevOpenHelper mDevOpenHelper;
    SQLiteDatabase mDatabase;

    public MockDatabaseModule(Context context, String databaseName) {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, databaseName, null);
        mDatabase = mDevOpenHelper.getWritableDatabase();
    }

    @Provides
    @Singleton
    DaoSession provideSession() {
        DaoMaster daoMaster = new DaoMaster(mDatabase);
        return daoMaster.newSession();
    }

    @Provides
    @Singleton
    SQLiteDatabase provideDatabase() {
        return mDevOpenHelper.getWritableDatabase();
    }

    @Provides
    @Singleton
    AnswerDataSource provideAnswerDataSource(DaoSession session) {
        return new AnswerDataSource(session);
    }

    @Provides
    @Singleton
    ChallengeDataSource provideChallengeDataSource(DaoSession session) {
        return new ChallengeDataSource(session);
    }

    @Provides
    @Singleton
    CompletionDataSource provideCompletionDataSource(DaoSession session) {
        return new CompletionDataSource(session);
    }

    @Provides
    @Singleton
    SettingsDataSource provideSettingsDataSource(DaoSession session) {
        return new SettingsDataSource(session);
    }

    @Provides
    @Singleton
    UserDataSource provideUserDataSource(DaoSession session, SettingsDataSource settingsDataSource) {
        return new UserDataSource(session, settingsDataSource);
    }

    @Provides
    @Singleton
    CategoryDataSource provideCategoryDataSource(DaoSession session) {
        return Mockito.mock(CategoryDataSource.class);
    }
}
