package andreacauchoix.reminder.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andreacauchoix.reminder.AdapteurDesListes.ListWifiAdapter;
import andreacauchoix.reminder.BaseDeDonnee.BDD;
import andreacauchoix.reminder.BaseDeDonnee.LieuData;
import andreacauchoix.reminder.R;
import andreacauchoix.reminder.Wifi.WifiItem;

/**
 * Created by Andréa on 14/04/2015.
 */
public class AjoutLieu extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;
    private Button boutonRechercher;
    private ListView listeViewWifi;
    private List<WifiItem> listeWifiItem;
    private ListWifiAdapter wifiAdapter;
    private WifiManager wifiManager;
    private WifiBroadcastReceiver broadcastReceiver;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ajout_lieu, container,
                false);
        listeViewWifi = (ListView) view.findViewById(R.id.listViewWifi);
        boutonRechercher = (Button) view.findViewById(R.id.buttonRefresh);

        boutonRechercher.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (wifiManager != null)
                    wifiManager.startScan();
            }
        });

        // On récupère le service Wifi d'Android
        wifiManager = (WifiManager) this.getActivity().getSystemService(Context.WIFI_SERVICE);

        // Gestion de la liste des AP Wifi (Voir tuto sur les adapters et les
        // listview)
        listeWifiItem = new ArrayList<WifiItem>();
        wifiAdapter = new ListWifiAdapter(this.getActivity(), listeWifiItem);
        listeViewWifi.setAdapter(wifiAdapter);

        // Création du broadcast Receiver
        broadcastReceiver = new WifiBroadcastReceiver();

        // On attache le receiver au scan result
       getActivity().registerReceiver(broadcastReceiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
 /*       list = (ListView) view.findViewById(R.id.listView1);
        mainWifiObj = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WifiScanReceiver();
        mainWifiObj.startScan();

*/

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
        lieuInput.setWifi_id(((TextView) getView().findViewById((R.id.tvWifiMac))).getText().toString());


        BDD datasource = new BDD(getActivity());
        datasource.ajoutLieu(lieuInput);
        Toast.makeText(getActivity().getApplicationContext(),"Lieu enregistré!", Toast.LENGTH_SHORT).show();
    }


    // On arrête le receiver quand on met en pause l'application
    @Override
    public void onPause() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    // On remet en rourte le receiver quand on reviens sur l'application
    @Override
    public void onResume() {
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    public WifiManager getCurrentWifiManager() {
        return wifiManager;
    }

    public ListWifiAdapter getListWifiAdapter() {
        return wifiAdapter;
    }

    public List<WifiItem> getListeWifiItem() {
        return listeWifiItem;
    }

    class WifiBroadcastReceiver extends BroadcastReceiver {

        private WifiManager wifiManager;
        private ListWifiAdapter wifiAdapter;
        private List<WifiItem> listeWifiItem;

        @Override
        public void onReceive(Context context, Intent intent) {
            wifiManager = getCurrentWifiManager();
            wifiAdapter = getListWifiAdapter();
            listeWifiItem = getListeWifiItem();
            // On vérifie que notre objet est bien instancié
            if (wifiManager != null) {

                // On vérifie que le wifi est allumé
                if (wifiManager.isWifiEnabled()) {
                    // On récupère les scans
                    List<ScanResult> listeScan = wifiManager.getScanResults();

                    // On vide notre liste
                    listeWifiItem.clear();

                    // Pour chaque scan
                    for (ScanResult scanResult : listeScan) {
                        WifiItem item = new WifiItem();

                        item.setAdresseMac(scanResult.BSSID);
                        item.setAPName(scanResult.SSID);
                        item.setForceSignal(scanResult.level);

                        Log.d("FormationWifi", scanResult.SSID + " LEVEL "
                                + scanResult.level);

                        listeWifiItem.add(item);
                    }

                    // On rafraichit la liste
                    wifiAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Vous devez activer votre wifi",
                            Toast.LENGTH_SHORT);
                }
            }

        }

    }

}


