package andreacauchoix.reminder.AdapteurDesListes;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import andreacauchoix.reminder.Fragments.AjoutLieu;
import andreacauchoix.reminder.R;
import andreacauchoix.reminder.Wifi.WifiItem;

public class ListWifiAdapter extends BaseAdapter {

    private List<WifiItem> listeWifiItem;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListWifiAdapter(Context context, List<WifiItem> alWifi) {

        this.context = context;
        listeWifiItem = alWifi;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return listeWifiItem.size();
    }

    public Object getItem(int position) {
        return listeWifiItem.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private class ViewWifiHolder {
        TextView tvApName;
        TextView tvAdresseMac;
        TextView ForceSignal;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewWifiHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewWifiHolder();

            convertView = layoutInflater.inflate(R.layout.ajout_lieu_list_item, null);

            viewHolder.tvApName = (TextView) convertView.findViewById(R.id.tvWifiName);
            viewHolder.tvAdresseMac = (TextView) convertView.findViewById(R.id.tvWifiMac);
            viewHolder.ForceSignal = (TextView) convertView.findViewById(R.id.ForceSignal);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewWifiHolder)convertView.getTag();
        }

        // On affecte les valeurs
        viewHolder.tvApName.setText(listeWifiItem.get(position).getAPName());
        viewHolder.tvAdresseMac.setText(listeWifiItem.get(position).getAdresseMac());

        // On change la couleur en fonction de la force du signal
        if(listeWifiItem.get(position).getForceSignal() <= -80) {
            viewHolder.ForceSignal.setBackgroundColor(Color.GREEN);
        } else if(listeWifiItem.get(position).getForceSignal() <= -50) {
            viewHolder.ForceSignal.setBackgroundColor(Color.YELLOW);
        } else {
            viewHolder.ForceSignal.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

}
