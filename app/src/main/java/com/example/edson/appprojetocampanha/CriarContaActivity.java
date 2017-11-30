package com.example.edson.appprojetocampanha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class CriarContaActivity extends AppCompatActivity {


    private Button btnSalvar;
    private EditText txtCadastroNome;
    private EditText txtCadastroCPF;
    private EditText txtCadastroEmail;
    private EditText txtCadastroTelefone;
    private EditText txtCadastroSenha;
    private EditText txtCadastroConfirmaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        txtCadastroNome = (EditText) findViewById(R.id.txtCadastroNome);
        txtCadastroCPF = (EditText) findViewById(R.id.txtCadastroCPF);
        txtCadastroEmail = (EditText) findViewById(R.id.txtCadastroEmail);
        txtCadastroTelefone = (EditText) findViewById(R.id.txtCadastroTelefone);
        txtCadastroSenha = (EditText) findViewById(R.id.txtCadastroSenha);
        txtCadastroConfirmaSenha = (EditText) findViewById(R.id.txtCadastroConfirmaSenha);

        final Usuario usuario = new Usuario();
        final DBHelper db = new DBHelper(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = txtCadastroNome.getText().toString();
                String email = txtCadastroEmail.getText().toString();
                String cpf = txtCadastroCPF.getText().toString();
                String telefone = txtCadastroTelefone.getText().toString();
                String senha = txtCadastroSenha.getText().toString();
                String confirmaSenha = txtCadastroConfirmaSenha.getText().toString();

                if(Validacao.validaCampoVazio(nome)){
                    txtCadastroNome.setError("Campo não pode ser vazio.");
                    txtCadastroNome.requestFocus();
                    return;
                } else if(!Validacao.validaEmail(email)){
                    txtCadastroEmail.setError("Digite um e-mail válido.");
                    txtCadastroEmail.requestFocus();
                    return;
                } else if(!Validacao.validaCPF(cpf)){
                    txtCadastroCPF.setError("Digite um CPF válido.");
                    txtCadastroCPF.requestFocus();
                    return;
                } else if(!Validacao.validaTelefone(telefone)) {
                    txtCadastroTelefone.setError("Digite um Telefone válido. ((DD) XXXXX-XXXX)");
                    txtCadastroTelefone.requestFocus();
                    return;
                } else if(!Validacao.validaSenha(senha)){
                    txtCadastroSenha.setError("Tamanho minimo da senha : 6 Digitos");
                    txtCadastroSenha.requestFocus();
                    return;
                } else if(!confirmaSenha.equals(senha)){
                    txtCadastroConfirmaSenha.setError("Campos Confirma senha e Senha precisam ser iguais.");
                    txtCadastroConfirmaSenha.requestFocus();
                    return;
                }


                usuario.setId(UUID.randomUUID().toString());
                usuario.setNome(nome);
                usuario.setCpf(cpf);
                usuario.setEmail(email);
                usuario.setTelefone(telefone);
                usuario.setSenha(senha);


                try{
                    // valida se já possui um usuario com o e-mail informado
                    Usuario usuarioValida = db.retornaUsuarioPorEmail(txtCadastroEmail.getText().toString());
                    if(usuarioValida == null){
                        db.insertUsuario(usuario);
                        Toast.makeText(CriarContaActivity.this, "Salvo com sucesso!!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(CriarContaActivity.this, "Já existe um cadastro com esse mesmo e-mail, favor informar outro", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(CriarContaActivity.this, "Não foi possível realizar o seu cadastro, por favor tente novamente!!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void validaCampos(){

    }
}
