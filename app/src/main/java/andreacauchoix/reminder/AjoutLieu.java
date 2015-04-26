package andreacauchoix.reminder;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Andréa on 14/04/2015.
 */
public class AjoutLieu extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    WifiManager mainWifiObj;
    WifiScanReceiver wifiReciever;
    ListView list;
    String wifis[];

    public AjoutLieu() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ajout_lieu, container,
                false);

        list = (ListView) view.findViewById(R.id.listView1);
        mainWifiObj = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WifiScanReceiver();
        mainWifiObj.startScan();

        Button clickButton = (Button) view.findViewById(R.id.buttonAjout);
        clickButton.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View view) {
                       switch (view.getId()) {
                           case R.id.buttonAjout:
                               Toast.makeText(getActivity().getApplicationContext(), "Lieu enregistré!", Toast.LENGTH_SHORT).show();
                               enregistrerLieu();
                               break;
                       }
                   }
               }
        );
        return view;
    }

    public void enregistrerLieu(){

        LieuData lieuInput = new LieuData();
        lieuInput.setName(((EditText) getView().findViewById((R.id.lieu))).getText().toString());
        lieuInput.setWifi_id(((EditText) getView().findViewById((R.id.lieu))).getText().toString());

        BDD datasource = new BDD(getActivity());
        datasource.ajoutLieu(lieuInput);
        Toast.makeText(getActivity().getApplicationContext(),"Lieu enregistré!", Toast.LENGTH_SHORT).show();
    }


    public void onPause() {
        getActivity().unregisterReceiver(wifiReciever);
        super.onPause();
    }

    public void onResume() {
        getActivity().registerReceiver(wifiReciever, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    class WifiScanReceiver extends BroadcastReceiver {
        @SuppressLint("UseValueOf")
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
            wifis = new String[wifiScanList.size()];
            for(int i = 0; i < wifiScanList.size(); i++){
                wifis[i] = ("Nom du WIFI: "+ (wifiScanList.get(i)).SSID +" - BSSID du WIFI: "+ (wifiScanList.get(i)).BSSID);
            }

            list.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,wifis));
        }
    }

}


