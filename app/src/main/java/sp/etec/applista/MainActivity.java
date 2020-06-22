package sp.etec.applista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sp.etec.applista.dao.ProdutosDao;

public class MainActivity extends AppCompatActivity {

    ListView lstProdutos;
    List<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstProdutos = findViewById(R.id.lstProdutos);
        atualizaProdutos();
        lstProdutos.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_produtos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        if (item.getItemId()==R.id.menu_item_excluir)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Produto produto = listaProdutos.get(info.position);
            ProdutosDao dao = new ProdutosDao(this);
            dao.RemoveProduto(produto);
            atualizaProdutos();
        }
        else if (item.getItemId()==R.id.menu_item_atualizar)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Produto produto = listaProdutos.get(info.position);
            Intent intent = new Intent(this, FormProdutoActivity.class);
            intent.putExtra ("produto", produto);
            startActivity(intent);
        }


        return true;
    }

    private void atualizaProdutos() {

        ProdutosDao dao = new ProdutosDao(this);
        listaProdutos = dao.TodosProdutos();
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(
                this,
                android.R.layout.simple_list_item_1,
                listaProdutos
        );
        lstProdutos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        atualizaProdutos();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_item_novo)
        {
            Intent intent =
                    new Intent(this,FormProdutoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
