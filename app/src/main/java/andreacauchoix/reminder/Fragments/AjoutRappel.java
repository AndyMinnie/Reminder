package andreacauchoix.reminder.Fragments;

        import android.app.DatePickerDialog;
        import android.app.Fragment;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.net.wifi.ScanResult;
        import android.net.wifi.WifiManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

        import android.app.DialogFragment;

        import andreacauchoix.reminder.AdapteurDesListes.ArrayListAdapter;
        import andreacauchoix.reminder.AdapteurDesListes.ArrayListAdapterLieux;
        import andreacauchoix.reminder.AdapteurDesListes.LieuxAdapter;
        import andreacauchoix.reminder.AdapteurDesListes.ListWifiAdapter;
        import andreacauchoix.reminder.BaseDeDonnee.BDD;
        import andreacauchoix.reminder.BaseDeDonnee.LieuData;
        import andreacauchoix.reminder.BaseDeDonnee.RappelData;
        import andreacauchoix.reminder.R;
        import andreacauchoix.reminder.Wifi.WifiItem;

/**
 * Created by Andréa on 14/04/2015.
 */
public class AjoutRappel extends DialogFragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    private TextView Output;
    private Button changeDate;
    private ListView listeViewWifi;

    ListView list;
    private List<LieuData> listeLieuxItem;
    private LieuxAdapter lieuxAdapter;

    private int year;
    private int month;
    private int day;

    LieuData wifiSelected;

    static final int DATE_PICKER_ID = 1111;


    private Context _context;
    private DatePickerDialog.OnDateSetListener _listener;

    public void recupererDatas(){
        BDD datasource = new BDD(getActivity());
        listeLieuxItem = datasource.getLieux();
    }



    /**
     * fonction qui enregistre les rappels dans la base de données
     */
    public void enregistrer(){
        RappelData dataInput = new RappelData();

        dataInput.setDate(((EditText) getView().findViewById(R.id.date)).getText().toString());
        dataInput.setRappel(((EditText) getView().findViewById(R.id.rappel)).getText().toString());

        if(wifiSelected != null) {
            dataInput.setLieu(wifiSelected.getWifi_id());
        }
        else
            dataInput.setLieu("");

        BDD datasource = new BDD(getActivity());
        datasource.ajoutRappel(dataInput);
        Toast.makeText(getActivity().getApplicationContext(),"Rappel Enregistré!", Toast.LENGTH_SHORT).show();
    }
    /*public void enregistrerLieu(){

        Log.e("TAG", wifiSelected.getWifi_id());
        LieuData lieuInput = new LieuData();
        lieuInput.setName(((EditText) getView().findViewById((R.id.lieu))).getText().toString());


        BDD datasource = new BDD(getActivity());
        datasource.ajoutLieu(lieuInput);
        Toast.makeText(getActivity().getApplicationContext(),"Lieu enregistré!", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ajout_rappel, container,
                false);


        Button clickButton = (Button) view.findViewById(R.id.ajouter_rappel);
        clickButton.setOnClickListener(new View.OnClickListener() {
                public void onClick (View view){
                    switch (view.getId()) {
                        case R.id.ajouter_rappel:
                            Toast.makeText(getActivity().getApplicationContext(), "Rappel enregistré!", Toast.LENGTH_SHORT).show();
                            enregistrer();
                            break;
                    }
                }
            });
        listeLieuxItem = null;


        listeViewWifi = (ListView) view.findViewById(R.id.listview);
        recupererDatas();
        lieuxAdapter = new LieuxAdapter(this.getActivity(), listeLieuxItem);
        listeViewWifi.setAdapter(lieuxAdapter);

        listeViewWifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                wifiSelected = (LieuData) listeViewWifi.getItemAtPosition(position);
              }
        });
        Output = (TextView) view.findViewById(R.id.date);
        changeDate = (Button) view.findViewById(R.id.changer_date);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        Output.setText(new StringBuilder().append("Date: ").append(month + 1).append("-").append(day).append("-").append(year).append(" "));

            changeDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog d = new DatePickerDialog(getActivity(),pickerListener, year, month, day);
                    d.show();
                }

            });
            return view;
        }


    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;
            Output.setText(new StringBuilder().append("Date: ").append(month + 1).append("-").append(day).append("-").append(year).append(" "));

        }
    };

}
