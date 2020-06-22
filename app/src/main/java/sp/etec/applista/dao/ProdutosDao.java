package sp.etec.applista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sp.etec.applista.Produto;

public class ProdutosDao extends SQLiteOpenHelper {

    public ProdutosDao( Context context) {
        super(context, "BDProdutos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cmd = "Create Table produtos( id INTEGER PRIMARY KEY, nome VARCHAR(255), valor double )";
        db.execSQL(cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String cmd = "drop table produtos";
        onCreate(db);
    }

    public void CadastraProduto(Produto produto)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",produto.getNome());
        contentValues.put("valor",produto.getValor());

        db.insert("produtos",null,contentValues);

    }

    public List<Produto> TodosProdutos()
    {
        SQLiteDatabase db= getReadableDatabase();
        ArrayList<Produto> lista = new ArrayList<>();
        String[] colunas = new String[]{"id","nome","valor"};
        Cursor cursor = db.query("produtos",colunas,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            Produto produto = new Produto();
            produto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            produto.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
            lista.add(produto);
        }

        return lista;
    }

    public void RemoveProduto(Produto produto)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("produtos", "id=?",new String[]{String.valueOf(produto.getId())});
    }

    public void AtualizaProduto (Produto produto)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",produto.getNome());
        contentValues.put("valor", produto.getValor());
        db.update("produtos", contentValues, "id=?",new String[]{String.valueOf(produto.getId())});
    }


}
