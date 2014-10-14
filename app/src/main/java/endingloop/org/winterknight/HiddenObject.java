package endingloop.org.winterknight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class HiddenObject extends Activity {
    private ImageView iImagen;
    private ImageView iSolucion;
    private TextView tPistas;
    private TextView tPresentation;

    private String[] imagenes;
    private String[] soluciones;
    private String[] objetos;
    private Resources res;

    private int questionID;
    private int hiddenID;
    private HashMap<String,Boolean> finded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_obejct);

        tPistas= (TextView) findViewById(R.id.items);
        tPresentation= (TextView) findViewById(R.id.explainationHid);
        iImagen= (ImageView) findViewById(R.id.imageHid);
        iSolucion= (ImageView) findViewById(R.id.imageSol);

        res= getResources();
        imagenes=res.getStringArray(R.array.image);
        soluciones=res.getStringArray(R.array.image_solutions);
        objetos=res.getStringArray(R.array.clues);

        finded=new HashMap<String, Boolean>();
        finded.put("00ff29",false);//green
        finded.put("0026fd",false);//blue
        finded.put("fe0000",false);//red
        finded.put("fdec00",false);//yellow
        finded.put("000000",false);//black

        SharedPreferences settings = getApplicationContext().getSharedPreferences("org.endingloop.winterknight.settings", Context.MODE_PRIVATE);
        int acceso=settings.getInt("last_answered", -1);

        Intent intent = getIntent();
        questionID=intent.getIntExtra("position",-1);

        if(acceso<questionID){
            Log.d("INTENT", "" + questionID);
            switch(questionID){
                case 0:
                    hiddenID=0;
                    tPistas.setText(objetos[hiddenID]);
                    break;
                case 1:
                    hiddenID=1;
                    tPistas.setText(objetos[hiddenID]);
                    break;
                case 2:
                    hiddenID=2;
                    tPistas.setText(objetos[hiddenID]);
                    break;
                case 3:
                    hiddenID=3;
                    tPistas.setText(objetos[hiddenID]);
                    break;
                default:
                    tPistas.setText("Ha ocurrido un error");
                    break;
            }
            /*
            Añadir aqui on ontouch listener a la imagen de abajo que es la que se ve
            y con eso capturar la x y la y

             */
            iImagen.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int x=(int)motionEvent.getX();
                    int y=(int)motionEvent.getY();
                    Log.d("COORDS","x:"+x+" - y: "+y);
                    int color=getColour(x,y);
                    //color=color*-1;
                    Log.d("COLOR",""+color);
                    String hexColor=Integer.toHexString(color);
                    hexColor=hexColor.substring(2);
                    Log.d("COLOR CONVERT",""+hexColor);
                    int red=Color.red(color);
                    int green=Color.green(color);
                    int blue=Color.blue(color);
                    Log.d("COLORS","Red: "+red+" - green: "+green+" - blue"+blue);
                    if(finded.get(hexColor)!=null){
                        if(finded.get(hexColor)){
                            Toast.makeText(HiddenObject.this, "¡Ya habías encontrado este!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            finded.put(hexColor+"",true);
                            Log.d("Encontrados:",""+finded.size());
                            Toast.makeText(HiddenObject.this, "¡Has encontrado uno!", Toast.LENGTH_SHORT).show();
                            if(!finded.containsValue(false)){
                                Toast.makeText(HiddenObject.this, "¡Felicidades has encontrado todos!", Toast.LENGTH_SHORT).show();
                                tPresentation.setText("¡Ya has superado este acertijo!");
                                tPistas.setText("¡Prueba con el siguiente!");
                                tPresentation.setTextColor(Color.parseColor("#99CC00"));
                                tPistas.setTextColor(Color.parseColor("#99CC00"));
                                iImagen.setEnabled(false);
                                iSolucion.setEnabled(false);
                                /*
                                Falta añadir a las preferencias que este acertijo esta superado.
                                 */
                            }
                        }
                    }
                    return false;
                }
            });
        }
        else{
            tPresentation.setText("¡Ya has superado este acertijo!");
            tPistas.setText("¡Prueba con el siguiente!");
            tPistas.setTextColor(Color.parseColor("#99CC00"));
            tPresentation.setTextColor(Color.parseColor("#99CC00"));
            iImagen.setEnabled(false);
            iSolucion.setEnabled(false);
        }


    }

    private int getColour( int x, int y){
        //ImageView img = (ImageView) findViewById(R.id.imageSol);
        iSolucion.setDrawingCacheEnabled(true);
        Bitmap hotspots=Bitmap.createBitmap(iSolucion.getDrawingCache());
        iSolucion.setDrawingCacheEnabled(false);
        /*Drawable imgDrawable = iSolucion.getDrawable();
        Bitmap mutableBitmap = Bitmap.createBitmap(iSolucion.getWidth(), iSolucion.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutableBitmap);
        imgDrawable.draw(canvas);
        int pixel = mutableBitmap.getPixel(x,y);*/
        return hotspots.getPixel(x, y);
        //return pixel;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hidden_obejct, menu);
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
