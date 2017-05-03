
/* Tip button-- enter check amount, number of people and tip percentage(default is 15%)
* pushing the go button to calculate total bill, total per person, total tip, and tip per person
* Web button-- starts WebLookup to browse website
* Dial button-- automatically dial 781-891-2000
* Map button-- open google map that display Bentley University's location
*/

package com.example.echo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class spaceInfo extends Activity implements OnClickListener {




    public Button button;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.parkinfo);


        // Set up click listeners for all the buttons

        button = (Button) findViewById(R.id.dial);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.map);
        button.setOnClickListener(this);

    }


    // Perform action on click
    //avoids runtime check for permission to CALL_PHONE
    public void onClick(View v) throws SecurityException {
        switch (v.getId()){

            // active activity WebLookup

            // open built-in dialer, dials 7818912000
            case R.id.dial:
                Uri uri2 = Uri.parse("tel:7818912000");
                Intent i2 = new Intent(Intent.ACTION_DIAL,uri2);
                startActivity(i2);
                break;

            // open built-in google map application which display Bentley's location
            case R.id.map:
                Uri uri3 = Uri.parse("geo:0,0?q=Bentley University");
                Intent i3 = new Intent(Intent.ACTION_VIEW,uri3);

                /*For API18 and 19 Google Maps is not on Launch Pad so should first check
                if Package is present to avoid app crashing.*/
                if (i3.resolveActivity(getPackageManager()) != null) {
                    startActivity(i3);
                }
                break;
        }

    }
}
