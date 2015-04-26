package andreacauchoix.reminder.Fragments;

        import android.app.DatePickerDialog;
        import android.app.Fragment;
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

        import andreacauchoix.reminder.BaseDeDonnee.BDD;
        import andreacauchoix.reminder.BaseDeDonnee.RappelData;
        import andreacauchoix.reminder.R;

/**
 * Created by Andréa on 14/04/2015.
 */

public class AjoutRappel extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    DatePicker dpResult;
    EditText tvDisplayDate;

    public void enregistrer(){

        RappelData dataInput = new RappelData();

      //  dataInput.setDate(((EditText) getView().findViewById(R.id.date)).getText().toString());
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

        ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag1_text);

        tvItemName.setText(getArguments().getString(ITEM_NAME));
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));

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

        dpResult = (DatePicker) view.findViewById(R.id.date);
        tvDisplayDate = (EditText) view.findViewById(R.id.rappel);

        return view;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {


        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            int year = selectedYear;
            int month = selectedMonth;
            int day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(month + 1).append("-").append(day).append("-")
                    .append(year).append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };
}
