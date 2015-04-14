package andreacauchoix.reminder;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

/**
 * Created by Andréa on 14/04/2015.
 */

public class AjoutRappel extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    private int id;
    private String rappel;
    private String date;
    private String lieu;

    public AjoutRappel() {

    }
    public AjoutRappel(int id, String titre, String date, String lieu){
        this.id = id;
        this.rappel = titre;
        this.date = date;
        this.lieu = lieu;
    }

    public AjoutRappel(String titre, String date, String lieu){
        this.rappel = titre;
        this.date = date;
        this.lieu = lieu;
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
        return view;
    }
    public String getRappel() {
        return rappel;
    }

    public String getLieu() {
        return lieu;
    }

    public void setRappel(String titre) {
        this.rappel = titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
