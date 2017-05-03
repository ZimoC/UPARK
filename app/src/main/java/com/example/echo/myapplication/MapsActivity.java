package com.example.echo.myapplication;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.speech.tts.TextToSpeech;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng p = new LatLng(42.3889167, -71.2208033);
    private static final LatLng p1 = new LatLng(42.384802, -71.218219);
    private static final LatLng p2 = new LatLng(42.386396, -71.225954);
    private static final LatLng p3 = new LatLng(42.366146, -71.228616);
    private static final LatLng p4 = new LatLng(42.352055, -71.173902);

    private static final String addr = "175 Forest Street, Waltham, MA02452";
    private static final String addr1 = "1-99 Cedar Hill Ln, Waltham, MA02452";
    private static final String addr2 = "16 Forest Street, Waltham, MA02452";
    private static final String addr3 = "196 High St, Waltham";

    String addList[] = {addr,addr1, addr2, addr3};


    private static final float zoom = 13.0f;
    private TabHost tabs;
    private Button button;
    private TabHost.TabSpec spec;
    private TextView tvText;
    private EditText etText;
    private TextToSpeech tts;
    private ListView listview;
    private ArrayAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();

    Intent intent = new Intent("address");
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //To show AppBar for optionmenu.
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.show();

        tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();

        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.tab1);    //add tab view content
        spec.setIndicator("Map");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost container

        button = (Button) findViewById(R.id.searchButton);
        etText = (EditText) findViewById(R.id.searchET);

  /* Initialize a TabSpec for tab2 and add it to the TabHost */
        spec = tabs.newTabSpec("tag2");        //create new tab specification
        spec.setContent(R.id.tab2);            //add view tab content
        spec.setIndicator("List");              //put text on tab
        tabs.addTab(spec);                     //put tab in TabHost container

   /* Initialize a TabSpec for tab3 and add it to the TabHost */
        spec = tabs.newTabSpec("tag3");        //create new tab specification
        spec.setContent(R.id.tab3);            //add view tab content
        spec.setIndicator("Share");              //put text on tab
        tabs.addTab(spec);                     //put tab in TabHost container

        IntentFilter mainFilter = new IntentFilter("address");
        mReceiver = new MyMainLocalReceiver();
        registerReceiver(mReceiver, mainFilter);

        //----------------------
        // Get ListView from XML
        listview = (ListView) findViewById(R.id.list);

        for (int i = 0; i < addList.length; ++i) {
            list.add(addList[i]);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent newActivity1 = new Intent(MapsActivity.this, space1.class);
                        startActivity(newActivity1);
                        break;
                    case 1:
                        Intent newActivity2 = new Intent(MapsActivity.this, space2.class);
                        startActivity(newActivity2);
                        break;
                    case 2:
                        Intent newActivity3 = new Intent(MapsActivity.this, space3.class);
                        startActivity(newActivity3);
                        break;
                    case 3:
                        Intent newActivity4 = new Intent(MapsActivity.this, space4.class);
                        startActivity(newActivity4);
                        break;
                }

            }

        });

    //Initialize Text to Speech engine (context, listener object)
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });

    }


    public class MyMainLocalReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String value = intent.getExtras().getString("address");
            addNotification(value);
            Log.v("Upark", value);
        }
    }

    private void addNotification(String value) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Upark")
                        .setContentText(value);

        Intent notificationIntent = new Intent(this, MapsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        button = (Button) findViewById(R.id.searchButton);

        mMap.addMarker(new MarkerOptions().position(p).title("Bentley University").snippet(addr));

        mMap.addMarker(new MarkerOptions().position(p1).title("").snippet(addr1));

        mMap.addMarker(new MarkerOptions().position(p2).title("").snippet(addr2));

        mMap.addMarker(new MarkerOptions().position(p3).title("").snippet(addr3));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p4, zoom));


        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {

                    public boolean onMarkerClick(Marker m) {
                        String title = m.getSnippet();
                        String snip = m.getSnippet();
                        Toast.makeText(getApplicationContext(), title + "\n" + snip, Toast.LENGTH_LONG).show();

                        if (snip.equals(addr)) {
                            Intent newActivity1 = new Intent(MapsActivity.this, space1.class);
                            startActivity(newActivity1);
                        }
                        if (snip.equals(addr1)) {
                            Intent newActivity2 = new Intent(MapsActivity.this, space2.class);
                            startActivity(newActivity2);
                        }
                        if (snip.equals(addr2)) {
                            Intent newActivity1 = new Intent(MapsActivity.this, space3.class);
                            startActivity(newActivity1);
                        }
                        if (snip.equals(addr3)) {
                            Intent newActivity1 = new Intent(MapsActivity.this, space4.class);
                            startActivity(newActivity1);
                        }
                        return true;
                    }
                }
        );

        mMap.setOnMapLongClickListener(
                new GoogleMap.OnMapLongClickListener() {
                    public void onMapLongClick(LatLng point) {
                        Toast.makeText(getApplicationContext(), "Long Tap", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
    // set search function to button

    public void onSearch(View view) {

        EditText location_tf = (EditText) findViewById(R.id.searchET);
        String location = location_tf.getText().toString();
        tts.speak(location, TextToSpeech.QUEUE_FLUSH, null);
        List<android.location.Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            android.location.Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            intent.putExtra("address","We are heading to: "+ location);
            sendBroadcast(intent);

        }
    }


    // on destroy
    public void onDestroy(){
        // shut down TTS engine
        super.onDestroy();

        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        //shut down broadcast receiver
        try {
            unregisterReceiver(mReceiver);
        } catch (Exception e) {

            Log.e ("Register", e.getMessage() );
        }
    }

    //Add option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.profile:
                Intent i1 = new Intent(this, profile.class);
                startActivity(i1);
                return true;

            case R.id.feedback:
                Uri uri2 = Uri.parse("mailto:customerservice@gmail.com");
                Intent i2 = new Intent(Intent.ACTION_SENDTO, uri2);
                startActivity(i2);

                return true;

            case R.id.close:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ?");
                builder.setPositiveButton("I am sure!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(MapsActivity.this,Login.class);
                        startActivity(intent);

                        finish();

                    }
                });

                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}






