package br.ufpi.es.checar.Persistencia;
import br.ufpi.es.checar.Persistencia.ConexaoBD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class ControleBanco {
    private SQLiteDatabase db;

    public ControleBanco(Context context) {
        ConexaoBD banco = new ConexaoBD(context);
        db = banco.getWritableDatabase();
    }


    public void InserirDado(String cpf, String rg, String Nome) {
        ContentValues valores = new ContentValues();
        valores.put("CPF", cpf);
        valores.put("RG", rg);
        valores.put("Nome", Nome);
        db.insert("cliente", null, valores);
        db.close();

    }
}

