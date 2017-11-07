package com.example.edson.appprojetocampanha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class AfinidadesActivity extends AppCompatActivity {

    private Spinner spinnerAfinidade;
    private ListView listaAfinidade;
    private Button btnAddAfinidades;


    private ArrayAdapter<String> afinidadesOpcoes;
    private ArrayAdapter<String> afinidadesEscolhidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinidades);


        final Spinner spinnerAfinidade = (Spinner) findViewById(R.id.spinnerAfinidade);
        final ListView listaAfinidade = (ListView) findViewById(R.id.listaAfinidade);
        Button btnAddAfinidades = (Button) findViewById((R.id.btnAddAfinidades));

        final DBHelper db = new DBHelper(this);
        afinidadesOpcoes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);

        afinidadesOpcoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAfinidade.setAdapter(afinidadesOpcoes);

        afinidadesOpcoes.add("Refugiados");
        afinidadesOpcoes.add("Crianças vítimas de abuso");
        afinidadesOpcoes.add("Pessoas em situação de rua");
        afinidadesOpcoes.add("Mulheres vítimas de violência");
        afinidadesOpcoes.add("Crianças desaparecidas");
        afinidadesOpcoes.add("Animais abandonados");
        afinidadesOpcoes.add("Crianças e adolescentes fora da escola");
        afinidadesOpcoes.add("Idosos");
        afinidadesOpcoes.add("Pessoas com deficiência");
        afinidadesOpcoes.add("Direitos Humanos");

        afinidadesEscolhidas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        listaAfinidade.setAdapter(afinidadesEscolhidas);

        // carrega as afinidades escolhidas do banco de dados
        ArrayList<String> arrayAfinidades = null;
        try {
            arrayAfinidades = db.retornaAfinidades();
        } catch (Exception ex) {
            Toast.makeText(AfinidadesActivity.this, "Erro no carregar a lista", Toast.LENGTH_LONG).show();
        }

        // se tiver afinidades escolhidas ele carrega na lista pra mostrar na tela
        if(arrayAfinidades != null){
            for (int i = 0; i < arrayAfinidades.size(); i++) {
                afinidadesEscolhidas.add(arrayAfinidades.get(i));
            }
        }

        btnAddAfinidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Afinidade afinidade = new Afinidade();
                String afinidadeEscolhida = spinnerAfinidade.getSelectedItem().toString();
                boolean validaAfinidade = false;

                for (int idx = 0; idx < afinidadesEscolhidas.getCount(); idx++) {
                    if (afinidadeEscolhida.equals(afinidadesEscolhidas.getItem(idx))) {
                        validaAfinidade = true;
                    }
                }

                try {
                    if (!validaAfinidade) {
                        afinidade.setId(UUID.randomUUID().toString());
                        afinidade.setAfinidade_nome(afinidadeEscolhida);
                        db.insertAfinidade(afinidade);
                        afinidadesEscolhidas.add(afinidadeEscolhida);
                    } else {
                        Toast.makeText(AfinidadesActivity.this, "Afinidade já foi escolhida", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception ex) {
                    Toast.makeText(AfinidadesActivity.this, "Algo deu errado, tente novamente", Toast.LENGTH_LONG).show();
                }

            }
        });



        registerForContextMenu(listaAfinidade);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        final DBHelper db = new DBHelper(AfinidadesActivity.this);
        MenuItem excluir = menu.add("Remover");

        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;


                //String item = listaAfinidade.getItemAtPosition(1).toString();
                String item = afinidadesEscolhidas.getItem(info.position);

                try {
                    db.deleteAfinidade(item);
                    afinidadesEscolhidas.remove(item);
                } catch (Exception ex){
                    Toast.makeText(AfinidadesActivity.this, "Algo deu errado, tente novamente", Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });
    }


}
