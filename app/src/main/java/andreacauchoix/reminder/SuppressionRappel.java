package andreacauchoix.reminder;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Andréa on 14/04/2015.
 */

public class SuppressionRappel extends ListFragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    private final int ID_SUPPR = 0;
    private final int GROUP_DEFAULT = 0;

    ArrayAdapter<RappelData> adapter;
    List<RappelData> values;

    RappelData dataSelected;

    public SuppressionRappel() {

    }

    /*protected void onResume() {
        recupererDatas();
        adapter = new ArrayAdapter<RappelData>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        super.onResume();
    }*/

    public void recupererDatas(){
        BDD datasource = new BDD(getActivity());
        values = datasource.getRappels();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.suppression_rappel, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag1_text);

        tvItemName.setText(getArguments().getString(ITEM_NAME));
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));


        values = null;
        recupererDatas();

        Log.e("TAG", "nombre de rappels: " + values.size());
        adapter = new ArrayAdapter<RappelData>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        ListView lv = (ListView) view.findViewById(android.R.id.list);
        registerForContextMenu(lv);

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
   /* public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ID_SUPPR:
                AlertDialog.Builder newDialog = new AlertDialog.Builder(getActivity());
                newDialog.setTitle("Supprimer Note");
                newDialog.setMessage("Voulez-vous supprimer cette note?");
                newDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        BDD datasource = new BDD(getActivity());
                        values.remove(dataSelected);
                        //datasource.deleteElement(dataSelected, mode);
                        adapter.remove(dataSelected);
                        setListAdapter(adapter);
                        Toast.makeText(getActivity().getApplicationContext(), "Note supprimée", Toast.LENGTH_SHORT).show();
                    }
                });
                newDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
                return true;
        }
        return super.onContextItemSelected(item);
    }*/

}
