package com.example.edson.appprojetocampanha;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;

import static com.example.edson.appprojetocampanha.VariavelsGlobal.usuarioLogado;

public class LoginActivity extends AppCompatActivity {

    // botão para entrar no sistema
    private Button btnEntrar;

    // manipulação do e-mail e senha quando digitado
    private EditText txtLoginEmail;
    private EditText txtLoginSenha;

    // manipulação do e-mail e das senhas quando for recuperar ela.
    private EditText txtRecuperaUsername;
    private EditText txtRecuperaPassword;
    private EditText txtRecuperaConfirmePassword;

    // parte do login do facebook
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // serve para abrir o AlertDialog para alterar de senha.
    private TextView lblEsqueciSenha;

    private static final String TAG = "Login";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtLoginSenha = (EditText) findViewById(R.id.txtLoginSenha);
        txtRecuperaUsername = (EditText) findViewById(R.id.txtRecuperaUsername);
        txtRecuperaPassword = (EditText) findViewById(R.id.txtRecuperaPassword);
        txtRecuperaConfirmePassword = (EditText) findViewById(R.id.txtRecuperaConfirmePassword);
        lblEsqueciSenha = (TextView) findViewById(R.id.lblEsqueciSenha);

        // estancia o banco
        final DBHelper db = new DBHelper(this);

        // parte do esqueci a senha
        lblEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                LayoutInflater inflater = LoginActivity.this.getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.recuperar_senha, null))
                        .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Usuario usuario = new Usuario();
                                String email = txtRecuperaUsername.getText().toString();
                                String senha = txtRecuperaPassword.getText().toString();
                                String confirmaSenha = txtRecuperaConfirmePassword.getText().toString();

                                if(!Validacao.validaEmail(email)){
                                    txtRecuperaUsername.setError("Digite um e-mail válido.");
                                    txtRecuperaUsername.requestFocus();
                                    return;
                                }
                                if(validaSenha(senha)){
                                    if(!Validacao.validaSenha(senha)) {
                                        txtRecuperaPassword.setError("Tamanho minimo da senha : 6 Digitos");
                                        txtRecuperaPassword.requestFocus();
                                        return;
                                    } else if(!confirmaSenha.equals(senha)){
                                        txtRecuperaConfirmePassword.setError("Campos Confirma senha e Senha precisam ser iguais.");
                                        txtRecuperaConfirmePassword.requestFocus();
                                        return;
                                    }
                                }

                                usuario.setEmail(email);

                                if(!senha.equals(""))
                                    usuario.setSenha(senha);


                                try {
                                    db.atualizaUsuario(usuario);
                                    SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("email", email);
                                    editor.commit();
                                    Toast.makeText(LoginActivity.this, "Alteração realizada com sucesso.", Toast.LENGTH_LONG).show();
                                    finish();
                                } catch (Exception ex){
                                    Toast.makeText(LoginActivity.this, "Algo deu errado, tente novamente.", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };



        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });



        // parte de entrar no app quando digitado o e-mail e a senha.
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Usuario usuario = null;
                            final DBHelper db = new DBHelper(LoginActivity.this);

                            try{
                                usuario = db.retornaUsuarioPorEmail(user.getEmail().toString());
                            } catch (Exception ex){
                                Toast.makeText(LoginActivity.this, "Algo deu errado, tente novamente.", Toast.LENGTH_LONG).show();
                            }

                            try{
                                if(usuario == null) {
                                usuario = new Usuario();
                                usuario.setEmail(user.getEmail());
                                usuario.setId(user.getUid());
                                usuario.setNome(user.getDisplayName());
                                db.insertUsuario(usuario);
                                Toast.makeText(LoginActivity.this, "Salvo com sucesso!!", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception ex){
                                ex.printStackTrace();
                                Toast.makeText(LoginActivity.this, "Não foi possível realizar o seu cadastro, por favor tente novamente!!", Toast.LENGTH_LONG).show();
                            }

                            SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("estaLogado", true);
                            editor.putString("email", usuario.getEmail());

                            editor.commit();

                            usuarioLogado = usuario;

                            Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainActivity);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static boolean validaSenha(String senha){
        return (!Validacao.validaCampoVazio(senha));
    }

}
