package com.test.designmodelearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.test.designmodelearning.oop_principles.imageload_demo.ImageLoader;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imge);
        ImageLoader loader=new ImageLoader();
        String url="http://img.taopic.com/uploads/allimg/110915/29-11091512035335.jpg";
        loader.displayImage(url,imageView);
    }
}
