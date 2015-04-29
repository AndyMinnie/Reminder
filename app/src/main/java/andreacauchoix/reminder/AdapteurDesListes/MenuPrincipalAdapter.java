package andreacauchoix.reminder.AdapteurDesListes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import andreacauchoix.reminder.BaseDeDonnee.RappelData;
import andreacauchoix.reminder.R;

public class MenuPrincipalAdapter extends ArrayAdapter {

    private Context context;
    private boolean useList = true;

    public MenuPrincipalAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_2, items);
        this.context = context;
    }

    /**
     * Holder for the list items.
     */
    private class ViewHolder{
        TextView titleText;
        TextView titleText1;
        TextView titleText2;
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
        RappelData item = (RappelData)getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if(useList){
                viewToUse = mInflater.inflate(R.layout.base_menu, null);
            } else {
                viewToUse = mInflater.inflate(R.layout.base_menu, null);
            }

            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.lieubaby);
            holder.titleText1 = (TextView)viewToUse.findViewById(R.id.titrebaby);
            holder.titleText2 = (TextView)viewToUse.findViewById(R.id.contenubaby);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText("Réseau wifi: "+item.getLieu()+"\n");
        holder.titleText1.setText(item.getDate()+"\n");
        holder.titleText2.setText(item.getRappel()+"\n");
        return viewToUse;
    }
}
/**
 * Created by isen on 29/04/2015.
 */

/*
public class MenuPrincipalAdapter extends BaseAdapter {

    private List<RappelData> listeRappels;
    private LayoutInflater layoutInflater;
    private Context context;

    public int getCount() {
        return listeRappels.size();
    }

    public Object getItem(int position) {
        return listeRappels.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public MenuPrincipalAdapter(Context context, List<RappelData> alRappels) {

        this.context = context;
        listeRappels = alRappels;
        layoutInflater = LayoutInflater.from(context);
    }
    private class ViewNameLieux {
        TextView tvTitle;
        TextView tvLieu;
        TextView tvContenu;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewNameLieux viewHolder;

        if(convertView == null) {
            viewHolder = new ViewNameLieux();

            convertView = layoutInflater.inflate(R.layout.base_menu, null);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.titrebaby);
            viewHolder.tvLieu = (TextView) convertView.findViewById(R.id.lieubaby);
            viewHolder.tvContenu = (TextView) convertView.findViewById(R.id.contenubaby);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewNameLieux)convertView.getTag();
        }

        // On affecte les valeurs
        viewHolder.tvTitle.setText(listeRappels.get(position).getDate());
        viewHolder.tvLieu.setText(listeRappels.get(position).getLieu());viewHolder.tvLieu.setText(listeRappels.get(position).getLieu());
        viewHolder.tvContenu.setText(listeRappels.get(position).getRappel());
        return convertView;
    }
}
*/