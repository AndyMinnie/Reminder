package andreacauchoix.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Andrea on 14/04/2015.
 */

public class BDD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Reminder";

    private static final String TABLE_RAPPEL  = "rappels";

    private static final String KEY_RAPPEL_ID="rappel_id";
    private static final String KEY_RAPPEL_TITRE="rappel_titre";
    private static final String KEY_RAPPEL_DATE="rappel_date";
    private static final String KEY_RAPPEL_LIEU="rappel_lieu";


    private static final String[] COLONNES_RAPPELS = { KEY_RAPPEL_ID, KEY_RAPPEL_TITRE, KEY_RAPPEL_DATE, KEY_RAPPEL_LIEU};

    private static final String CREATION_TABLE_RAPPELS = "CREATE TABLE "+TABLE_RAPPEL+" ( "+
            KEY_RAPPEL_ID + " INTEGER PRIMARY KEY , "+
            KEY_RAPPEL_TITRE+ " TEXT , "+
            KEY_RAPPEL_DATE + " TEXT," +
            KEY_RAPPEL_LIEU + " TEXT )";

    public BDD(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        Log.e("BDD Constructeur", "Construction !");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATION_TABLE_RAPPELS);
        Log.i("table "+TABLE_RAPPEL, "créé");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RAPPEL);

        onCreate(db);
    }

    public ArrayList<AjoutRappel> getRappel(){
        ArrayList<AjoutRappel> rappels = new ArrayList<AjoutRappel>();

        String query = "SELECT * FROM " + TABLE_RAPPEL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                AjoutRappel rappel = new AjoutRappel();
                rappel.setId(Integer.parseInt(cursor.getString(0)));
                rappel.setRappel(cursor.getString(1));
                rappel.setDate(cursor.getString(2));
                rappels.add(rappel);
            } while(cursor.moveToNext());
        }

        return rappels;
    }
    public AjoutRappel getRappel(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RAPPEL,
                COLONNES_RAPPELS,
                "rappel_id = ? ",
                new String[]{ String.valueOf(id)},
                null,
                null,
                null,
                null);
        if ( cursor != null){
            cursor.moveToFirst();
        }
        AjoutRappel rappel= new AjoutRappel();
        rappel.setId(Integer.parseInt(cursor.getString(0)));
        rappel.setRappel(cursor.getString(1));
        rappel.setDate(cursor.getString(2));

        return rappel;
    }
    public void modifRappel(AjoutRappel rappel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RAPPEL_ID, rappel.getId());
        values.put(KEY_RAPPEL_TITRE, rappel.getRappel());
        values.put(KEY_RAPPEL_DATE, rappel.getDate());
        values.put(KEY_RAPPEL_LIEU, rappel.getLieu());

        int i = db.update(TABLE_RAPPEL,
                values,
                "rappel_id = ?",
                new String[] { String.valueOf(rappel.getId()) });

        db.close();
    }

    //select type, count(type) from table group by type;
    public String getLieuById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RAPPEL,
                COLONNES_RAPPELS,
                "rappel_id = ? ",
                new String[]{ String.valueOf(id)},
                null,
                null,
                null,
                null);
        if ( cursor != null){
            cursor.moveToFirst();
        }

        return cursor.getString(3);
    }

    public void ajoutRappel(AjoutRappel rappel){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_RAPPEL_ID, getMax() + 1);
        values.put(KEY_RAPPEL_TITRE, rappel.getRappel());
        values.put(KEY_RAPPEL_DATE, rappel.getDate());
        values.put(KEY_RAPPEL_LIEU, rappel.getLieu());

        db.insert(TABLE_RAPPEL,
                null, values);
        db.close();
    }

    public int getMax(){
        String query = "SELECT MAX(rappel_id) FROM " + TABLE_RAPPEL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();
        //int nbrappels = Integer.parseInt(cursor.getString(0));

        return count;
    }

    public String getRappelById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RAPPEL,
                COLONNES_RAPPELS,
                "rappel_id = ? ",
                new String[]{ String.valueOf(id)},
                null,
                null,
                null,
                null);
        if ( cursor != null){
            cursor.moveToFirst();
        }

        String titre = cursor.getString(1);

        return titre ;

    }
    public String getDateById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RAPPEL,
                COLONNES_RAPPELS,
                "rappel_id = ? ",
                new String[]{ String.valueOf(id)},
                null,
                null,
                null,
                null);
        if ( cursor != null){
            cursor.moveToFirst();
        }

        String contenu = cursor.getString(2);

        return contenu ;
    }

}