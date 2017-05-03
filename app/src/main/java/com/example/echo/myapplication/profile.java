
package com.example.echo.myapplication;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.echo.myapplication.Login._email;
import static com.example.echo.myapplication.Login._fname;
import static com.example.echo.myapplication.Login._pass;
import static com.example.echo.myapplication.Login._phone;


public class profile extends AppCompatActivity {

    public TextView name1,phone1,email1,passwd1;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.profile);

        //To hide AppBar for full screen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        name1 = (TextView) findViewById(R.id.name);
        phone1 = (TextView) findViewById(R.id.phone);
        email1 = (TextView) findViewById(R.id.email);
        passwd1 = (TextView) findViewById(R.id.password);
//set name form database
        name1.setText(_fname);
        phone1.setText(_phone);
        email1.setText(_email);
        passwd1.setText(_pass);
    }

}
