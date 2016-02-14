package br.ufpi.es.checar.Persistencia;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexaoBD extends SQLiteOpenHelper {

    static final String NOME_BANCO = "checar.db";
    static final String TABELA = "cliente";
    static final int VERSAO_BD = 1;


    public ConexaoBD(Context ctx){
        super(ctx, NOME_BANCO, null, VERSAO_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sql = "create table cliente(_id integer primary key autoincrement, CPF text not null, RG text not null, Nome text not null);";
        bd.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table if exists" + TABELA);
        onCreate(bd);
    }
}