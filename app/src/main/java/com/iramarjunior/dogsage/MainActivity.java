package com.iramarjunior.dogsage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner spnPorte;
    private Spinner spnRaca;
    private EditText edtAgeYear;
    private EditText edtAgeMonth;
    private TextView txtAgeResult;
    private Button btnCalcular;

    public String porte;
    public String raca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnPorte = (Spinner) findViewById(R.id.spnPorte);
        spnRaca = (Spinner) findViewById(R.id.spnRaca);
        edtAgeYear = (EditText) findViewById(R.id.edtAgeYear);
        txtAgeResult = (TextView) findViewById(R.id.txtAgeResult);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);

        ArrayAdapter adpPorte = ArrayAdapter.createFromResource(this, R.array.cachorro_porte, R.layout.spinner_dropdown_item);
        adpPorte.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spnPorte.setAdapter(adpPorte);

        ArrayAdapter adpRaca = ArrayAdapter.createFromResource(this, R.array.cachorro_raca, R.layout.spinner_dropdown_item);
        adpRaca.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spnRaca.setAdapter(adpRaca);

        spnPorte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                porte = spnPorte.getSelectedItem().toString();
                Log.w("Porte ", porte);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        spnRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                raca = spnRaca.getSelectedItem().toString();
                Log.w("Raça ", raca);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtAgeYear = edtAgeYear.getText().toString();
                if (txtAgeYear.isEmpty()) {
                    txtAgeResult.setText("Digite uma idade!");
                    hideKeyboard(MainActivity.this, edtAgeYear);
                } else {

                    double porteSelected = porte(porte);
                    double racaSelected = raca(raca);
                    double age = Double.parseDouble(txtAgeYear);

                    double ageDog;
                    String txtAgeDog;

                    if (age < 3) {
                        ageDog = age * porteSelected;
                        txtAgeDog = anoMes(ageDog);
                    } else {
                        ageDog = (3 * porteSelected) + ((age - 3) * racaSelected);
                        txtAgeDog = anoMes(ageDog);
                    }

                    txtAgeResult.setText(txtAgeDog);
                    hideKeyboard(MainActivity.this, edtAgeYear);
                }
            }
        });
    }

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private double porte(String porte) {

        double porteSelected = 0;

        switch (porte) {
            case "Pequeno até 9Kg":
                porteSelected = 12.5;
                break;
            case "Médio 10 a 23Kg":
                porteSelected = 10.5;
                break;
            case "Grande maior que 24Kg":
                porteSelected = 9.0;
                break;
        }

        return porteSelected;
    }

    private double raca(String raca) {

        double racaSelected;

        switch (raca) {
            case "Lhasa Apso":
                racaSelected = 4.49;
                break;
            case "Shih Tzu":
                racaSelected = 4.78;
                break;
            case "Chihuahua":
                racaSelected = 4.87;
                break;
            case "Beagle":
                racaSelected = 5.20;
                break;
            case "Cocker Spaniel":
                racaSelected = 5.55;
                break;
            case "Pug":
                racaSelected = 5.95;
                break;
            case "Buldogue Francês":
                racaSelected = 7.65;
                break;
            case "Labrador Retriever":
                racaSelected = 5.74;
                break;
            case "Golden Retriever":
                racaSelected = 5.74;
                break;
            case "Staffordshire Bull Terrier":
                racaSelected = 5.33;
                break;
            case "Pastor Alemão":
                racaSelected = 7.84;
                break;
            case "Boxer":
                racaSelected = 8.90;
                break;
            default:
                racaSelected = porte(porte);
                break;
        }

        return racaSelected;

    }

    private String mes(Double d) {
        String mes;

        d = 12 * d;
        int result;
        result = (int) Math.floor(d);

        if (d < 2) {
            mes = " e " + result + " mês!";
        } else {
            mes = " e " + result + " meses)!";
        }
        return mes;
    }

    private String anoMes(double age) {
        int ano;
        double mes;
        String result;

        ano = (int) Math.floor(age);
        mes = age - ano;

        if (mes == 0) {
            result = "A idade do seu cachorro é: " + ano + " ano(s)!";
        } else {
            result = "A idade do seu cachorro é: " + ano + " ano(s)" + mes(mes);
        }

        return result;
    }
}
