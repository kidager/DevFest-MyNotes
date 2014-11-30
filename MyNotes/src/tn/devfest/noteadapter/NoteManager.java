package tn.devfest.noteadapter;

import java.util.List;

import tn.devfest.dao.NoteDAO;
import tn.devfest.entities.NoteEntity;
import android.content.Context;
import android.widget.ListView;

public class NoteManager {

  NoteDAO nDAO;
  List<NoteEntity> list;
  Context ctx;

  public NoteManager(Context ctx, String name) {
    this.ctx = ctx;
    nDAO = new NoteDAO(ctx);
    list = nDAO.getAll();
    nDAO.close();
  }

  public void addadapter(final ListView listv) {
    NoteAdapter adapter = new NoteAdapter(ctx, list);
    listv.setAdapter(adapter);
  }

  public boolean isListEmpty() {
    return list == null;
  }

}
