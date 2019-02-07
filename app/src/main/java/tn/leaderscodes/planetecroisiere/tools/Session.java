package tn.leaderscodes.planetecroisiere.tools;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Sami on 14/07/2017.
 */

public class Session {

    private SharedPreferences prefs;
    private static Session instance;

    private Session() {
        prefs = PreferenceManager.getDefaultSharedPreferences(Contextor.getInstance().getContext());
    }

    public void setEmail(String email) {
        prefs.edit().putString("email", email).apply();
    }

    public void setLogin(boolean b) {
        prefs.edit().putBoolean("login", b).apply();
    }

    public Boolean getLogin() {
        return prefs.getBoolean("login", false);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).apply();
    }

    public void setImage(String image) {
        prefs.edit().putString("image", image).apply();
    }

    public void setid(String id) {
        prefs.edit().putString("id", id).apply();
    }

    public String getId() {
        return prefs.getString("id", "");
    }

    public String getEmail() {
        return prefs.getString("email", "");
    }

    public String getusename() {
        return prefs.getString("usename", "");
    }

    public String getImage() {
        return prefs.getString("image", "");
    }

    public void setToken(String token) {
        prefs.edit().putString("token", "Bearer " + token).apply();
    }

    public String getToken() {
        return prefs.getString("token", "");
    }

    public void clearSession() {
        prefs.edit().clear().apply();
    }

    public void setRole(String Roles) {
        prefs.edit().putString("role", Roles).apply();
    }

    public String getRole() {
        return prefs.getString("role", "");
    }


    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }
}
