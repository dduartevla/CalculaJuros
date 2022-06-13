package br.ufjf.dcc196.izabel.calculajuros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class ValorFuturo extends AppCompatActivity {

    private TextView textViewValorPresente;
    private Double valorPresente = 0.00;
    private Double valorPorcentagem = 0.0;
    Double valorFuturo = 0.00;
    private EditText editTextValorFuturo;
    private Button buttonCalcular;
    private TextView textViewPorcentagem;
    private Button buttonRetornar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_futuro);
        textViewValorPresente = findViewById(R.id.editTextValorPresente);
        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("valorPresente");
        textViewValorPresente.setText(valorPresente.toString());
        editTextValorFuturo = findViewById(R.id.editTextValorPresente);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        textViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        buttonRetornar = findViewById(R.id.buttonRetornar);
    }

    public void calcularClick(View view){

        valorFuturo = Double.parseDouble(editTextValorFuturo.getText().toString());
        valorPorcentagem = ((valorFuturo - valorPresente) / valorPresente) * 100;

        Locale locale = new Locale("pt","BR");
        textViewPorcentagem.setText(NumberFormat.getCurrencyInstance(locale).format(valorPorcentagem));

    }

    public void retornaClick(View view){
        Intent resultado = new Intent();
        resultado.putExtra("valorPorcentagem", valorPorcentagem);
        resultado.putExtra("valorFuturo", valorFuturo);
        setResult(3, resultado);
        finish();
    }
}