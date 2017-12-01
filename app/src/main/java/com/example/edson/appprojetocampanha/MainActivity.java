package com.example.edson.appprojetocampanha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutionException;

import static com.example.edson.appprojetocampanha.VariavelsGlobal.usuarioLogado;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private TextView txtNomeHeader;
    private TextView txtEmailHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DBHelper db = new DBHelper(this);



        //valida se o usuario saiu do app ou somente fechou
        SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
        boolean jaLogou = prefs.getBoolean("estaLogado", false);




        //se usuario somente fechou ele não entra no IF se o usuário saiu do app ele entra no if
        if(!jaLogou) {
            Intent telaInicioView = new Intent(this, TelaInicialActivity.class);
            startActivity(telaInicioView);
        } else {
            usuarioLogado = db.retornaUsuarioPorEmail(prefs.getString("email", ""));
        }







        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        txtEmailHeader = (TextView) findViewById(R.id.txtEmailHeader);
        txtNomeHeader = (TextView) findViewById(R.id.txtNomeHeader);

        if(usuarioLogado != null) {
            txtEmailHeader.setText(usuarioLogado.getEmail());
            txtNomeHeader.setText(usuarioLogado.getNome());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // faz a implementação do usuário sair
        if (id == R.id.sair) {
            SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("estaLogado", false);
            editor.putString("email", null);
            editor.commit();

            Intent  it = new Intent(getApplicationContext(), MainActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.putExtra("SAIR", true);
            startActivity(it);


            if(getIntent().getBooleanExtra("SAIR", false)){
                finish();
            }

            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();
        }else if(id == R.id.qrcode){

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.altDados) {
            Intent alteraDados = new Intent(this, AlteracaoDadosActivity.class);
            startActivity(alteraDados);

        } else if (id == R.id.confAfinidades) {
            Intent afinidades = new Intent(this, AfinidadesActivity.class);
            startActivity(afinidades);
        } else if (id == R.id.confDoacoes) {
            Intent doacoes = new Intent(this, DoacoesActivity.class);
            startActivity(doacoes);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
