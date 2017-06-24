package hr.unipu.android.mozgalica.logic;

import org.mockito.Mockito;

import hr.unipu.android.mozgalica.logic.DueChallengeLogic;
import hr.unipu.android.mozgalica.logic.UserLogicFactory;
import hr.unipu.android.mozgalica.model.User;


public class MockTestUserLogicFactory extends UserLogicFactory {
    @Override
    public DueChallengeLogic createDueChallengeLogic(User user) {
        return Mockito.mock(DueChallengeLogic.class);
    }
}
