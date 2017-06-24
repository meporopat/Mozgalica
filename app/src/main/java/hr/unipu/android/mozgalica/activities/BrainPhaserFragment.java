package hr.unipu.android.mozgalica.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import hr.unipu.android.mozgalica.BrainPhaserApplication;
import hr.unipu.android.mozgalica.BrainPhaserComponent;

/**
 *
 * Base Activity class to be used by all Fragments in the project.
 * Subclasses need to implement injectComponent to use the Depency Injector.
 * See: https://blog.gouline.net/2015/05/04/dagger-2-even-sharper-less-square/
 */
public abstract class BrainPhaserFragment extends Fragment {
    /**
     * OnCreate injects components
     *
     * @param savedInstanceState Ignored
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectComponent(((BrainPhaserApplication) getActivity().getApplication()).getComponent());
    }

    /**
     * Called to inject dependencies. Should call component.inject(this) as
     * uniform implementation in all Activities.
     *
     * @param component
     */
    protected abstract void injectComponent(BrainPhaserComponent component);
}
