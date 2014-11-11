package endingloop.org.winterknight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private int acceso;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View backgroundimage = findViewById(R.id.fondoMain);
        Drawable background = backgroundimage.getBackground();
        background.setAlpha(95);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new RiddlerAdapter(this));

        final Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        settings = getApplicationContext().getSharedPreferences("org.endingloop.winterknight.settings", Context.MODE_PRIVATE);
        acceso=settings.getInt("last_answered", -1);

        Log.d("TIME TEST", today.monthDay + "-" + today.month);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //hacer aquí la comprobación de que esta activo y sí es así permitir el click.
                Log.d("PRUEBA TEST","acceso: "+acceso+"- "+ today.monthDay + "-" + today.month);
                if(acceso<=(position+1)){
                    //cambiar el 10 por el 11
                    int tempday=(today.month==10)?today.monthDay-6:today.monthDay+23;
                    Log.d("FECHA","MONTH:"+today.month+" - dia"+today.monthDay);
                    Log.d("TEMP",""+tempday);
                    Log.d("POSITION",""+position);
                    if(tempday<(position+1)){
                        Toast.makeText(MainActivity.this,"Todavía no ha llegado el día", Toast.LENGTH_SHORT).show();
                    }
                    else{
                       // Toast.makeText(MainActivity.this, "Acceso concedido", Toast.LENGTH_SHORT).show();
                        //  Intent intent =new Intent(MainActivity.this, RiddlesActivity.class);

                        position=position+1;
                        //Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                        List<Integer> hidden= Arrays.asList(1,4,7,10,13,16,19,22,25,28);
                        List<Integer> logic=Arrays.asList(2,5,8,11,14,17,20,23,26);
                        List<Integer> riddles=Arrays.asList(3,6,9,12,15,18,21,24,27);
                        Intent intent;
                        if(hidden.contains(position)){
                            intent =new Intent(MainActivity.this, HiddenObject.class);
                            //Toast.makeText(MainActivity.this, ""+hidden.indexOf(position), Toast.LENGTH_SHORT).show();
                            intent.putExtra("position",(hidden.indexOf(position)));
                            intent.putExtra("id",position);
                            Log.d("GLOBAL",""+position);
                            startActivity(intent);
                        }
                        else if(logic.contains(position)){
                            intent =new Intent(MainActivity.this, LogicActivity.class);
                            intent.putExtra("position",(logic.indexOf(position)));
                            intent.putExtra("id",position);
                            Log.d("GLOBAL",""+position);
                            startActivity(intent);
                        }
                        else if(riddles.contains(position)){
                            intent =new Intent(MainActivity.this, RiddlesActivity.class);
                            intent.putExtra("position",(riddles.indexOf(position)));
                            intent.putExtra("id",position);
                            Log.d("GLOBAL",""+position);
                            startActivity(intent);
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"No se porque estas aqui", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        int acceso=settings.getInt("last_answered", -1);
        //Toast.makeText(MainActivity.this, "Prueba de resume", Toast.LENGTH_SHORT).show();
        if(acceso==28){
            //meter aquí la ventana dando la enhorabuena
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage(R.string.dialog_message)
                                .setTitle(R.string.dialog_title);

            // 3. Get the AlertDialog from create()
                        AlertDialog dialog = builder.create();
            dialog.show();
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
        int id = item.getItemId();/*
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
