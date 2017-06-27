package bstar128.example.hansung.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googlemap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googlemap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.524088,126.918927),17));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"위성 지도");
        menu.add(0,2,0,"일반 지도");
        menu.add(0,3,0,"중계CGV");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case 1: googlemap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case 2: googlemap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.639978,127.068558),17));
                return true;
        }
        return false;
    }
}
