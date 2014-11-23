package me.happyhls.picassodemo;

import me.happyhls.picassodemo.R;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class PostProcessActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_postprocess);
    ImageView imageview = (ImageView)findViewById(R.id.imageview);
    
    Picasso picasso = Picasso.with(getApplicationContext());
    DrawLineTransformation myTransformation = new DrawLineTransformation();
    picasso.load("http://www.baidu.com/img/bdlogo.png").transform(myTransformation).into(imageview);
    
  }

  class DrawLineTransformation implements Transformation {

    @Override
    public String key() {
      // TODO Auto-generated method stub
      return "drawline";
    }

    @Override
    public Bitmap transform(Bitmap bitmap) {
      // TODO Auto-generated method stub
      synchronized (DrawLineTransformation.class) {
        if(bitmap == null) {
          return null;
        }
        Bitmap resultBitmap = bitmap.copy(bitmap.getConfig(), true);
        Canvas canvas = new Canvas(resultBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawLine(0, resultBitmap.getHeight()/2, resultBitmap.getWidth(), resultBitmap.getHeight()/2, paint);
        bitmap.recycle();
        return resultBitmap;
      }
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
