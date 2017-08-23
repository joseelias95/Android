package com.example.android.operaciones;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    private TextView res;
    private EditText n1, n2;
    private Resources resources;
    private Spinner operaciones;
    private String op [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        res = (TextView)findViewById(R.id.lblResultado);
        n1 = (EditText) findViewById(R.id.txtNumerouno);
        n2 = (EditText) findViewById(R.id.txtNumerodos);
        operaciones = (Spinner) findViewById(R.id.cmbOperaciones);


        resources = this.getResources();
        op = resources.getStringArray(R.array.operaciones);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, op);

        operaciones.setAdapter(adapter);
    }

    public void calcular(View v){
        int opcion;
        double num1, num2, resultado= 0;
        res.setText("");
        if (validar()){

            opcion = operaciones.getSelectedItemPosition();
            num1 = Double.parseDouble(n1.getText().toString());
            num2 = Double.parseDouble(n2.getText().toString());
            switch (opcion){
                case 0:
                    resultado = num1 + num2;
                    break;
                case 1:
                    resultado = num1 - num2;
                    break;
                case 2:
                    resultado = num1 * num2;
                    break;
                case 3:
                    resultado = num1 / num2;
                    break;
            }

            res.setText(" "+String.format("%.2f",resultado));
        }


    }
    public void borrar(View v){

        operaciones.setSelection(0);
        n1.setText("");
        n2.setText("");
        res.setText("");
        n1.requestFocus();

    }

    public boolean validar(){
        int posicion = operaciones.getSelectedItemPosition();

        if (n1.getText().toString().isEmpty()){
            n1.setError(resources.getString(R.string.mensaje_error_uno));
            return false;
        }

        if (n2.getText().toString().isEmpty()){
            n2.setError(resources.getString(R.string.mensaje_error_dos));
            return false;
        }

        if ((Double.parseDouble(n2.getText().toString()) == 0) && posicion == 3){
            Toast.makeText(this, resources.getString(R.string.mensaje_error_tres), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
