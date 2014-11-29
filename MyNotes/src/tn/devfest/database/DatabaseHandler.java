package tn.devfest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // for our logs
    public static final String LOG_TAG = "DatabaseHandler.java";
 
    // database version
    private static final int DATABASE_VERSION = 2;
 
    // database name
    protected static final String DATABASE_NAME = "note.db";
 
    // table details
    public String tableName1 = "Note";
 
    public String fieldId = "id_note";
    public String fieldName = "titre";
    public String fieldContenu = "contenu";
    public String fieldIdCategory = "id_category";
    public String fieldDate = "date";
    
 
    
    public String tableName2 = "Category";
    
    public String fieldCategoryId = "id_category";
    public String fieldCategoryName = "nom_category";
    public String fieldCategoryImage = "image_category";
 
 
    
    public String tableName3 = "Media";
    
    public String fieldIdMedia = "id_media";
    public String fieldCheminMedia = "chemin_media";
    public String fieldMediaNote = "id_note";
 
    
    // constructor
    public DatabaseHandler(Context context) {
 
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        String sql = "";
 
        sql += "CREATE TABLE " + tableName1;
        sql += " ( ";
        sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += fieldName + " TEXT, ";
        sql += fieldContenu + " TEXT ";
        sql += fieldIdCategory + " INTEGER ";
        sql += fieldDate + " DATE ";
        
        
        sql += " ) ";
        
        db.execSQL(sql);
 
        sql  = "CREATE TABLE " + tableName2;
        sql += " ( ";
        sql += fieldCategoryId + " INTEGER , ";
        sql += fieldCategoryName + " TEXT, ";
        sql += fieldCategoryImage + " TEXT ";

        
        sql += " ) ";
        
        db.execSQL(sql);
 
        
        sql += "CREATE TABLE " + tableName3;
        sql += " ( ";
        sql += fieldIdMedia + " INTEGER , ";
        sql += fieldCheminMedia + " TEXT, ";
        sql += fieldMediaNote + " INTEGER ";

        
        sql += " ) ";
        
        db.execSQL(sql);
 
        
        
        
    }
 
    // When upgrading the database, it will drop the current table and recreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
        String sql = "DROP TABLE IF EXISTS " + tableName1;
        db.execSQL(sql);
 
        
        onCreate(db);
    }
 
    // Update location record.
    // @param id - will identify which record is to be updated.
    // @param name - the new location name to be saved.
    // @param description - the new location description to be saved.
    public boolean update(int id, String name, String description) {
 
        boolean updateSuccessful = false;
 
        ContentValues values = new ContentValues();
 
        values.put(fieldName, name);
        values.put(fieldContenu, description);
 
        // you can use AND if you have multiple conditions
        String where = fieldId + " = ?";
 
        // you should use commas when you have multiple conditions
        String[] whereArgs = { Integer.toString(id) };
 
        SQLiteDatabase db = this.getWritableDatabase();
 
        // use the update command
        updateSuccessful = db.update(tableName1, values, where, whereArgs) > 0;
        db.close();
 
        return updateSuccessful;
    }
 
    // Delete location record.
    // @param id - to identify which location record is to be deleted.
    public boolean delete(int id) {
        boolean deleteSuccessful = false;
 
        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete(tableName1, "id ='" + id + "'", null) > 0;
        db.close();
 
        return deleteSuccessful;
 
    }
 
    /*
     * For more methods like reading single record or counting records, see my
     * full version of Android Sqlite tutorial, specifically,
     * LocationTableController.java
     */
}