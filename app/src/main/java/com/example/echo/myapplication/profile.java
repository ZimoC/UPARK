
/* Tip button-- enter check amount, number of people and tip percentage(default is 15%)
* pushing the go button to calculate total bill, total per person, total tip, and tip per person
* Web button-- starts WebLookup to browse website
* Dial button-- automatically dial 781-891-2000
* Map button-- open google map that display Bentley University's location
*/

package com.example.echo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class profile extends Activity implements OnClickListener {



    public TextView URL;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.profile);

        URL=(TextView)findViewById(R.id.URL) ;
        URL.setOnClickListener(this);
        // Set up click listeners for all the buttons

    }


    // Perform action on click
    //avoids runtime check for permission to CALL_PHONE
    public void onClick(View v) throws SecurityException {
        Intent i1 = new Intent(this,WebLookup.class);
        startActivity(i1);
       // WebLookup.loadUrl("http://www.bentley.edu/");

    }
}
