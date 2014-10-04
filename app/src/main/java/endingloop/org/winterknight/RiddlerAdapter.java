package endingloop.org.winterknight;

import android.content.Context;
import android.text.format.Time;
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

        if(today.monthDay<(position+1)){
            imageView.getDrawable().mutate().setAlpha(70);
        }
        return imageView;
    }
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}