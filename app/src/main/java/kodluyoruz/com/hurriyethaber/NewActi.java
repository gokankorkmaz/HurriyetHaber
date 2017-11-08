package kodluyoruz.com.hurriyethaber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import kodluyoruz.com.hurriyethaber.Database.Database;

public class NewActi extends AppCompatActivity {

    Button dbBtnUpdate, dbBtnDelete;
    EditText dbPassword, dbid;
    AutoCompleteTextView dbUsername;
    TextView dbListed;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Hürriyet App");

        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setTitleTextColor(Color.WHITE);

        dbBtnUpdate = (Button) findViewById(R.id.dbBtnUpdate);
        dbBtnDelete = (Button) findViewById(R.id.dbBtnDelete);

        dbUsername = (AutoCompleteTextView) findViewById(R.id.dbUsername);
        dbPassword = (EditText) findViewById(R.id.dbPassword);
        dbid = (EditText) findViewById(R.id.dbid);
        dbListed = (TextView) findViewById(R.id.dbListed);


        listRecord();


        dbBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String updateName = dbUsername.getText().toString();
                String updateTc = dbPassword.getText().toString();
                String upId = dbid.getText().toString();


                if (upId.isEmpty() || updateTc.isEmpty() || updateName.isEmpty()) {
                    Toast.makeText(NewActi.this, "Güncellemek İstediğiniz Bilgileri Yazınız", Toast.LENGTH_SHORT).show();

                } else {
                    long updateId = Long.parseLong(upId);

                    Database updateDb = new Database(getApplicationContext());
                    try {
                        updateDb.databaseOpen();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    updateDb.updateRecord(updateId, updateName, updateTc);
                    updateDb.databaseClose();
                    Toast.makeText(NewActi.this, "Kayıt Güncellendi", Toast.LENGTH_SHORT).show();


                    toolbar.setTitle(updateName);

                }
                dbUsername.setText("");
                dbPassword.setText("");
                dbid.setText("");
                listRecord();


            }
        });


        dbBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String deleteRec = dbid.getText().toString();
                if (deleteRec.isEmpty()) {
                    Toast.makeText(NewActi.this, "Silinecek Kayda Ait Id'yi Giriniz", Toast.LENGTH_SHORT).show();
                } else {

                    long deleteID = Long.parseLong(deleteRec);

                    Database dbDelete = new Database(getApplicationContext());
                    try {
                        dbDelete.databaseOpen();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    dbDelete.deleteRecord(deleteID);

                    dbDelete.databaseClose();
                    Toast.makeText(NewActi.this, "Kayıt Silindi", Toast.LENGTH_SHORT).show();
                    dbUsername.setText("");
                    dbPassword.setText("");
                    dbid.setText("");
                    listRecord();

                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NewActi.this, MainActivity.class);
        intent.putExtra("toolbarTitle", toolbar.getTitle());

        startActivity(intent);
        finish();

    }


    private void listRecord() {

        //Kayıtları Listeleme
        Database db = new Database(getApplicationContext());
        try {
            db.databaseOpen();
        } catch (SQLException e) {
            Toast.makeText(NewActi.this, e.toString() + " ", Toast.LENGTH_SHORT).show();
        }

        String allRecords = db.listRecord();

        db.databaseClose();
        dbListed.setText(allRecords);

    }
}
