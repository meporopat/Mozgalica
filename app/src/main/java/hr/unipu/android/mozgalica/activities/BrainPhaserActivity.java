package hr.unipu.android.mozgalica.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hr.unipu.android.mozgalica.BrainPhaserApplication;
import hr.unipu.android.mozgalica.BrainPhaserComponent;

/**
 *
 * Base Activity class to be used by all activitites in the project.
 * Subclasses need to implement injectComponent to use the Depency Injector.
 * See: https://blog.gouline.net/2015/05/04/dagger-2-even-sharper-less-square/
 */
public abstract class BrainPhaserActivity extends AppCompatActivity {
    /**
     * OnCreate injects components
     *
     * @param savedInstanceState Ignored
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(((BrainPhaserApplication) getApplication()).getComponent());
    }

    /**
     * Called to inject dependencies. Calls component.inject(this) as
     * uniform implementation in all Activities.
     *
     * @param component the component supplied by the Application to resolve dependencies
     */
    protected abstract void injectComponent(BrainPhaserComponent component);
}
