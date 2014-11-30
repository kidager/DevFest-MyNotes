package tn.devfest.menu;

import java.util.List;
import java.util.zip.Inflater;

import tn.devfest.mynotes.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	
	int nbr_elem ;
	List<Item> list ;
	Inflater inf ;
	Activity res ;
	
	public MenuAdapter(Activity res) {
		// TODO Auto-generated constructor stub
		nbr_elem = res.getResources().getStringArray(R.array.item_name).length;
		list = ItemParser.parseFromResorce(res);
		inf = new Inflater() ;
		this.res = res ;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nbr_elem;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    res.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if(list.get(position).type.equals("normal")){
            	convertView = mInflater.inflate(R.layout.menu_item, null);
            	((ImageView)convertView.findViewById(R.id.imageView_item)).setImageResource(list.get(position).id_img);
            }else{
            	convertView = mInflater.inflate(R.layout.menu_divider, null);
            }
            ((TextView)convertView.findViewById(R.id.textView_item)).setText(list.get(position).title);
        }
		return convertView;
	}

}
