package andreacauchoix.reminder.Fragments;

        import android.app.DatePickerDialog;
        import android.app.Fragment;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.util.Calendar;
        import android.app.DialogFragment;

        import andreacauchoix.reminder.BaseDeDonnee.BDD;
        import andreacauchoix.reminder.BaseDeDonnee.RappelData;
        import andreacauchoix.reminder.R;

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

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;


    private Context _context;
    private DatePickerDialog.OnDateSetListener _listener;

    public void enregistrer(){

        RappelData dataInput = new RappelData();

        dataInput.setDate(((EditText) getView().findViewById(R.id.date)).getText().toString());
        dataInput.setRappel(((EditText) getView().findViewById(R.id.rappel)).getText().toString());
        dataInput.setLieu(((EditText) getView().findViewById(R.id.lieu)).getText().toString());

        BDD datasource = new BDD(getActivity());
        datasource.ajoutRappel(dataInput);
        Toast.makeText(getActivity().getApplicationContext(),"Text Note Saved!", Toast.LENGTH_SHORT).show();
    }

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
            }
        );

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
