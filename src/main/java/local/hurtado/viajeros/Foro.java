package local.hurtado.viajeros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Foro extends Fragment {
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_foro, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // specify an adapter (see also next example)
        List<MensajeForo> mDataSet = ListaMensajeForo.get(getContext()).getmMensajeForo(getContext());
        mAdapter = new MyAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder> {


        private List<MensajeForo> mensajes;
        public MyAdapter(List<MensajeForo> mensajes) {
            this.mensajes = mensajes;
        }

        TextView mensaje_txt;


        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.listado_mensajesforo, parent, false));
                mensaje_txt = (TextView) itemView.findViewById(R.id.mensajeForo);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        id = getAdapterPosition()+1;
                        Intent intent = new Intent(getContext(), Chat.class);
                        intent.putExtra("REFERENCIA", "foro/0/"+id+"/message");
                        startActivity(intent);
                    }
                } );
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()),parent);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            mensaje_txt.setText(mensajes.get(position).getTitulo());
            //id = mensajes.get(position).getId();
        }

        public int getItemCount() {
            return ListaMensajeForo.get(getContext()).getmMensajeForo(getContext()).size();
        }
    }

}
