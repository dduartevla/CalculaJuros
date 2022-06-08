package br.ufjf.dcc196.izabel.calculajuros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class JurosCompostosActivity extends AppCompatActivity {

    private TextView textViewValorPresente;
    private Double valorPresente;
    private EditText editTextTaxaDeJuros;
    private EditText editTextPeriodos;
    private TextView textViewResultado;
    private Double valorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juros_compostos);
        textViewValorPresente = findViewById(R.id.textViewValorPresente);
        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("valorPresente");
        textViewValorPresente.setText(valorPresente.toString());
        editTextTaxaDeJuros = findViewById(R.id.editTextTaxaJuroSimples);
        editTextPeriodos = findViewById(R.id.editTextPeriodo);
        textViewResultado = findViewById(R.id.textViewResultado);
    }

    public void retornaClick (View view){

        Intent resultado = new Intent();
        resultado.putExtra("valorFinal", valorFinal);
        setResult(1, resultado);
        finish();
    }

    public void calcularClick(View view){

        Double taxaDeJuros;
        Integer periodos;
        taxaDeJuros = Double.parseDouble(editTextTaxaDeJuros.getText().toString());
        periodos = Integer.parseInt(editTextPeriodos.getText().toString());

        valorFinal = valorPresente * Math.pow(1+taxaDeJuros,periodos);

        Locale locale = new Locale("pt", "BR");
        textViewResultado.setText(NumberFormat.getCurrencyInstance(locale).format(valorFinal));

    }
}