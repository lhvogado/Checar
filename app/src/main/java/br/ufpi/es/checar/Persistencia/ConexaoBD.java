package br.ufpi.es.checar.Persistencia;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexaoBD extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "cliente.db";
    static final String ID = "_id";
    static final String TABELA = "cliente";
    private static final int VERSAO_BD = 1;
    static final String CPF = "CPF";
    static final String RG = "RG";
    static final String Nome = "Nome";


    public ConexaoBD(Context ctx){
        super(ctx, NOME_BANCO, null, VERSAO_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sql = "CREATE TABLE" + TABELA + "("
                + ID + "integer primary key autoincrement,"
                + CPF + "text,"
                + RG + "text,"
                + Nome + "text"
                +");";
        bd.execSQL(sql);
    }
    @Override

    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table cliente;");
        onCreate(bd);
    }
}