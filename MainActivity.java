package com.example.myapplicationopencv;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btRGB, btGray, btYcrCb, btHSV1, btHSV;
    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRGB = (Button) findViewById(R.id.btRGB);
        btGray = (Button) findViewById(R.id.btGray);
        btYcrCb = (Button) findViewById(R.id.btYcrCb);
        btHSV1 = (Button) findViewById(R.id.btHSV1);
        btHSV = (Button) findViewById(R.id.btHsv);

        btRGB.setOnClickListener(onClickTet);
        btGray.setOnClickListener(onClickTet);
        btYcrCb.setOnClickListener(onClickTet);
        btHSV1.setOnClickListener(onClickTet);

        imgView = (ImageView) findViewById(R.id.imgView);
    }


    private  View.OnClickListener onClickTet = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "1111111111111");

            Mat src = new Mat();
            Mat dst = new Mat();

            try {
                src = Utils.loadResource(MainActivity.this, R.drawable.demo1, Imgcodecs.IMREAD_COLOR);
                if (v.equals(btRGB)) {
                    dst = src;
                } else if (v.equals(btGray)) {
                    Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
                } else if (v.equals(btYcrCb)) {
                    Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2YCrCb);
                }  else if (v.equals(btHSV1)) {
                    Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2HSV);
                    Log.d(TAG, "hsv1");
                }

                Bitmap img = Bitmap.createBitmap(dst.cols(), dst.rows(), Bitmap.Config.ARGB_8888);

                Utils.matToBitmap(dst, img);

                imgView.setImageBitmap(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "OpenCVLoader not loaded");
        } else {
            Log.d(TAG, "OOOOOOOOOOOOOkee");

        }
    }

    public void onHSVClicked(View view) {
        Log.d(TAG, "Else- Nothing");
        Toast.makeText(this, "onHSVClicked", Toast.LENGTH_SHORT).show();
    }
}
