package br.ufpi.es.checar.Persistencia;

import br.ufpi.es.checar.Entidade.Pessoa;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String NOME_BD = "teste";
    static final String TABELA = "Cliente";
    private static final int VERSAO_BD = 1;
    Pessoa p = new Pessoa();
    static int CPF;
    static int RG;
    static String Nome;
    String email;
    Date data = p.getData_Nascimento();

    public ConexaoBD(Context ctx){
        super(ctx, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE cliente(_" +
                "CPF integer primary key, " +
                " RG integer not null," +
                " nome text not null," +
                " email text not null," +
                " Data text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table cliente;");
        onCreate(bd);
    }

}
