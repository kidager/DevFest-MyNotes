package tn.devfest.catogories;

import tn.devfest.mynotes.R;
import tn.devfest.noteadapter.NoteManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class ScreenSlidePageFragment extends Fragment {

	RecyclerView mRecyclerView ;
	LayoutManager mLayoutManager ;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        
        ListView liste = (ListView) rootView.findViewById(R.id.note_list);
        
        NoteManager manager = new NoteManager(getActivity(), "general");
        if(manager.isListEmpty()) Toast.makeText(getActivity(), R.string.no_note, Toast.LENGTH_LONG).show(); 
        	else{
        	manager.addadapter(liste);
        }
        
        return rootView;
    }
}
