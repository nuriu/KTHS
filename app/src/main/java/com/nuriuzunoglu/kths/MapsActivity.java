package com.nuriuzunoglu.kths;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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

        // Sydney üzerine bir işaretçi koy ve kamerayı taşı
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney üzerindeki işaretçi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onMapSearch(View view) {
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
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        System.out.println(view);
    }
}
