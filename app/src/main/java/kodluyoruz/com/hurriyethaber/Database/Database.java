package kodluyoruz.com.hurriyethaber.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class Database {
    // Sutunlar
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_NAME = "isim";
    public static final String KEY_TC = "telefon";
    private static final String DATABASE_NAME = "Kisiler";
    private static final String DATABASE_TABLE = "Tablo";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public Database(Context context) {
        this.context = context;
    }


    public Database databaseOpen() throws SQLException {

        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();

        return this;

    }

    public void databaseClose() {

        databaseHelper.close();

    }

    public long save(String ad, String tc) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, ad);
        cv.put(KEY_TC, tc);

        return database.insert(DATABASE_TABLE, null, cv);

    }

    public String listRecord() {

        String[] columns = new String[]{KEY_ROW_ID, KEY_NAME, KEY_TC};
        Cursor cursor = database.query(DATABASE_TABLE, columns, null, null,
                null, null, null);

        String allRecords = "";

        //sütunların id'leri değişkenlere atandı id 0 isim 1 tc 2 seklinde
        int idNo = cursor.getColumnIndex(KEY_ROW_ID);
        int nameNo = cursor.getColumnIndex(KEY_NAME);
        int tcNo = cursor.getColumnIndex(KEY_TC);

        //tüm kayıtların okunması bu for döngüsüyle sağlandı
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            allRecords = allRecords + cursor.getString(idNo) + "    "
                    + cursor.getString(nameNo) + "  " + cursor.getString(tcNo)
                    + " \n";
        }

        return allRecords;
    }

    public void updateRecord(long Id, String name,
                             String tc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_TC, tc);

        database.update(DATABASE_TABLE, contentValues, KEY_ROW_ID + "="
                + Id, null);

    }

    public void deleteRecord(long Id) {
        database.delete(DATABASE_TABLE, KEY_ROW_ID + "=" + Id, null);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROW_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_NAME
                    + " TEXT NOT NULL, " + KEY_TC + " TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }

    }
}