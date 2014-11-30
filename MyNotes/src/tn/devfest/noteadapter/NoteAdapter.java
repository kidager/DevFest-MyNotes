package tn.devfest.noteadapter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import tn.devfest.entities.NoteEntity;
import tn.devfest.mynotes.R;
import tn.devfest.mynotes.ShowNoteActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {

  List<NoteEntity> noteList;
  Context ctx;

  public NoteAdapter(Context ctx, List<NoteEntity> noteList) {
    this.noteList= noteList;
    this.ctx = ctx;
  }

  @Override
  public int getCount() {
    return noteList.size();
  }

  @Override
  public Object getItem(int position) {
    return noteList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      LayoutInflater mInflater = (LayoutInflater) ctx
          .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

      convertView = mInflater.inflate(R.layout.note_item, null);

      ((TextView) convertView.findViewById(R.id.textView111111111)).setText(noteList.get(position).getTitle());
      ((TextView) convertView.findViewById(R.id.textView2)).setText(noteList.get(position).getContent());
      Timestamp ts = noteList.get(position).getTimestamp();
      ((TextView) convertView.findViewById(R.id.note_view_title)).setText(tsToDate(ts));
      
      ((CardView)convertView.findViewById(R.id.card_view)).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          NoteEntity note = noteList.get(position);
          Intent i = new Intent(ctx, ShowNoteActivity.class);
          i.putExtra("MyClass", note);
          ctx.startActivity(i);
        }
      });
    }
    return convertView;
  }
  
  private String tsToDate(Timestamp ts) {
    SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss");
    return dt1.format(ts);
  }

}
