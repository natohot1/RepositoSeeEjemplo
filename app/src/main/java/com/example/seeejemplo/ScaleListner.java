package com.example.seeejemplo;

import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class ScaleListner extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    private ImageView imageView;
    private float scale =1;
    public ScaleListner(ImageView imageView){
        this.imageView=imageView;
    }

    public boolean onScale(ScaleGestureDetector detector){
        scale *=detector.getScaleFactor();
        imageView.setScaleX(scale);
        imageView.setScaleY(scale);
        return true;
    }

}
