package br.ufjf.dcc196.izabel.calculajuros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JurosSimplesActivity extends AppCompatActivity {

    private TextView textViewValorPresente;
    private Double valorPresente;
    private EditText editTextTaxaDeJuros;
    private EditText editTextPeriodos;
    private TextView textViewResultado;
    private Double valorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juros_simples);
        textViewValorPresente = findViewById(R.id.textViewValePresente);
        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("ValorPresente");
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

        valorFinal = valorPresente * (1 + taxaDeJuros*periodos);

        textViewResultado.setText(valorFinal.toString());

    }
}