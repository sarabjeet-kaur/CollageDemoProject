package com.example.collagedemoproject;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.collagedemoproject.utility.AppConstants.REQUEST_OPEN_GALLERY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button make_collage,open_gallery;
   private TextView array_size;
     private ArrayList<Uri> mArrayUri;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        make_collage = (Button) findViewById(R.id.make_collage);
        open_gallery = (Button) findViewById(R.id.open_gallery);
        array_size = (TextView) findViewById(R.id.array_size);
        make_collage.setOnClickListener(this);
        open_gallery.setOnClickListener(this);


    }


    //method for open gallery
    private void activeGallery() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_OPEN_GALLERY);
        } else {
            startImageSelection();


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_OPEN_GALLERY:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startImageSelection();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_OPEN_GALLERY);
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_OPEN_GALLERY){

            if(resultCode==RESULT_OK){
                //data.getParcelableArrayExtra(name);

                //If Single image selected then it will fetch from Gallery
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();
                    Toast.makeText(this, "Select Atleast 2 Images", Toast.LENGTH_SHORT).show();


                }else{
                    if(data.getClipData()!=null){
                        ClipData mClipData=data.getClipData();
                        mArrayUri=new ArrayList<Uri>();
                        for(int i=0;i<mClipData.getItemCount();i++){

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                        }
                        Log.v("LOG_TAG", "Selected Images"+ mArrayUri.size());
                        array_size.setVisibility(View.VISIBLE);

                        array_size.setText("Selected Images:-" + mArrayUri.size()+"");

                        int size= mArrayUri.size();
                        if (size>=2){
                            make_collage.setVisibility(View.VISIBLE);
                        }else{
                            make_collage.setVisibility(View.GONE);
                        }

                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startImageSelection() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_OPEN_GALLERY);
    }

    private void addImageOnCollage(){
        if (mArrayUri.size()>10){
            Toast.makeText(this, "Only 10 Images are allowed...", Toast.LENGTH_SHORT).show();
        }
      switch (mArrayUri.size()){
          case 2:
             setImageInCollageForTwo();
              break;
          case 3:
              setImageInCollageForThree();
              break;
          case 4:
              setImageInCollageForFour();
              break;
          case 5:
              setImageInCollageForFive();
              break;
          case 6:
              setImageInCollageForSix();
              break;
          case 7:
              setImageInCollageForSeven();
              break;
          case 8:
              setImageInCollageForEight();
              break;
          case 9:
              setImageInCollageForNine();
              break;
          case 10:
              setImageInCollageForTen();
              break;
      }
    }

    private void setImageInCollageForTwo(){
        setContentView(R.layout.collage_for_two);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView[] images={image_one,image_two};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);
        }
    }
    private void setImageInCollageForThree(){
        setContentView(R.layout.collage_for_three);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView[] images={image_one,image_two,image_three};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);
        }

    }
    private void setImageInCollageForFour(){
        setContentView(R.layout.collage_for_four);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView[] images={image_one,image_two,image_three,image_four};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);
        }

    }

    private void setImageInCollageForFive(){
        setContentView(R.layout.collage_for_five);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView image_five=(ImageView)findViewById(R.id.image_five);
        ImageView[] images={image_one,image_two,image_three,image_four,image_five};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);
        }
    }
    private void setImageInCollageForSix(){
        setContentView(R.layout.collage_for_six);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView image_five=(ImageView)findViewById(R.id.image_five);
        ImageView image_six=(ImageView)findViewById(R.id.image_six);
        ImageView[] images={image_one,image_two,image_three,image_four,image_five,image_six};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);

        }
    }
    private void setImageInCollageForSeven(){
        setContentView(R.layout.collage_for_seven);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView image_five=(ImageView)findViewById(R.id.image_five);
        ImageView image_six=(ImageView)findViewById(R.id.image_six);
        ImageView image_seven=(ImageView)findViewById(R.id.image_seven);
        ImageView[] images={image_one,image_two,image_three,image_four,image_five,image_six,image_seven};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);

        }
    }

    private void setImageInCollageForEight(){
        setContentView(R.layout.collage_for_eight);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView image_five=(ImageView)findViewById(R.id.image_five);
        ImageView image_six=(ImageView)findViewById(R.id.image_six);
        ImageView image_seven=(ImageView)findViewById(R.id.image_seven);
        ImageView image_eight=(ImageView)findViewById(R.id.image_eight);
        ImageView[] images={image_one,image_two,image_three,image_four,image_five,image_six,image_seven,image_eight};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);

        }
    }
    private void setImageInCollageForNine(){
        setContentView(R.layout.collage_for_nine);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView image_five=(ImageView)findViewById(R.id.image_five);
        ImageView image_six=(ImageView)findViewById(R.id.image_six);
        ImageView image_seven=(ImageView)findViewById(R.id.image_seven);
        ImageView image_eight=(ImageView)findViewById(R.id.image_eight);
        ImageView image_nine=(ImageView)findViewById(R.id.image_nine);
        ImageView[] images={image_one,image_two,image_three,image_four,image_five,image_six,image_seven,image_eight,image_nine};
        for (int i=0;i<mArrayUri.size();i++){
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);

        }
    }
    private void setImageInCollageForTen(){
        setContentView(R.layout.collage_for_ten);
        ImageView image_one=(ImageView)findViewById(R.id.image_one);
        ImageView image_two=(ImageView)findViewById(R.id.image_two);
        ImageView image_three=(ImageView)findViewById(R.id.image_three);
        ImageView image_four=(ImageView)findViewById(R.id.image_four);
        ImageView image_five=(ImageView)findViewById(R.id.image_five);
        ImageView image_six=(ImageView)findViewById(R.id.image_six);
        ImageView image_seven=(ImageView)findViewById(R.id.image_seven);
        ImageView image_eight=(ImageView)findViewById(R.id.image_eight);
        ImageView image_nine=(ImageView)findViewById(R.id.image_nine);
        ImageView image_ten=(ImageView)findViewById(R.id.image_ten);
        ImageView[] images={image_one,image_two,image_three,image_four,image_five,image_six,image_seven,
                image_eight,image_nine,image_ten};
        for (int i=0;i<mArrayUri.size();i++) {
            Glide.with(MainActivity.this).load(mArrayUri.get(i)).skipMemoryCache(true).into(images[i]);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.open_gallery:
                activeGallery();
                break;
            case R.id.make_collage:
                addImageOnCollage();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count==1)
            setContentView(R.layout.activity_main);
        else
            finish();
    }
}
