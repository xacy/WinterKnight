package endingloop.org.winterknight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RiddlesActivity extends Activity {
    private EditText mAnswer;
    private TextView mRiddle;
    private Button bAnswer;
    private Resources res;
    private String[] riddles;
    private String[] solutions;
    private int riddleId;
    private int questionId;
    private int global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddles);

        mAnswer= (EditText) findViewById(R.id.answer);
        mRiddle= (TextView) findViewById(R.id.riddle);
        bAnswer= (Button) findViewById(R.id.answer_button);
        res= getResources();
        riddles=res.getStringArray(R.array.riddles);
        solutions=res.getStringArray(R.array.riddles_solutions);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("org.endingloop.winterknight.settings", Context.MODE_PRIVATE);
        int acceso=settings.getInt("last_answered", 0);

        Intent intent = getIntent();
        questionId=intent.getIntExtra("position",-1);
        global=intent.getIntExtra("id",-1);
        Log.d("GLOBAL",""+global+" acceso "+acceso);
        if(acceso==(global-1)){
            Log.d("INTENT",""+questionId);
            mRiddle.setText(riddles[questionId]);/*
            switch(questionId){
                case 0:
                    riddleId=0;
                    mRiddle.setText(riddles[0]);
                    break;
                case 1:
                    riddleId=1;
                    mRiddle.setText(riddles[1]);
                    break;
                case 2:
                    riddleId=2;
                    mRiddle.setText(riddles[2]);
                    break;
                case 3:
                    riddleId=3;
                    mRiddle.setText(riddles[3]);
                    break;
                default:
                    mRiddle.setText("Ha ocurrido un error");
                    break;
            }*/
            bAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptAnswer();
                }
            });
        }
        else if(acceso<global){
            mRiddle.setText("¡No has resuelto los anteriores!");
            mRiddle.setTextColor(Color.parseColor("#F44336"));
            mAnswer.setEnabled(false);
        }
        else{
            mRiddle.setText("¡Ya has superado este acertijo!");
            mRiddle.setTextColor(Color.parseColor("#99CC00"));
            mAnswer.setEnabled(false);
        }

    }

    public void attemptAnswer() {
        Log.d("SOLUCION",mAnswer.getText()+" - "+solutions[questionId].toLowerCase()+" - "+questionId);
        if(mAnswer.getText().toString().toLowerCase().equals(solutions[questionId].toLowerCase())){
            Log.d("SOLUCION","son iguales");
            //Guardamos la ultima pregunta resuelta y mostramos mensaje de acierto.
            SharedPreferences settings =getApplicationContext().getSharedPreferences("org.endingloop.winterknight.settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("last_answered", global);
            Toast.makeText(RiddlesActivity.this, "¡Has acertado!", Toast.LENGTH_LONG).show();
            mRiddle.setText("¡Respuesta correcta!");
            mRiddle.setTextColor(Color.parseColor("#99CC00"));
            mAnswer.setEnabled(false);
            editor.commit();
        }
        else{
            Toast.makeText(RiddlesActivity.this, "¡Respuesta incorrecta!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.riddles, menu);
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
