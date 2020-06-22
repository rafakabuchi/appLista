package sp.etec.applista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sp.etec.applista.dao.ProdutosDao;

public class FormProdutoActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtValor;
    Button btSalvar;
    Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);
        txtNome = findViewById(R.id.txtNome);
        txtValor = findViewById(R.id.txtValor);
        btSalvar = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarProduto();
                FormProdutoActivity.this.finish();
            }
        });

        produto = (Produto) this.getIntent().getSerializableExtra("produto");
        if (produto!=null)
        {
            exibirDadosProduto();
        }


    }

    private void exibirDadosProduto() {
        txtNome.setText(produto.getNome());
        txtValor.setText(String.valueOf( produto.getValor()));


    }

    private void SalvarProduto()
    {
        if (produto==null) {


            Produto produto = new Produto();
            produto.setNome(txtNome.getText().toString());
            produto.setValor(Double.valueOf(txtValor.getText().toString()));

            ProdutosDao dao = new ProdutosDao(this);
            dao.CadastraProduto(produto);
            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
        }
        else
        {
            produto.setNome(txtNome.getText().toString());
            produto.setValor(Double.valueOf(txtValor.getText().toString()));
            ProdutosDao dao = new ProdutosDao(this);

            dao.AtualizaProduto(produto);
            Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_LONG).show();

        }

    }

}
