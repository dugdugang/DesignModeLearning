package com.test.designmodelearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.test.designmodelearning.oop_principles.SecondActivity;
import com.test.designmodelearning.oop_principles.imageload_demo.DoubleCache;
import com.test.designmodelearning.oop_principles.imageload_demo.ImageLoader;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imge);
        ImageLoader loader=new ImageLoader();
        loader.setmCache(new DoubleCache());
        String url="http://img.taopic.com/uploads/allimg/110915/29-11091512035335.jpg";
        Log.i("M-TAG","00000");
        loader.displayImage(url,imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
                finish();
            }
        });
    }
}
