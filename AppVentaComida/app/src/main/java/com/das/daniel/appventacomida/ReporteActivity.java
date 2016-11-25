package com.das.daniel.appventacomida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReporteActivity extends AppCompatActivity {
    private TextView textoImpresion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        textoImpresion= (TextView)findViewById(R.id.txtImpresion);
        Bundle mochila=getIntent().getExtras();
        String qImpresion=mochila.getString("imprimir");
        textoImpresion.setText(""+qImpresion);
    }
}
