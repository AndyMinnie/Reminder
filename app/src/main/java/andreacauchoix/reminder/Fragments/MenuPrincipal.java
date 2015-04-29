package andreacauchoix.reminder.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import andreacauchoix.reminder.AdapteurDesListes.ArrayListAdapter;
import andreacauchoix.reminder.AdapteurDesListes.LieuxAdapter;
import andreacauchoix.reminder.AdapteurDesListes.ListWifiAdapter;
import andreacauchoix.reminder.AdapteurDesListes.MenuPrincipalAdapter;
import andreacauchoix.reminder.BaseDeDonnee.BDD;
import andreacauchoix.reminder.BaseDeDonnee.LieuData;
import andreacauchoix.reminder.BaseDeDonnee.RappelData;
import andreacauchoix.reminder.R;
import andreacauchoix.reminder.Wifi.WifiItem;

/**
 * Created by Andréa on 14/04/2015.
 */

public class MenuPrincipal extends ListFragment {

    ImageView ivIcon;
    TextView tvItemName;

    private final int ID_SUPPR = 0;
    private final int GROUP_DEFAULT = 0;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    ArrayAdapter<RappelData> adapter;
    List<RappelData> values;

    RappelData dataSelected;

    public MenuPrincipal() {}

    public void recupererDatas(){
        BDD datasource = new BDD(getActivity());
        values = datasource.getRappels();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menu_bitch, container,
                false);


        values = null;
        recupererDatas();

        adapter = new MenuPrincipalAdapter(getActivity(), values);
        setListAdapter(adapter);

        return view;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == android.R.id.list) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            dataSelected = (RappelData) lv.getItemAtPosition(acmi.position);
            menu.add(GROUP_DEFAULT, ID_SUPPR, 0, "Supprimer");
        }
    }
}
