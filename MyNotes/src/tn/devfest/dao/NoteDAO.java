package tn.devfest.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tn.devfest.database.DBAdapter;
import tn.devfest.database.DatabaseHelper;
import tn.devfest.entities.NoteEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

// Should be Singleton, but OK, it's not :/
public class NoteDAO {

  private DBAdapter dbAdapter;
  
  public NoteDAO(Context context) {
    dbAdapter = new DBAdapter(context);
    dbAdapter.open();
  }
  
  public void close() {
    dbAdapter.close();
  }
  
  public long create(NoteEntity note) {
    ContentValues values = new ContentValues();
    // values.put(DatabaseHelper.getNoteId(), note.getId());
    values.put(DatabaseHelper.getNoteTitle(), note.getTitle());
    values.put(DatabaseHelper.getNoteContent(), note.getContent());
    values.put(DatabaseHelper.getNoteCategoryId(), note.getCategoryId());
    values.put(DatabaseHelper.getNoteTimestamp(), note.getTimestamp().getTime());
    
    return dbAdapter.getDb().insert(DatabaseHelper.getNoteTableName(), null, values);
  }
  
  public boolean update(NoteEntity note) {
    String where = DatabaseHelper.getNoteId() + "=" + note.getId();

    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.getNoteId(), note.getId());
    values.put(DatabaseHelper.getNoteTitle(), note.getTitle());
    values.put(DatabaseHelper.getNoteContent(), note.getContent());
    values.put(DatabaseHelper.getNoteCategoryId(), note.getCategoryId());
    values.put(DatabaseHelper.getNoteTimestamp(), note.getTimestamp().getTime());

    return dbAdapter.getDb().update(DatabaseHelper.getNoteTableName(), values, where, null) != 0;
  }
  
  public boolean delete(NoteEntity note) {
    // TODO: Delete all the medias in the note
    return dbAdapter.getDb().delete(DatabaseHelper.getNoteTableName(), DatabaseHelper.getNoteId() + "=" + note.getId(), null) != 0;
  }
    
  public List<NoteEntity> getAll(){
    ArrayList<NoteEntity> list = new ArrayList<NoteEntity>();
    Cursor c =  dbAdapter.getDb().query(true, DatabaseHelper.getNoteTableName(), null, null, null, null, null, null, null);
    if (c != null && c.moveToFirst()) {
      do {
        list.add(new NoteEntity(
            c.getLong(c.getColumnIndex(DatabaseHelper.getNoteId())),
            DatabaseHelper.getNoteTitle(),
            c.getString(c.getColumnIndex(DatabaseHelper.getNoteContent())),
            c.getLong(c.getColumnIndex(DatabaseHelper.getNoteCategoryId())), 
            new Timestamp(c.getLong(c.getColumnIndex(DatabaseHelper.getNoteTimestamp())))));
      } while(c.moveToNext());
    }
    return list;
  }
  
  private NoteEntity getWhere(String where) {
    NoteEntity note = null;
    Cursor c =  dbAdapter.getDb().query(true, DatabaseHelper.getNoteTableName(), null, where, null, null, null, null, null);
    if (c != null && c.moveToFirst()) {
      note = new NoteEntity(
            c.getLong(c.getColumnIndex(DatabaseHelper.getNoteId())),
            DatabaseHelper.getNoteTitle(),
            c.getString(c.getColumnIndex(DatabaseHelper.getNoteContent())),
            c.getLong(c.getColumnIndex(DatabaseHelper.getNoteCategoryId())), 
            new Timestamp(c.getLong(c.getColumnIndex(DatabaseHelper.getNoteTimestamp()))));
    }
    return note;
  }
  
  public NoteEntity getById(long id) {
    return getWhere(DatabaseHelper.getNoteId() + "=" + id);
  }
  
  public NoteEntity searchByTitle(String title) {
    return getWhere(DatabaseHelper.getNoteTitle() + "LIKE %" + title + "%");
  }
  
  public NoteEntity searchByContent(String content) {
    return getWhere(DatabaseHelper.getNoteContent() + "LIKE %" + content + "%");
  }

}
