package bstar128.example.hansung.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googlemap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googlemap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.524088,126.918927),17));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                loc_mark=new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.drawable.sombra_iconspray)).position(latLng,100f,100f);//f=m
                googleMap.addGroundOverlay(loc_mark);
            }
        });
    }
    public static  final int ITEM_SATELLITE=1;
    public static  final int ITEM_NORMAL=2;
    public static  final int ITEM_CGV=3;
    public static  final int ITEM_YOIDOPARK=4;
    public static  final int MARK_CLEAR=5;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,ITEM_SATELLITE,0,"위성 지도");
        menu.add(0,ITEM_NORMAL,0,"일반 지도");
        SubMenu hotMenu=menu.addSubMenu("핫 플레이스");
        hotMenu.add(0,ITEM_CGV,0,"중계 CGV");
        hotMenu.add(0,ITEM_YOIDOPARK,0,"여의도 공원");
        menu.add(0,MARK_CLEAR,0,"위치 아이콘 초기화");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case ITEM_SATELLITE: googlemap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NORMAL: googlemap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_CGV:
                googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.639978,127.068558),17));
                return true;
            case ITEM_YOIDOPARK:
                googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.524054,126.918916),17));
                return true;
            case MARK_CLEAR:
                googlemap.clear();
                return true;
        }
        return false;
    }
}
