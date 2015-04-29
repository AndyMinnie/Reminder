package andreacauchoix.reminder.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Gabriel on 29/04/2015.
 */
public class NetWatcher extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent1) {
        //here, check that the network connection is available. If yes, start your service. If not, stop your service.
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (info != null) {
            if (info.isConnected() && wifiManager.isWifiEnabled()) {
                //start service
                Intent intent = new Intent(context, MyService.class);
                context.startService(intent);
            }
            else {
                //stop service
                Intent intent = new Intent(context, MyService.class);
                context.stopService(intent);
            }
        }
    }
}
