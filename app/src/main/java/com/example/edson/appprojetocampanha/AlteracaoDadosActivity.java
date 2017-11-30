package com.example.edson.appprojetocampanha;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.edson.appprojetocampanha.VariavelsGlobal.usuarioLogado;

public class AlteracaoDadosActivity extends AppCompatActivity {

    private EditText txtNomeAltDados;
    private EditText txtEmailAltDados;
    private EditText txtSenhaAltDados;
    private EditText txtConfirmaSenhaAltDados;
    private EditText txtTelefoneAltDados;
    private EditText txtCPFAltDados;
    private Button btnSalvarAltDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_dados);

        txtNomeAltDados = (EditText) findViewById(R.id.txtNomeAltDados);
        txtEmailAltDados = (EditText) findViewById(R.id.txtEmailAltDados);
        txtCPFAltDados = (EditText) findViewById(R.id.txtCPFAltDados);
        txtTelefoneAltDados = (EditText) findViewById(R.id.txtTelefoneAltDados);
        txtSenhaAltDados = (EditText) findViewById(R.id.txtSenhaAltDados);
        txtConfirmaSenhaAltDados = (EditText) findViewById(R.id.txtConfirmaSenhaAltDados);
        btnSalvarAltDados = (Button) findViewById(R.id.btnSalvarAltDados);


        txtNomeAltDados.setText(usuarioLogado.getNome());
        txtEmailAltDados.setText(usuarioLogado.getEmail());
        txtCPFAltDados.setText(usuarioLogado.getCpf());
        txtTelefoneAltDados.setText(usuarioLogado.getTelefone());

        txtEmailAltDados.setEnabled(false);

        final String nomeAntesSalvar = usuarioLogado.getNome();
        final String emailAntesSalvar = usuarioLogado.getEmail();
        final String senhaAntesSalvar = usuarioLogado.getSenha();
        final String cpfAntesSalvar = usuarioLogado.getCpf();
        final String telefoneAntesSalvar = usuarioLogado.getTelefone();


        final DBHelper db = new DBHelper(this);

        btnSalvarAltDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = txtNomeAltDados.getText().toString();
                String email = txtEmailAltDados.getText().toString();
                String cpf = txtCPFAltDados.getText().toString();
                String telefone = txtTelefoneAltDados.getText().toString();
                String senha = txtSenhaAltDados.getText().toString();
                String confirmaSenha = txtConfirmaSenhaAltDados.getText().toString();

                // valida campos
                if(Validacao.validaCampoVazio(nome)){
                    txtNomeAltDados.setError("Campo não pode ser vazio.");
                    txtNomeAltDados.requestFocus();
                    return;
                } else if(!Validacao.validaEmail(email)){
                    txtEmailAltDados.setError("Digite um e-mail válido.");
                    txtEmailAltDados.requestFocus();
                    return;
                } else if(!Validacao.validaCPF(cpf)){
                    txtCPFAltDados.setError("Digite um CPF válido.");
                    txtCPFAltDados.requestFocus();
                    return;
                } else  if(!Validacao.validaTelefone(telefone)){
                    txtTelefoneAltDados.setError("Digite um Telefone válido. Formato: (DD) XXXXX-XXXX");
                    txtTelefoneAltDados.requestFocus();
                    return;
                }


                usuarioLogado.setNome(nome);
                usuarioLogado.setEmail(email);
                usuarioLogado.setCpf(cpf);
                usuarioLogado.setTelefone(telefone);

                // valida para ver se o campo está vazio ou não
                if(validaSenha(senha)){
                    //se estiver preenchido ele entra nos IF
                    if(!Validacao.validaSenha(senha)) {
                        txtSenhaAltDados.setError("Tamanho minimo da senha : 6 Digitos");
                        txtSenhaAltDados.requestFocus();
                        return;
                    } else if(!confirmaSenha.equals(senha)){
                        txtConfirmaSenhaAltDados.setError("Campos Confirma senha e Senha precisam ser iguais.");
                        txtConfirmaSenhaAltDados.requestFocus();
                        return;
                    }
                }

                // se o campo senha não estiver vazio ele atualiza.
                if(!senha.equals(""))
                    usuarioLogado.setSenha(senha);



                try {
                    db.atualizaUsuario(usuarioLogado);
                    SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", email);
                    editor.commit();
                    Toast.makeText(AlteracaoDadosActivity.this, "Alteração realizada com sucesso.", Toast.LENGTH_LONG).show();
                    finish();
                } catch (Exception ex){
                    usuarioLogado.setNome(nomeAntesSalvar);
                    usuarioLogado.setEmail(emailAntesSalvar);
                    usuarioLogado.setCpf(cpfAntesSalvar);
                    usuarioLogado.setTelefone(telefoneAntesSalvar);
                    usuarioLogado.setSenha(senhaAntesSalvar);
                    Toast.makeText(AlteracaoDadosActivity.this, "Algo deu errado, tente novamente.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static boolean validaSenha(String senha){
        return (!Validacao.validaCampoVazio(senha));
    }
}
