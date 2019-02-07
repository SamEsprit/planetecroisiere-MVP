package tn.leaderscodes.planetecroisiere.bases;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/*
 * Base activity class
 * Created by Sami on 16/01/2019.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void initializeView() {

    }
    public void initializeToolBar() {

    }
    public void initializeUtils(){

    }
    /**
     * Returns true if network is available or about to become available
     *
     * @param context Context used to get ConnectivityManager
     * @return true if device is online
     */
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

}

