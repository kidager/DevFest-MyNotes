package tn.devfest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

  // for our logs
  public static final String LOG_TAG = "DatabaseHandler.java";

  // database version
  private static final int DATABASE_VERSION = 1;

  // database name
  protected static final String DATABASE_NAME = "note.db";

  // table details
  protected static String noteTableName = "Note";
  protected static String noteId = "id";
  protected static String noteTitle = "title";
  protected static String noteContent = "content";
  protected static String noteCategoryId = "categoryId";
  protected static String noteTimestamp = "timestamp";

  protected static String categoryTableName = "Category";
  protected static String categoryId = "id";
  protected static String categoryName = "name";
  protected static String categoryImage = "image";

  protected static String mediaTableName = "Media";
  protected static String mediaId = "id";
  protected static String mediaPath = "path";
  protected static String mediaNoteId = "noteId";
  
  // constructor
  public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  // creating Tables
  @Override
  public void onCreate(SQLiteDatabase db) {
    String sql = "";

    sql += "CREATE TABLE " + noteTableName + " (";
    sql += noteId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    sql += noteTitle + " TEXT, ";
    sql += noteContent + " TEXT, ";
    sql += noteCategoryId + " INTEGER, ";
    sql += noteTimestamp + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP";
    sql += ")";
    db.execSQL(sql);

    sql = "CREATE TABLE " + categoryTableName + " (";
    sql += categoryId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    sql += categoryName + " TEXT, ";
    sql += categoryImage + " TEXT";
    sql += ")";
    db.execSQL(sql);

    sql = "CREATE TABLE " + mediaTableName + " (";
    sql += mediaId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    sql += mediaPath + " TEXT, ";
    sql += mediaNoteId + " INTEGER";
    sql += ")";
    db.execSQL(sql);
  }

  // When upgrading the database, it will drop the current tables and recreate
  // them.
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + noteTableName);
    db.execSQL("DROP TABLE IF EXISTS " + categoryTableName);
    db.execSQL("DROP TABLE IF EXISTS " + mediaTableName);
    onCreate(db);
  }

  public static String getLogTag() {
    return LOG_TAG;
  }

  public static String getNoteId() {
    return noteId;
  }

  public static String getNoteTitle() {
    return noteTitle;
  }

  public static String getNoteContent() {
    return noteContent;
  }

  public static String getNoteCategoryId() {
    return noteCategoryId;
  }

  public static String getNoteTimestamp() {
    return noteTimestamp;
  }

  public static String getCategoryId() {
    return categoryId;
  }

  public static String getCategoryName() {
    return categoryName;
  }

  public static String getCategoryImage() {
    return categoryImage;
  }

  public static String getMediaId() {
    return mediaId;
  }

  public static String getMediaPath() {
    return mediaPath;
  }

  public static String getMediaNoteId() {
    return mediaNoteId;
  }
  
  public static String getNoteTableName() {
    return noteTableName;
  }
  
  public static String getCategoryTableName() {
    return categoryTableName;
  }
  
  public static String getMediaTableName() {
    return mediaTableName;
  }
}