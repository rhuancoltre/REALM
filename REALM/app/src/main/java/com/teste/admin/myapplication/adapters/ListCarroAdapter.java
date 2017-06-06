package com.teste.admin.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.teste.admin.myapplication.EditCarroActivity;
import com.teste.admin.myapplication.R;
import com.teste.admin.myapplication.models.Carro;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Admin on 06/06/2017.
 */

public class ListCarroAdapter extends RecyclerView.Adapter<ListCarroAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private RealmResults<Carro> mCarros;

    public ListCarroAdapter(Context context, RealmQuery<Carro> guitars) {
        mCarros = guitars.findAll().sort("id");
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.activity_edit_carro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Carro guitar = mCarros.get(position);
        holder.mTextViewName.setText(guitar.getName());
        holder.mTextViewColor.setText(guitar.getColor());
        //DELETANDO OS DADOS
        holder.mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        guitar.deleteFromRealm();
                    }
                });
                Realm.getDefaultInstance().addChangeListener(new RealmChangeListener<Realm>() {
                    @Override
                    public void onChange(Realm element) {
                        notifyDataSetChanged();
                    }
                });
            }
            //FINISH DELETE DADOS
        });

        holder.mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent intent = new Intent(c, EditCarroActivity.class);
                intent.putExtra(EditCarroActivity.INTENT_KEY_EDIT_ID, guitar.getId());
                c.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mCarros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_name)
        TextView mTextViewName;
        @BindView(R.id.textview_color)
        TextView mTextViewColor;
        @BindView(R.id.button_remove)
        Button mButtonRemove;
        @BindView(R.id.button_edit)
        Button mButtonEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
