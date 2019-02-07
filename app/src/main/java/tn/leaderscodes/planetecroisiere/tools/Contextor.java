package tn.leaderscodes.planetecroisiere.tools;

import android.content.Context;

/**
 * Created by Sami on 13/07/2017.
 */

public class Contextor {
    private static Contextor contextor;

    public static Contextor getInstance() {
        if (contextor == null) {
            contextor = new Contextor();
        }
        return contextor;
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }
}
