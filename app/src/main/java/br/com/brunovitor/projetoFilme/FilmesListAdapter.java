package br.com.brunovitor.projetoFilme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.brunovitor.projetoFilme.model.Filme;

/**
 * Created by Bruno Vitor Pires on 22/10/2015.
 */
public class FilmesListAdapter extends ArrayAdapter<Filme> {
    public FilmesListAdapter(Context context, List<Filme> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_filme_list, null);

            viewHolder = new ViewHolder();

            viewHolder.imgCapaFilme = (ImageView)convertView.findViewById(R.id.img_capa_item);
            viewHolder.txtTitulo = (TextView)convertView.findViewById(R.id.txtNome);
            viewHolder.txtDirecao = (TextView)convertView.findViewById(R.id.txtElenco);
            viewHolder.txtAnoLancamento = (TextView)convertView.findViewById(R.id.txtAnoLancamento);

            convertView.setTag(viewHolder);
        }else  {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Filme filme = getItem(position);

        if(filme != null) {
            Picasso.with(getContext()).load(filme.capa).into(viewHolder.imgCapaFilme);
            viewHolder.txtTitulo.setText(filme.titulo);
            viewHolder.txtDirecao.setText(filme.direcao);
            viewHolder.txtAnoLancamento.setText(filme.lancamento);
        }
        return convertView;
    }
}

    class ViewHolder {
        ImageView imgCapaFilme;
        TextView txtTitulo;
        TextView txtDirecao;
        TextView txtAnoLancamento;
    }

