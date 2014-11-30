package tn.devfest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
  private DatabaseHelper dbHelper;
  private SQLiteDatabase db;

  public DBAdapter(Context context) {
    this.dbHelper = new DatabaseHelper(context);
  }
  
  public DBAdapter open() {
    db = dbHelper.getWritableDatabase();
    return this;
  }
  
  public void close() {
    dbHelper.close();
  }
  
  public SQLiteDatabase getDb() {
    return db;
  }
  
}
