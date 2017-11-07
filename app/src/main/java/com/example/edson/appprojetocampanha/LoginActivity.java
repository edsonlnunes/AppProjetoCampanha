package com.example.edson.appprojetocampanha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.edson.appprojetocampanha.VariavelsGlobal.usuarioLogado;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText txtLoginEmail;
    private EditText txtLoginSenha;
    private Button btnFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtLoginSenha = (EditText) findViewById(R.id.txtLoginSenha);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);

        Usuario usuario = new Usuario();
        final DBHelper db = new DBHelper(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();

                String email = txtLoginEmail.getText().toString();
                String senha = txtLoginSenha.getText().toString();

                if(!Validacao.validaEmail(email)) {
                    txtLoginEmail.setError("Digite um e-mail válido.");
                    txtLoginEmail.requestFocus();
                    return;
                } else if(!Validacao.validaSenha(senha)) {
                    txtLoginSenha.setError("Tamanho minimo da senha : 6 Digitos");
                    txtLoginSenha.requestFocus();
                    return;
                }



                try{
                    usuario = db.retornaUsuarioPorEmail(email);
                } catch (Exception ex){
                    Toast.makeText(LoginActivity.this, "Algo deu errado, tente novamente.", Toast.LENGTH_LONG).show();
                }


                if(usuario != null) {
                    if (usuario.getSenha().equals(txtLoginSenha.getText().toString())) {
                        SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("estaLogado", true);
                        editor.putString("email", usuario.getEmail());

                        editor.commit();

                        usuarioLogado = usuario;


                        Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainActivity);
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou senha incorreto.", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(LoginActivity.this, "Não existe cadastro com o e-mail informado, favor informar um correto.", Toast.LENGTH_LONG).show();
                }




            }
        });


    }
}
