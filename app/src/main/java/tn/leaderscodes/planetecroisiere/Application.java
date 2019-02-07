package tn.leaderscodes.planetecroisiere;

import tn.leaderscodes.planetecroisiere.tools.Contextor;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().setContext(getApplicationContext());
    }
}
