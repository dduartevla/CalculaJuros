package br.ufjf.dcc196.izabel.calculajuros;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_JUROS_SIMPLES = 1;
    public static final int REQUEST_JUROS_COMPOSTO = 2;
    public static final int REQUEST_VALOR_FUTURO = 3;

    ActivityResultLauncher<Intent> launcher;
    private EditText editTextValorPresente;
    //TextView textViewValorFinal;
    Locale locale = new Locale("pt","BR");
    TextView textViewPorcentagem;
    TextView textViewValorFuturo;
    Double valorFinal = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextValorPresente = findViewById(R.id.editTextValorPresente);

        //textViewValorFinal = findViewById(R.id.textViewValorFinal);
        textViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        textViewValorFuturo = findViewById(R.id.textViewValorFinal);
         launcher = registerForActivityResult(
                 new ActivityResultContracts.StartActivityForResult(),
                 new ActivityResultCallback<ActivityResult>(){

                     @Override
                     public void onActivityResult (ActivityResult result){
                         Bundle extras;
                         Double valorPorcentagem;

                         switch (result.getResultCode()){
                             case REQUEST_JUROS_SIMPLES:

                                 extras = result.getData().getExtras();
                                 valorFinal = extras.getDouble("valorFinal");
                                 valorPorcentagem = extras.getDouble("valorPorcentagem");

                                 textViewPorcentagem.setText(valorPorcentagem + "%");
                                 textViewValorFuturo.setText(NumberFormat.getCurrencyInstance(locale).format(valorFinal));
                                 break;

                             case REQUEST_JUROS_COMPOSTO:

                                 extras = result.getData().getExtras();
                                 valorFinal = extras.getDouble("valorFinal");
                                 valorPorcentagem = extras.getDouble("valorPorcentagem");

                                 textViewPorcentagem.setText(valorPorcentagem + "%");
                                 textViewValorFuturo.setText(NumberFormat.getCurrencyInstance(locale).format(valorFinal));
                                 break;

                             case REQUEST_VALOR_FUTURO:

                                 extras = result.getData().getExtras();
                                 Double valorFuturo = extras.getDouble("valorFuturo");
                                 valorPorcentagem = extras.getDouble("valorPorcentagem");

                                 textViewPorcentagem.setText(valorPorcentagem + "%");

                                 textViewValorFuturo.setText(NumberFormat.getCurrencyInstance(locale).format(valorFuturo));

                                 break;

                         }
                     }
                 }
         );
    }

    public void jurosSimplesClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, JurosSimplesActivity.class);

            intent.putExtra("valorPresente", valorPresente);

            //startActivityForResult(intent,1);
            launcher.launch(intent);
        } catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }

    }

    public void jurosCompostosClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, JurosCompostosActivity.class);

            intent.putExtra("valorPresente", valorPresente);
           // startActivityForResult(intent,1);
            launcher.launch(intent);

        } catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    public void valorFuturoClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, ValorFuturo.class);

            intent.putExtra("valorPresente", valorPresente);
            intent.putExtra("valorFuturo", valorFinal);

            //startActivityForResult(intent,1);
            launcher.launch(intent);
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

            textViewValorFuturo.setText(NumberFormat.getCurrencyInstance(locale).format(valorFinal));
        }

    }
}