package com.example.edson.appprojetocampanha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EventoActivity extends AppCompatActivity {

    private TextView txtEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        txtEvento = (TextView) findViewById(R.id.txtEvento);

        // QR code
            IntentIntegrator integrator = new IntentIntegrator(this);

            integrator.addExtra("SCAN_WIDTH", 200);
            integrator.addExtra("SCAN_HEIGHT", 200);
            integrator.addExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
            //Mensagem enquanto scanea
            integrator.addExtra("PROMPT_MESSAGE", "Lendo o QRCode!");
            integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() != null){
                txtEvento.setText(result.getContents());
            } else {
                Toast.makeText(getApplicationContext(), "Scan cancelado", Toast.LENGTH_LONG).show();
            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
