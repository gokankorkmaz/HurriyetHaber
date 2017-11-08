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
import android.widget.Toast;

import kodluyoruz.com.hurriyethaber.Database.Database;

public class LogActivity extends AppCompatActivity {
    Button activity_main_btnLogin;
    EditText password;
    AutoCompleteTextView username;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Hürriyet App");
        toolbar.setTitleTextColor(Color.WHITE);
        username = (AutoCompleteTextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        activity_main_btnLogin = (Button) findViewById(R.id.activity_main_btnLogin);


        activity_main_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString();
                String tcId = password.getText().toString();


                try {

                    if (!name.isEmpty() && !tcId.isEmpty()) {

                        Database db = new Database(LogActivity.this);
                        db.databaseOpen();
                        db.save(name, tcId);
                        db.databaseClose();

                        Toast.makeText(LogActivity.this, "Kayıt Başarılı.", Toast.LENGTH_SHORT).show();

                        //Intent ile Diğer Activity'ye Veri Gönderme
                        Intent intent = new Intent(LogActivity.this, MainActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LogActivity.this, "Alanlar Boş Geçilemez.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(LogActivity.this, e.toString() + "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


