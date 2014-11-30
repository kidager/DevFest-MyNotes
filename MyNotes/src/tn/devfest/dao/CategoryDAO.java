package tn.devfest.dao;

import java.util.ArrayList;
import java.util.List;

import tn.devfest.database.DBAdapter;
import tn.devfest.database.DatabaseHelper;
import tn.devfest.entities.CategoryEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CategoryDAO {
  private DBAdapter dbAdapter;

  public CategoryDAO(Context context) {
    dbAdapter = new DBAdapter(context);
    dbAdapter.open();
  }

  public void close() {
    dbAdapter.close();
  }

  public long create(CategoryEntity category) {
    ContentValues values = new ContentValues();
    // values.put(DatabaseHelper.getCategoryId(), category.getId());
    values.put(DatabaseHelper.getCategoryName(), category.getName());
    values.put(DatabaseHelper.getCategoryImage(), category.getImage());
    return dbAdapter.getDb().insert(DatabaseHelper.getCategoryTableName(),
        null, values);
  }

  public boolean update(CategoryEntity category) {
    String where = DatabaseHelper.getCategoryId() + "="
        + category.getId();

    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.getCategoryId(), category.getId());
    values.put(DatabaseHelper.getCategoryName(), category.getName());
    values.put(DatabaseHelper.getCategoryImage(), category.getImage());
    return dbAdapter.getDb().update(DatabaseHelper.getCategoryTableName(),
        values, where, null) != 0;
  }

  public boolean delete(CategoryEntity category) {
    // TODO: Delete all the notes in the category
    return dbAdapter.getDb().delete(DatabaseHelper.getCategoryTableName(),
        DatabaseHelper.getCategoryId() + "=" + category.getId(), null) != 0;
  }

  public List<CategoryEntity> getAll() {
    ArrayList<CategoryEntity> list = new ArrayList<CategoryEntity>();
    Cursor c = dbAdapter.getDb().query(true,
        DatabaseHelper.getCategoryTableName(), null, null, null, null, null,
        null, null);
    if (c != null && c.moveToFirst()) {
      do {
        list.add(new CategoryEntity(
            c.getLong(c.getColumnIndex(DatabaseHelper.getCategoryId())),
            c.getString(c.getColumnIndex(DatabaseHelper.getCategoryName())),
            c.getString(c.getColumnIndex(DatabaseHelper.getCategoryImage()))));
      } while (c.moveToNext());
    }
    return list;
  }

  private CategoryEntity getWhere(String where) {
    CategoryEntity media = null;
    Cursor c = dbAdapter.getDb().query(true, DatabaseHelper.getCategoryTableName(),
        null, where, null, null, null, null, null);
    if (c != null && c.moveToFirst()) {
      media = new CategoryEntity(
          c.getLong(c.getColumnIndex(DatabaseHelper.getCategoryId())),
          c.getString(c.getColumnIndex(DatabaseHelper.getCategoryName())),
          c.getString(c.getColumnIndex(DatabaseHelper.getCategoryImage())));

    }
    return media;
  }

  public CategoryEntity getById(long id) {
    return getWhere(DatabaseHelper.getCategoryId() + "=" + id);
  }

  public CategoryEntity searchByName(String name) {
    return getWhere(DatabaseHelper.getCategoryName() + "LIKE %" + name + "%");
  }
}
