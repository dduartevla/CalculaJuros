package br.ufjf.dcc196.izabel.calculajuros;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText editTextValorPresente;
    TextView textViewValorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextValorPresente = findViewById(R.id.editTextValorPresente);

        textViewValorFinal = findViewById(R.id.textViewValorFinal);
    }

    public void jurosSimplesClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, JurosSimplesActivity.class);

            intent.putExtra("valorPresente", valorPresente);
            startActivityForResult(intent,1);
        } catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }

    }

    public void jurosCompostosClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, JurosSimplesActivity.class);

            intent.putExtra("valorPresente", valorPresente);
            startActivityForResult(intent,1);
        } catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Double valorFinal = extras.getDouble("valorFinal");
            textViewValorFinal.setText(valorFinal.toString());
        }
    }
}