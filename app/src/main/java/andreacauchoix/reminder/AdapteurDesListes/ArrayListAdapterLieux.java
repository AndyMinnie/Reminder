package andreacauchoix.reminder.AdapteurDesListes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;
import java.util.List;

import andreacauchoix.reminder.BaseDeDonnee.LieuData;
import andreacauchoix.reminder.R;

public class ArrayListAdapterLieux extends ArrayAdapter {

    private Context context;
    private boolean useList = true;

    public ArrayListAdapterLieux(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    /**
     * Holder for the list items.
     */
    private class ViewHolder{
        TextView titleText;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LieuData item = (LieuData)getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if(useList){
                viewToUse = mInflater.inflate(R.layout.lieu_list_item, null);
            } else {
                viewToUse = mInflater.inflate(R.layout.lieu_list_item, null);
            }

            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        //holder.titleText.setText("ID: "+ item.getId() + " Nom: " + item.getName() + " BSSID: " + item.getWifi_id()); // recupere le titre du wifi enregistré
        holder.titleText.setText(" Nom du lieu: " + item.getName());
        return viewToUse;
    }
}