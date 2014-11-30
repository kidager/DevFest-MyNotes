package tn.devfest.adapters;

import java.util.List;

import tn.devfest.entities.NoteEntity;
import tn.devfest.mynotes.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteAdapter extends
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

  private List<NoteEntity> noteList;

  public NoteAdapter(List<NoteEntity> noteList) {
    this.noteList = noteList;
  }

  @Override
  public int getItemCount() {
    return noteList.size();
  }

  @Override
  public void onBindViewHolder(NoteViewHolder noteViewHolder, int i) {
    NoteEntity note = noteList.get(i);
    noteViewHolder.vTitle.setText(note.getTitle());
    noteViewHolder.vContent.setText(note.getContent());
  }

  @Override
  public NoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
        R.layout.card_view_layout, viewGroup, false);

    return new NoteViewHolder(itemView);
  }

  public static class NoteViewHolder extends RecyclerView.ViewHolder {
    protected TextView vTitle;
    protected TextView vContent;

    public NoteViewHolder(View v) {
      super(v);
      vTitle = (TextView) v.findViewById(R.id.note_view_title);
      vContent = (TextView) v.findViewById(R.id.note_view_content);
    }
  }
}
