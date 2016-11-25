package com.das.daniel.ficherostxt;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //Variables
    EditText editText;
    TextView textView;
    Button buttonEscribir;
    Button buttonLeer;
    Button buttonLeerPrograma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarComponentes();

        buttonEscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    OutputStreamWriter fout =
                            new OutputStreamWriter(
                                    openFileOutput("mitexto.txt", Context.MODE_PRIVATE));
                    fout.write(editText.getText().toString());
                    fout.close();
                    Toast.makeText(MainActivity.this, "Archivo creado...." +
                            editText.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Log.e("Error: ", "Ocurrio un error");
                }

            }
        });

        buttonLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    BufferedReader br =
                            new BufferedReader(
                                    new InputStreamReader(openFileInput("mitexto.txt")));

                    String respuesta = br.readLine();
                    textView.setText(respuesta);
                    br.close();
                }
                catch (Exception ex){
                    Log.e("Error: ", "Ocurrio un error");
                }
            }
        });

        buttonLeerPrograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    InputStream is = getResources().openRawResource(R.raw.leerprograma);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String respuesta = br.readLine();
                    textView.setText(respuesta);
                    br.close();
                }
                catch (Exception ex){
                    Log.e("Error: ", "Ocurrio un error...");
                }
            }
        });
    }

    private void InicializarComponentes()
    {
        textView = (TextView) findViewById(R.id.txtContenido);
        editText = (EditText) findViewById(R.id.editTextTexto);
        buttonEscribir = (Button) findViewById(R.id.btnEscribir);
        buttonLeer = (Button) findViewById(R.id.btnLeer);
        buttonLeerPrograma = (Button) findViewById(R.id.btnPrograma);
    }
}
