package hr.unipu.android.mozgalica.activities.statistics;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hr.unipu.android.mozgalica.BrainPhaserApplication;
import hr.unipu.android.mozgalica.BrainPhaserComponent;
import hr.unipu.android.mozgalica.R;
import hr.unipu.android.mozgalica.activities.BrainPhaserActivity;
import hr.unipu.android.mozgalica.activities.playchallenge.ChallengeActivity;
import hr.unipu.android.mozgalica.database.CategoryDataSource;
import hr.unipu.android.mozgalica.database.ChallengeDataSource;
import hr.unipu.android.mozgalica.database.CompletionDataSource;
import hr.unipu.android.mozgalica.database.StatisticsDataSource;
import hr.unipu.android.mozgalica.logic.UserLogicFactory;
import hr.unipu.android.mozgalica.logic.UserManager;
import hr.unipu.android.mozgalica.logic.statistics.ChartDataLogic;
import hr.unipu.android.mozgalica.logic.statistics.StatisticType;
import hr.unipu.android.mozgalica.model.User;

/**
 *
 * This is the activity showing statistics for the user in a specified category
 */
public class StatisticsActivity extends BrainPhaserActivity {
    //Constants
    private static final StatisticType[] TYPES_LANDSCAPE = new StatisticType[]{
            StatisticType.TYPE_MOST_PLAYED,
            StatisticType.TYPE_DUE,
            StatisticType.TYPE_MOST_FAILED,
            StatisticType.TYPE_STAGE,
            StatisticType.TYPE_MOST_SUCCEEDED
    };
    private static final StatisticType[] TYPES_PORTRAIT = new StatisticType[]{
            StatisticType.TYPE_DUE,
            StatisticType.TYPE_STAGE,
            StatisticType.TYPE_MOST_PLAYED,
            StatisticType.TYPE_MOST_FAILED,
            StatisticType.TYPE_MOST_SUCCEEDED
    };

    //Attributes
    @Inject
    UserManager mUserManager;
    @Inject
    UserLogicFactory mUserLogicFactory;
    @Inject
    ChallengeDataSource mChallengeDataSource;
    @Inject
    BrainPhaserApplication mApplication;
    @Inject
    CompletionDataSource mCompletionDataSource;
    @Inject
    StatisticsDataSource mStatisticsDataSource;

    private StatisticsAdapter mAdapter;
    private List<StatisticType> mShownTypes;
    private long mCategoryId;
    private boolean mIsLandscape;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void injectComponent(BrainPhaserComponent component) {
        component.inject(this);
    }

    /**
     * This method is called when the activity is created
     *
     * @param savedInstanceState ignored
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        mShownTypes = new ArrayList<>();

        //Add toolbar
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) ab.setDisplayHomeAsUpEnabled(true);

        User user = mUserManager.getCurrentUser();
        mCategoryId = getIntent().getLongExtra(ChallengeActivity.EXTRA_CATEGORY_ID,
                CategoryDataSource.CATEGORY_ID_ALL);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.statisticsRecycler);
        recyclerView.setHasFixedSize(true);

        int deviceOrientation = getResources().getConfiguration().orientation;

        mIsLandscape = (deviceOrientation == Configuration.ORIENTATION_LANDSCAPE);

        //Look up which statistics can be shown
        setShownTypes();

        GridLayoutManager layoutManager = new GridLayoutManager(this, mIsLandscape ? 3 : 2,
                GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new StatisticsSpanSizeLookup(mShownTypes));

        recyclerView.setLayoutManager(layoutManager);

        //Add adapter
        mAdapter = new StatisticsAdapter(mUserLogicFactory, mChallengeDataSource,
                (BrainPhaserApplication) getApplication(), user, mCategoryId, mShownTypes);

        recyclerView.setAdapter(mAdapter);
    }

    /**
     * This method looks up which charts can be created and sets the member shown types to an array
     * of the types of these charts
     */
    private void setShownTypes() {
        StatisticType[] types = mIsLandscape ? TYPES_LANDSCAPE : TYPES_PORTRAIT;

        ChartDataLogic chartDataLogic = new ChartDataLogic(mUserManager.getCurrentUser(),
                mCategoryId, mApplication, mChallengeDataSource, mCompletionDataSource,
                mStatisticsDataSource, mUserLogicFactory);

        mShownTypes.clear();

        for (StatisticType type : types) {
            switch (type) {
                case TYPE_DUE:
                    if (chartDataLogic.findDueData() != null) {
                        mShownTypes.add(type);
                    }
                    break;
                case TYPE_STAGE:
                    if (chartDataLogic.findStageData() != null) {
                        mShownTypes.add(type);
                    }
                    break;
                default:
                    if (chartDataLogic.findMostPlayedData(type, new ArrayList<Long>()) != null) {
                        mShownTypes.add(type);
                    }
                    break;
            }
        }
    }

    /**
     * This method is called when the activity is started. It resets the shown item views.
     */
    @Override
    protected void onStart() {
        super.onStart();

        setShownTypes();
        mAdapter.setStatisticItems(mShownTypes);
        mAdapter.notifyDataSetChanged();
    }
}