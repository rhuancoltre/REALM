package com.teste.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teste.admin.myapplication.adapters.ListCarroAdapter;
import com.teste.admin.myapplication.models.Carro;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRealm = Realm.getDefaultInstance();
        RealmQuery<Carro> carros = mRealm.where(Carro.class);
        mRecyclerView.setAdapter(new ListCarroAdapter(this, carros));
    }

    @OnClick(R.id.button_new_carro)
    public void onClickOpenNewCarro(View v) {
        startActivity(new Intent(this, NewCarroActivity.class));
    }

    //ATUALIZANDO A LISTA (POS INSERCAO, EXCLUSAO e ALTERACAO)
    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
