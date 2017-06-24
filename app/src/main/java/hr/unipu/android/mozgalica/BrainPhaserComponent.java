package hr.unipu.android.mozgalica;

import hr.unipu.android.mozgalica.activities.aboutscreen.AboutActivity;
import hr.unipu.android.mozgalica.activities.createuser.CreateUserActivity;
import hr.unipu.android.mozgalica.activities.main.MainActivity;
import hr.unipu.android.mozgalica.activities.main.ProxyActivity;
import hr.unipu.android.mozgalica.activities.playchallenge.AnswerFragment;
import hr.unipu.android.mozgalica.activities.playchallenge.ChallengeActivity;
import hr.unipu.android.mozgalica.activities.playchallenge.multiplechoice.ButtonViewState;
import hr.unipu.android.mozgalica.activities.playchallenge.multiplechoice.MultipleChoiceFragment;
import hr.unipu.android.mozgalica.activities.playchallenge.selfcheck.SelfTestFragment;
import hr.unipu.android.mozgalica.activities.playchallenge.text.TextFragment;
import hr.unipu.android.mozgalica.activities.selectcategory.SelectCategoryPage;
import hr.unipu.android.mozgalica.activities.selectuser.UserAdapter;
import hr.unipu.android.mozgalica.activities.selectuser.UserSelectionActivity;
import hr.unipu.android.mozgalica.activities.statistics.StatisticsActivity;
import hr.unipu.android.mozgalica.activities.usersettings.SettingsActivity;
import hr.unipu.android.mozgalica.logic.UserLogicFactory;
import hr.unipu.android.mozgalica.logic.fileimport.bpc.BPCWrite;

/**
 *
 * App Component that defines injection targets for DI.
 */
public interface BrainPhaserComponent {
    void inject(MainActivity mainActivity);
    void inject(ProxyActivity activity);
    void inject(ChallengeActivity challengeActivity);
    void inject(MultipleChoiceFragment questionFragment);
    void inject(TextFragment textFragment);
    void inject(SelfTestFragment selfTestFragment);
    void inject(CreateUserActivity createUserActivity);
    void inject(UserAdapter userAdapter);
    void inject(UserSelectionActivity activity);
    void inject(StatisticsActivity activity);
    void inject(SettingsActivity activity);

    void inject(AboutActivity activity);

    void inject(SelectCategoryPage selectCategoryPage);
    void inject(AnswerFragment answerFragment);

    void inject(BPCWrite bpcWrite);
    void inject(ButtonViewState state);

    void inject(UserLogicFactory f);
}
