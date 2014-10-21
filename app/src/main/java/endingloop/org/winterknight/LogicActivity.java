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


public class LogicActivity extends Activity {

    private EditText mAnswer;
    private TextView mLogic;
    private Button bAnswer;
    private Resources res;
    private String[] logic;
    private String[] solutions;
    private int logicID;
    private int questionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);

        mAnswer= (EditText) findViewById(R.id.answerLogic);
        mLogic= (TextView) findViewById(R.id.logicProblem);
        bAnswer= (Button) findViewById(R.id.answer_button_logic);
        res= getResources();
        logic=res.getStringArray(R.array.logics);
        solutions=res.getStringArray(R.array.logics_solutions);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("org.endingloop.winterknight.settings", Context.MODE_PRIVATE);
        int acceso=settings.getInt("last_answered", -1);

        Intent intent = getIntent();
        questionID=intent.getIntExtra("position",-1);

        if(acceso<questionID){
            Log.d("INTENT", "" + questionID);
            switch(questionID){
                case 0:
                    logicID=0;
                    mLogic.setText(logic[0]);
                    break;
                case 1:
                    logicID=1;
                    mLogic.setText(logic[1]);
                    break;
                case 2:
                    logicID=2;
                    mLogic.setText(logic[2]);
                    break;
                case 3:
                    logicID=3;
                    mLogic.setText(logic[3]);
                    break;
                default:
                    mLogic.setText("Ha ocurrido un error");
                    break;
            }
            bAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptAnswer();
                }
            });
        }
        else{
            mLogic.setText("¡Ya has superado este acertijo!");
            mLogic.setTextColor(Color.parseColor("#99CC00"));
            mAnswer.setEnabled(false);
        }

    }

    public void attemptAnswer() {
        Log.d("SOLUCION",mAnswer.getText()+" - "+solutions[logicID].toLowerCase()+" - "+logicID);
        if(mAnswer.getText().toString().equals(solutions[logicID].toLowerCase())){
            Log.d("SOLUCION","son iguales");
            //Guardamos la ultima pregunta resuelta y mostramos mensaje de acierto.
            /*SharedPreferences settings =getApplicationContext().getSharedPreferences("org.endingloop.winterknight.settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("last_answered", questionId);*/
            Toast.makeText(LogicActivity.this, "¡Has acertado!", Toast.LENGTH_LONG).show();
            mLogic.setText("¡Respuesta correcta!");
            mLogic.setTextColor(Color.parseColor("#99CC00"));
            mAnswer.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logic, menu);
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
