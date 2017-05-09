package com.nuriuzunoglu.kths;

import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private EditText mLocationSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Harita hazır olduğunda onu manipüle eder.
     * Harita kullanıma hazır olduğunda çağrılır.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mLocationSearch = (EditText) findViewById(R.id.map_search);
        mMap = googleMap;

        // Harita stilini değiştir
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Harita stili okunamadı.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Harita stili bulunamadı. Hata: ", e);
        }

        // Sydney üzerine bir işaretçi koy ve kamerayı taşı
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney üzerindeki işaretçi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onMapSearch(View view) {
        mLocationSearch.clearFocus();
        String location = mLocationSearch.getText().toString();
        List<Address> addressList = null;

        if (!location.equals("")) {
            Geocoder geocoder = new Geocoder(this);

            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            // Kamerayı ilk bulunan yere taşı
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }

        System.out.println(view);
    }
}
