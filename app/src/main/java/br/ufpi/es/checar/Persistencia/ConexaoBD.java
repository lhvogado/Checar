package br.ufpi.es.checar.Persistencia;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexaoBD extends SQLiteOpenHelper {
    /**
     * Variáveis que dão acesso ao banco de dados.
     */
    static final String NOME_BANCO = "checar.db";
    static final String TABELA_CLIENTES = "cliente";
    static final String TABELA_CARROS = "carro";
    static final int VERSAO_BD = 1;


    public ConexaoBD(Context ctx){
        super(ctx, NOME_BANCO, null, VERSAO_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        //String sql = "create table cliente(_id integer primary key autoincrement, CPF text not null, RG text not null, Nome text not null);";
        String carro = "create table carro(_id integer primary key autoincrement, Placa text not null, Fabricante text not null, Modelo text not null, Versao text not null,  Ano text not null, Cor text not null, Motor text not null);";
        //bd.execSQL(sql);
        bd.execSQL(carro);
    }
    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        //bd.execSQL("drop table if exists" + TABELA_CLIENTES);
        bd.execSQL("drop table if exists" + TABELA_CARROS);
        onCreate(bd);
    }
}