package andreacauchoix.reminder.AdapteurDesListes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import andreacauchoix.reminder.BaseDeDonnee.LieuData;
import andreacauchoix.reminder.R;
import andreacauchoix.reminder.Wifi.WifiItem;

/**
 * Created by isen on 28/04/2015.
 */
public class LieuxAdapter extends BaseAdapter{

    private List<LieuData> listeLieux;
    private LayoutInflater layoutInflater;
    private Context context;

 public int getCount() {
    return listeLieux.size();
}

    public Object getItem(int position) {
        return listeLieux.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public LieuxAdapter(Context context, List<LieuData> alLieux) {

        this.context = context;
        listeLieux = alLieux;
        layoutInflater = LayoutInflater.from(context);
    }
    private class ViewNameLieux {
        TextView nameLieux;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewNameLieux viewHolder;

        if(convertView == null) {
            viewHolder = new ViewNameLieux();

            convertView = layoutInflater.inflate(R.layout.adapter_lieux, null);

            viewHolder.nameLieux = (TextView) convertView.findViewById(R.id.nameLieux);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewNameLieux)convertView.getTag();
        }

        // On affecte les valeurs
        viewHolder.nameLieux.setText(listeLieux.get(position).getName());

        return convertView;
    }

}
