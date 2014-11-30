package tn.devfest.menu;

import java.util.ArrayList;
import java.util.List;

import tn.devfest.mynotes.R;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

public class ItemParser {
	
	public static List<Item> parseFromResorce(Activity res){
		List<Item> list = new ArrayList<Item>();
		String[] titles = res.getResources().getStringArray(R.array.item_name);
		String[] types = res.getResources().getStringArray(R.array.item_type);
		int[] resources = new int[]{0,R.drawable.ic_action_attachment,
				R.drawable.ic_action_edit,R.drawable.ic_action_remove,0,R.drawable.ic_action_attachment,
				R.drawable.ic_action_edit,R.drawable.ic_action_remove};
		
		for (int i = 0; i < titles.length; i++) {
			Item item = new Item();
			item.title = titles[i];
			item.type = types[i];
			item.id_img = resources[i];
			
			list.add(item);
		}
		
		return list ;
	}

}
