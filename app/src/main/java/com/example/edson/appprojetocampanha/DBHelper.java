package com.example.edson.appprojetocampanha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Edson on 06/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BD = "bd_solidario1.db";




    public DBHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BANCO);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql;

        sql = "CREATE TABLE USUARIO(ID TEXT PRIMARY KEY, NOME TEXT, EMAIL TEXT, TELEFONE TEXT, CPF TEXT, SENHA TEXT);";

        sql +="CREATE TABLE AFINIDADE(ID TEXT PRIMARY KEY, AFINIDADE_NOME TEXT, USUARIOID TEXT);";


        String[] comando = sql.split(";");
        for (String sqlComando : comando) {
            try{
                db.execSQL(sqlComando);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();

            values.put("ID", usuario.getId());
            values.put("NOME", usuario.getNome());
            values.put("EMAIL", usuario.getEmail());
            values.put("TELEFONE", usuario.getTelefone());
            values.put("CPF", usuario.getCpf());
            values.put("SENHA", usuario.getSenha());

            db.beginTransaction();

            long ok = db.insert("USUARIO", null, values);

            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex){
            ex.printStackTrace();
            db.endTransaction();
        }

        db.close();
    }

    public void deleteUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("USUARIO", "ID = ?", new String[] { usuario.getId() });

        db.close();
    }

    public Usuario retornaUsuarioPorEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM USUARIO WHERE EMAIL = '" + email + "';";

        Cursor cursor = db.rawQuery(query, null);

        Usuario usuario = null;
        if(cursor != null ) {
            cursor.moveToFirst();
            if(cursor.getCount() > 0){

                usuario = new Usuario();
                usuario.setId(cursor.getString(cursor.getColumnIndex("ID")));
                usuario.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                usuario.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
                usuario.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
                usuario.setSenha(cursor.getString(cursor.getColumnIndex("SENHA")));
            }
        }

        return usuario;
    }

    public void atualizaUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE USUARIO SET NOME = '" + usuario.getNome() + "', EMAIL = '" + usuario.getEmail() + "', TELEFONE = '" + usuario.getTelefone() +
                "', CPF = '" + usuario.getCpf() + "' WHERE ID = '" + usuario.getId() + "';";

        ContentValues values = new ContentValues();


        values.put("NOME", usuario.getNome());
        values.put("EMAIL", usuario.getEmail());
        values.put("TELEFONE", usuario.getTelefone());
        values.put("CPF", usuario.getCpf());
        values.put("SENHA", usuario.getSenha());

        db.update("USUARIO", values, "ID = '" + usuario.getId() + "'", null);
    }

    public void insertAfinidade(Afinidade afinidade){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("ID", afinidade.getId());
        values.put("AFINIDADE_NOME", afinidade.getAfinidade_nome());
        values.put("USUARIOID", afinidade.getUsuarioID());

        db.insert("AFINIDADE", null, values);

        db.close();
    }


    public ArrayList<String> retornaAfinidades(String usuarioID){
        ArrayList<String> afinidades = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();



        String query = "SELECT * FROM AFINIDADE WHERE USUARIOID = '" + usuarioID + "';";

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            afinidades.add(cursor.getString(cursor.getColumnIndex("AFINIDADE_NOME")));

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return afinidades;
    }


    public void deleteAfinidade(String afinidade, String usuarioID){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("AFINIDADE", "AFINIDADE_NOME = ? AND USUARIOID = ?", new String[] {afinidade, usuarioID});

        db.close();
    }
}
