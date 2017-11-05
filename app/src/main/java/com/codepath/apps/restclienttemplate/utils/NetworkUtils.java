package com.codepath.apps.restclienttemplate.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.codepath.apps.restclienttemplate.R;
import java.io.IOException;

/**
 * Created by zarko.runjevac on 10/17/2017.
 */

public class NetworkUtils {

  public static Boolean isNetworkAvailable(Activity activity) {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
  }

  public static boolean isInternetAvailable() {
    Runtime runtime = Runtime.getRuntime();
    try {
      Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
      int exitValue = ipProcess.waitFor();
      return (exitValue == 0);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean isOnline(Activity activity, View view, Snackbar snackbar) {

    if (!NetworkUtils.isNetworkAvailable(activity)) {
      snackbar = Snackbar.make(view, R.string.no_network, Snackbar.LENGTH_LONG);
      snackbar.show();
      return false;
    }

    if (!NetworkUtils.isInternetAvailable()) {
      snackbar = Snackbar.make(view, R.string.offline, Snackbar.LENGTH_LONG);
      snackbar.show();
      return false;
    }
    return true;
  }
}
