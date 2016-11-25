package com.das.daniel.login;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class MainActivity extends ActionBarActivity implements Validator.ValidationListener {
    @NotEmpty(message = "Ingrese una cuenta")
    EditText editTextUsuario;

    @NotEmpty(message = "Ingrese un password")
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    EditText editTextPassword;

    Button buttonIngresar;
    //////
    Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = (EditText) findViewById(R.id.txtUsuario);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);
        buttonIngresar = (Button) findViewById(R.id.btnIngresar);

        //////
        validator = new Validator(this);
        validator.setValidationListener(this);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
                //Toast.makeText(MainActivity.this, "Helloooo", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        Intent intentPrincipal = new Intent(MainActivity.this, Main2Activity.class);
        Intent intentSecundario = new Intent(MainActivity.this, Main3Activity.class);

        if(editTextUsuario.getText().toString().equals("admin")){
            Toast.makeText(MainActivity.this, "Ingresar a Admin", Toast.LENGTH_SHORT).show();
            startActivity(intentPrincipal);
        }

        else{
            Toast.makeText(MainActivity.this, "Ingresar a Usuario", Toast.LENGTH_SHORT).show();
            startActivity(intentSecundario);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError error : errors){
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText){
                ((EditText) view).setError(message);
            }
            else{
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
