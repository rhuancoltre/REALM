package com.teste.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.teste.admin.myapplication.models.Carro;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class NewCarroActivity extends AppCompatActivity implements RealmChangeListener<Realm> {

    @BindView(R.id.edittext_name)
    EditText mEditTextName;
    @BindView(R.id.edittext_color)
    EditText mEditTextColor;

    private Realm mRealm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_carro);
        ButterKnife.bind(this);
    }

    //INSERINDO DADOS

    @OnClick(R.id.button_save)
    public void onClickSave(View v) {

        mRealm.beginTransaction();

        Carro carro = mRealm.createObject(Carro.class);
        carro.setId(Carro.autoIncrementId());
        carro.setName(mEditTextName.getText().toString());
        carro.setColor(mEditTextColor.getText().toString());

        mRealm.commitTransaction();
        mRealm.addChangeListener(this);
    }

    @Override
    public void onChange(Realm element) {
        onBackPressed();
    }

    //FINISH INSERCAO DADOS

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.removeChangeListener(this);
        mRealm.close();
    }
}
