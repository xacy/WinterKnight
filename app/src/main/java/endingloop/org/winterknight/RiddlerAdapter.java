package endingloop.org.winterknight;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.pkmmte.view.CircularImageView;

/**
 * Created by jacinto on 04/10/2014.
 */
public class RiddlerAdapter extends BaseAdapter {
    private Context mContext;
    private Time today;

    public RiddlerAdapter(Context c) {
        mContext = c;
        today = new Time(Time.getCurrentTimezone());
        today.setToNow();
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        CircularImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new CircularImageView (mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(105, 105));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (CircularImageView ) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        Time custom=new Time();
        custom.set(position+1,(position>=24)?11:9,2014);
        Log.d("TIME TODAY",""+today.monthDay+"-"+today.month+"]["+custom.monthDay+custom.month);

        if(today.monthDay<custom.monthDay && today.month==custom.month){
            Log.d("alpha","es el mismo dia que position");
            imageView.getDrawable().mutate().setAlpha(70);
            //Esto no funciona con los imageview circulares así que habrá que hacer 2 columnas de recursos para los que esten disponibles y para los que no
            //los que no lo esten estaran en gris en photoshop y pista
        }
       /* if(today.monthDay<(position+1)){
            Log.d("alpha","es el mismo dia que position");
            imageView.getDrawable().mutate().setAlpha(70);
        }*/
        return imageView;
    }
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.numero_1, R.drawable.numero_2,
            R.drawable.numero_3, R.drawable.numero_4,
            R.drawable.numero_5, R.drawable.numero_6,
            R.drawable.numero_7, R.drawable.numero_8,
            R.drawable.numero_9, R.drawable.numero_10,
            R.drawable.numero_11, R.drawable.numero_12,
            R.drawable.numero_13, R.drawable.numero_14,
            R.drawable.numero_15, R.drawable.numero_16,
            R.drawable.numero_17, R.drawable.numero_18,
            R.drawable.numero_19, R.drawable.numero_20,
            R.drawable.numero_21, R.drawable.numero_22,
            R.drawable.numero_23, R.drawable.numero_24,
            R.drawable.numero_25, R.drawable.numero_26,
            R.drawable.numero_27, R.drawable.numero_28
    };
}