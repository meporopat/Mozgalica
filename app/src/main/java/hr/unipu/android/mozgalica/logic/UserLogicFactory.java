package hr.unipu.android.mozgalica.logic;

import javax.inject.Inject;

import hr.unipu.android.mozgalica.BrainPhaserApplication;
import hr.unipu.android.mozgalica.database.ChallengeDataSource;
import hr.unipu.android.mozgalica.database.CompletionDataSource;
import hr.unipu.android.mozgalica.database.StatisticsDataSource;
import hr.unipu.android.mozgalica.logic.statistics.ChartDataLogic;
import hr.unipu.android.mozgalica.logic.statistics.ChartSettings;
import hr.unipu.android.mozgalica.logic.statistics.StatisticsLogic;
import hr.unipu.android.mozgalica.model.User;

/**
 *
 * Factory that is used to create logic objects which require a user.
 * Dependencies are injected automatically.
 */
public class UserLogicFactory {
    @Inject
    BrainPhaserApplication mApplication;
    @Inject
    CompletionDataSource mCompletionDataSource;
    @Inject
    ChallengeDataSource mChallengeDataSource;
    @Inject
    StatisticsDataSource mStatisticsDataSource;
    @Inject
    ChartSettings mSettings;

    /**
     * Create a DueChallengeLogic for the specified user.
     *
     * @param user user whose challenges are inspected
     * @return the DueChallengeLogic object
     */
    public DueChallengeLogic createDueChallengeLogic(User user) {
        return new DueChallengeLogic(user, mCompletionDataSource, mChallengeDataSource);
    }

    /**
     * Creates ChartDataLogic
     *
     * @param user
     * @param categoryId category to inspect
     * @return ChartDataLogic
     */
    public ChartDataLogic createChartDataLogic(User user, long categoryId) {
        return new ChartDataLogic(user,
                categoryId,
                mApplication,
                mChallengeDataSource,
                mCompletionDataSource,
                mStatisticsDataSource,
                this);
    }

    /**
     * Create a DueChallengeLogic for the specified user.
     *
     * @param user       user whose challenges are inspected
     * @param categoryId category to inspect
     * @return the DueChallengeLogic object
     */
    public StatisticsLogic createStatisticsLogic(User user, long categoryId) {
        return new StatisticsLogic(mApplication, mSettings, createChartDataLogic(user, categoryId));
    }
}
