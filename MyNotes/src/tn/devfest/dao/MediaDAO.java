package tn.devfest.dao;

import java.util.ArrayList;
import java.util.List;

import tn.devfest.database.DBAdapter;
import tn.devfest.database.DatabaseHelper;
import tn.devfest.entities.MediaEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MediaDAO {
  private DBAdapter dbAdapter;

  public MediaDAO(Context context) {
    dbAdapter = new DBAdapter(context);
    dbAdapter.open();
  }

  public void close() {
    dbAdapter.close();
  }

  public long create(MediaEntity media) {
    ContentValues values = new ContentValues();
    // values.put(DatabaseHelper.getMediaId(), media.getId());
    values.put(DatabaseHelper.getMediaPath(), media.getPath());
    values.put(DatabaseHelper.getMediaNoteId(), media.getNoteId());
    return dbAdapter.getDb().insert(DatabaseHelper.getMediaTableName(), null,
        values);
  }

  public boolean update(MediaEntity media) {
    String where = DatabaseHelper.getMediaId() + "=" + media.getId();

    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.getMediaId(), media.getId());
    values.put(DatabaseHelper.getMediaPath(), media.getPath());
    values.put(DatabaseHelper.getMediaNoteId(), media.getNoteId());
    return dbAdapter.getDb().update(DatabaseHelper.getMediaTableName(), values,
        where, null) != 0;
  }

  public boolean delete(MediaEntity media) {
    return dbAdapter.getDb().delete(DatabaseHelper.getMediaTableName(),
        DatabaseHelper.getMediaId() + "=" + media.getId(), null) != 0;
  }

  public List<MediaEntity> getAll() {
    ArrayList<MediaEntity> list = new ArrayList<MediaEntity>();
    Cursor c = dbAdapter.getDb().query(true, DatabaseHelper.getMediaTableName(),
        null, null, null, null, null, null, null);
    if (c != null && c.moveToFirst()) {
      do {
        list.add(
            new MediaEntity(
                c.getLong(c.getColumnIndex(DatabaseHelper.getMediaId())),
                c.getString(c.getColumnIndex(DatabaseHelper.getMediaPath())),
                c.getLong(c.getColumnIndex(DatabaseHelper.getMediaNoteId()))));
      } while (c.moveToNext());
    }
    return list;
  }
  
  public List<MediaEntity> getAllForNoteId(long noteId) {
    String where = DatabaseHelper.getMediaNoteId() + "=" + noteId;
    ArrayList<MediaEntity> list = new ArrayList<MediaEntity>();
    Cursor c = dbAdapter.getDb().query(true, DatabaseHelper.getMediaTableName(),
        null, where, null, null, null, null, null);
    if (c != null && c.moveToFirst()) {
      do {
        list.add(
            new MediaEntity(
                c.getLong(c.getColumnIndex(DatabaseHelper.getMediaId())),
                c.getString(c.getColumnIndex(DatabaseHelper.getMediaPath())),
                c.getLong(c.getColumnIndex(DatabaseHelper.getMediaNoteId()))));
      } while (c.moveToNext());
    }
    return list;
  }


  private MediaEntity getWhere(String where) {
    MediaEntity media = null;
    Cursor c = dbAdapter.getDb().query(true, DatabaseHelper.getMediaTableName(),
        null, where, null, null, null, null, null);
    if (c != null && c.moveToFirst()) {
      media = new MediaEntity(
          c.getLong(c.getColumnIndex(DatabaseHelper.getMediaId())),
          c.getString(c.getColumnIndex(DatabaseHelper.getMediaPath())),
          c.getLong(c.getColumnIndex(DatabaseHelper.getMediaNoteId())));
    }
    return media;
  }

  public MediaEntity getById(long id) {
    return getWhere(DatabaseHelper.getMediaId() + "=" + id);
  }
}
