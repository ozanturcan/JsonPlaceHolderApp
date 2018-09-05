package ozanturcan.com.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Legend on 3.09.2018.
 */

public class PhotoActivity extends AppCompatActivity {
    private TextView tvtitle;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        tvtitle = (TextView) findViewById(R.id.txttitle);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String image = intent.getExtras().getString("Thumbnail") ;

        // Setting values

        tvtitle.setText(Title);
        Glide.with(this).load(image).into(img);

    }
}
