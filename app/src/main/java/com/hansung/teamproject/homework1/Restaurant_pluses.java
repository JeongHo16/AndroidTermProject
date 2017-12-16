package com.hansung.teamproject.homework1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.hansung.teamproject.homework1.RestaurantMap.myMarker;

/**
 * Created by Junho on 2017-11-18.
 */

public class Restaurant_pluses extends AppCompatActivity {

    final int REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA = 1;
    final int REQUEST_IMAGE_CAPTURE = 100;

    File mPhotoFile;
    String mPhotoFileName;

    ResHelper resHelper;
    Uri imageUri;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_pluses);

        checkDangerousPermissions();        // 파일 권한 확인 - 읽기, 쓰기, 위치

        final ImageButton camera = (ImageButton) findViewById(R.id.cameraBtn);
        camera.setOnClickListener(new View.OnClickListener() {             // 카메라 눌렀을 경우 동작
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();                // 카메라 사진 찍기
            }
        });

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText phone = (EditText) findViewById(R.id.phone);

        Intent regIntent = getIntent();
        address.setText(regIntent.getStringExtra("address"));

        resHelper = new ResHelper(this);
        Button pluses = (Button) findViewById(R.id.pluses);
        pluses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plusesName = String.valueOf(name.getText());
                String plusesAddress = String.valueOf(address.getText());
                String plusesPhone = String.valueOf(phone.getText());
                String plusesImageUri = String.valueOf(imageUri);

                Cursor cursor = resHelper.getAllUsersBySQL();

                while(cursor.moveToNext()){     // cursor가 움직이면서 입력된 가게이름과 같은게 있는지 확인하고 있으면 count ++
                    if(cursor.getString(2).equals(plusesName)){
                        count ++;
                        Toast.makeText(getApplicationContext(), "이미 등록된 맛집입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(count == 0){             //count가 만약 0이면 그대로 db에 입력해주기
                    resHelper.insertUserByMethod(plusesImageUri, plusesName, plusesAddress, plusesPhone);

                    LatLng latLng = getLatLng(plusesAddress);
                    myMarker.add(new com.hansung.teamproject.homework1.Marker(latLng, plusesName));

                    Toast.makeText(getApplicationContext(), "맛집이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    count = 0;
                    //return;
                }
                /*Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);        // 인텐트 선언
                intent.putExtra("plusesName", plusesName);*/
                Intent intent = new Intent(getApplicationContext(), RestaurantMap.class);
                startActivity(intent);                  //인텐트 넘기기
            }
        });
    }

    private void dispatchTakePictureIntent() {      // 카메라 찍기 //10주차 실습과제 참고코드
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
            mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
            if (mPhotoFile !=null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                imageUri = FileProvider.getUriForFile(this, "com.hansung.teamproject.homework1", mPhotoFile);
                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                Toast.makeText(getApplicationContext(), "file null", Toast.LENGTH_SHORT).show();
        }
    }

    private String currentDateFormat(){  //10주차 실습과제 참고코드
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //10주차 실습과제 참고코드
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);

                ImageButton camera = (ImageButton) findViewById(R.id.cameraBtn);
                camera.setImageURI(Uri.fromFile(mPhotoFile));

                // 파일 추가
                File saveFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File[] files = saveFile.listFiles();
                if (files != null) {
                    for (File f : files) {
                        Log.i("Save_pictures", "File name = " + f.getName());
                        Log.i("Save_pictures_path", "Picture_path = " + f.getPath());
                        Uri.parse("files://" + Environment.getExternalStorageDirectory().getPath() + "/Pictures/" + f.getName());
                    }
                }
            }
        }
    }

    /// 권한 확인
    private void checkDangerousPermissions() { //강의자료 참고 코드
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                //Manifest.permission.ACCESS_FINE_LOCATION      RestaurantMap에서 권한 확인
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);
        }
    }

    LatLng getLatLng(String address) {
        LatLng getLatLng = null;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(address,1);
            if (addresses.size() >0) {
                Address bestResult = (Address) addresses.get(0);

                getLatLng = new LatLng(bestResult.getLatitude(), bestResult.getLongitude());
            }
        } catch (IOException e) {
            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
        }
        return getLatLng;
    }
}
