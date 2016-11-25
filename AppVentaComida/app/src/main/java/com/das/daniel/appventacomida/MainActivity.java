package com.das.daniel.appventacomida;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private TextView textoCantidad, textoNombre, textoPrecio, textoSubtotal, textoTotal;
    private ImageView imagenGeneral;
    private int indice=0;
    private double total=0, subtotal;

    int imagenes[]={R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    String nombre[]={"Charquekan","Chicharron", "Majadito", "Pollo", "Silpancho"};
    double precio[]={20, 30, 15, 17, 25};
    int cantidad[]={0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarComponentes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevaActividad= new Intent(MainActivity.this, ReporteActivity.class);
                String reporte="";
                double sub=0;
                for (int j=0; j<5; j++){
                    sub=cantidad[j]*precio[j];
                    reporte=reporte+nombre[j]+" - "+ cantidad[j]+ " - "+sub+"\n";
                }
                reporte=reporte+"Total a pagar: "+ total;
                nuevaActividad.putExtra("imprimir", reporte);
                startActivity(nuevaActividad);
            }
        });
    }

    private void inicializarComponentes(){
        textoNombre= (TextView) findViewById(R.id.txtNombre);
        textoCantidad= (TextView) findViewById(R.id.txtCantidad);
        textoPrecio= (TextView) findViewById(R.id.txtPrecio);
        textoSubtotal= (TextView) findViewById(R.id.txtSubtotal);
        textoTotal= (TextView) findViewById(R.id.txtTotal);
        imagenGeneral= (ImageView) findViewById(R.id.imgGral);

        //dar valores por defecto
        textoNombre.setText(nombre[0]);
        textoCantidad.setText(cantidad[0]+"");
        textoPrecio.setText(precio[0]+"");
        subtotal=cantidad[0]*precio[0];
        textoSubtotal.setText(subtotal+"");
        textoTotal.setText(total+"");

    }


    public void siguiente (View view){
        indice++;
        if (indice>4)
        {
            indice=0;
        }
        textoNombre.setText(nombre[indice]);
        textoPrecio.setText(precio[indice]+"");
        textoCantidad.setText(cantidad[indice]+"");
        imagenGeneral.setImageResource(imagenes[indice]);
        subtotal=cantidad[indice]*precio[indice];
        textoSubtotal.setText(subtotal+"");
    }

    public void anterior (View view){
        indice--;
        if (indice<0)
        {
            indice=4;
        }
        textoNombre.setText(nombre[indice]);
        textoPrecio.setText(precio[indice]+"");
        textoCantidad.setText(cantidad[indice]+"");
        imagenGeneral.setImageResource(imagenes[indice]);
        subtotal=cantidad[indice]*precio[indice];
        textoSubtotal.setText(subtotal+"");
    }

public  void comprar(View view){
    cantidad[indice]=cantidad[indice]+1;
    textoCantidad.setText(cantidad[indice]+"");
    subtotal=cantidad[indice]*precio[indice];
    textoSubtotal.setText(subtotal+"");
    total=total+precio[indice];
    textoTotal.setText(total+"");
}

    public void cancelar(View view){
        if (cantidad[indice]>0){
            cantidad[indice]=cantidad[indice]-1;
            textoCantidad.setText(cantidad[indice]+"");
            subtotal=cantidad[indice]*precio[indice];
            textoSubtotal.setText(subtotal+"");
            total=total-precio[indice];
            textoTotal.setText(total+"");
        }
        else{
            Toast.makeText(this,"Se debe realizar por lo menos una compra",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void favorito (View view){
        FragmentManager fragmentManager =getFragmentManager();
        fragmentDialog dialogo=new fragmentDialog();
        dialogo.show(fragmentManager, "alerta");
    }
}
