package app.wizzeye.app.headwork;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import com.iristick.smartglass.support.app.HudActivity;
import com.iristick.smartglass.support.app.HudPresentation;

import app.wizzeye.app.BaseActivity;
import app.wizzeye.app.R;

/**
 * This example displays the current time on the display, using standard Android UI elements.
 */
public class HeadworkActivity extends BaseActivity implements HudActivity, LocationListener {

    //private FusedLocationProviderClient fusedLocationClient;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headwork_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(R.string.app_name);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    public void onLocationChanged(Location location) {
        //TextView txtLat = (TextView) findViewById(R.id.textview1);
        //txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        Log.d("locationPerso", String.valueOf(location.getLatitude()));
        Log.d("locationPerso", String.valueOf(location.getLongitude()));
    }

    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }



    @Override
    public HudPresentation onCreateHudPresentation(@NonNull Display display) {
        return new ResponseHud(this, display);
    }


}
