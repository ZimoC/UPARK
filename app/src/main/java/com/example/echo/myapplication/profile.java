
/* Tip button-- enter check amount, number of people and tip percentage(default is 15%)
* pushing the go button to calculate total bill, total per person, total tip, and tip per person
* Web button-- starts WebLookup to browse website
* Dial button-- automatically dial 781-891-2000
* Map button-- open google map that display Bentley University's location
*/

package com.example.echo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.echo.myapplication.Login._email;
import static com.example.echo.myapplication.Login._fname;
import static com.example.echo.myapplication.Login._pass;
import static com.example.echo.myapplication.Login._phone;


public class profile extends Activity {

    public TextView name1,phone1,email1,passwd1;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.profile);
        name1 = (TextView) findViewById(R.id.name);
        phone1 = (TextView) findViewById(R.id.phone);
        email1 = (TextView) findViewById(R.id.email);
        passwd1 = (TextView) findViewById(R.id.password);

        name1.setText(_fname);
        phone1.setText(_phone);
        email1.setText(_email);
        passwd1.setText(_pass);
    }

}
