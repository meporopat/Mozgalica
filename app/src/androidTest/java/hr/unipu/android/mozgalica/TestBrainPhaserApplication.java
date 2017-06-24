package hr.unipu.android.mozgalica;

import hr.unipu.android.mozgalica.BrainPhaserApplication;
import hr.unipu.android.mozgalica.BrainPhaserComponent;


public class TestBrainPhaserApplication extends BrainPhaserApplication {
    BrainPhaserComponent mTestComponent;
    public void setTestComponent(BrainPhaserComponent component) {
        mTestComponent = component;
    }

    @Override
    public BrainPhaserComponent getComponent() {
        return mTestComponent;
    }
}
