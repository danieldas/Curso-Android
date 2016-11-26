package com.example.roymer.myapphelloworld;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;


public class MainActivity extends ActionBarActivity implements Validator.ValidationListener {

    double numero1, numero2;
    @NotEmpty(message = "Ingrese un número" )
    private EditText _n1;
    @NotEmpty(message = "Ingrese un número" )
    private EditText _n2;
    private TextView _resultado;
    private Button _btnsumar, _btnrestar, _btnmultiplicar, _btnDividir, _btnMayorMenor;

    private ImageView imageView;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _n1 = (EditText) findViewById(R.id.editTextN1);
        _n2 = (EditText) findViewById(R.id.editTextN2);
        _resultado = (TextView) findViewById(R.id.textViewResultado);
        _btnsumar = (Button) findViewById(R.id.btnSumar);
        _btnrestar = (Button) findViewById(R.id.btnRestar);
        _btnmultiplicar = (Button) findViewById(R.id.btnMultiplicar);
        _btnDividir = (Button) findViewById(R.id.btnDividir);
        _btnMayorMenor = (Button) findViewById(R.id.btnMayorMenor);


        validator = new Validator(this);
        validator.setValidationListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);


        _btnsumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                _resultado.setText("La Suma es: " + Sumar(numero1, numero2));
            }
        });


        _btnrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                _resultado.setText("La Resta es: " + Restar(numero1, numero2));
            }
        });

        _btnmultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                _resultado.setText("La multiplicacion es: " + Multiplicar(numero1, numero2));
            }
        });

        _btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                _resultado.setText("La division es: " + String.format("%.2f", Dividir(numero1, numero2)));
            }
        });

        _btnMayorMenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
                _resultado.setText(MayorMenor(numero1, numero2));
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void CargarDatos(){
        if (_n1.getText().toString().equals(".") || _n2.getText().toString().equals(".")) {
            Toast.makeText(this, "Introduzca números válidos", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            numero1 = Double.parseDouble(_n1.getText().toString());
            numero2 = Double.parseDouble(_n2.getText().toString());
        }
    }

    private double Sumar(double n1, double n2){
        return n1 + n2;
    }

    private double Restar(double n1, double n2){
        return n1 - n2;
    }

    private double Multiplicar(double n1, double n2){
        return n1 * n2;
    }

    private double Dividir(double n1, double n2){
        return n1 / n2;
    }

    private String MayorMenor(double n1, double n2){
        if(n1 > n2)
            return "El Mayor es: " + n1;
        else if(n1 < n2)
            return "El Mayor es: " + n2;
        else
            return "Son iguales: " + n1 + " " + n2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onValidationSucceeded() {
        CargarDatos();
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(_n2.getWindowToken(), 0);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors)
        {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
            else
            {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}