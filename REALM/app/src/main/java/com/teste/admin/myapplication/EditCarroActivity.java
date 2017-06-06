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


public class EditCarroActivity extends AppCompatActivity {

    public static final String INTENT_KEY_EDIT_ID = "INTENT_KEY_EDIT_ID";
    @BindView(R.id.edittext_name)
    EditText mEditTextName;
    @BindView(R.id.edittext_color)
    EditText mEditTextColor;
    private Realm mRealm = Realm.getDefaultInstance();
    private Carro mCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_carro);
        ButterKnife.bind(this);

        //RECUPERANDO DADOS E SETANDO NOS EDITs
        Long id = getIntent().getLongExtra(INTENT_KEY_EDIT_ID, 0L);
        Realm realm = Realm.getDefaultInstance();
        mCarro = realm.where(Carro.class).equalTo("id", id).findFirst();
        mEditTextName.setText(mCarro.getName());
        mEditTextColor.setText(mCarro.getColor());
    }

    //SALVANDO OS DADOS DA ATUALIZACAO
    @OnClick(R.id.button_save)
    public void onClickEdit(View v) {

        mRealm.beginTransaction();
        mCarro.setName(mEditTextName.getText().toString());
        mCarro.setColor(mEditTextColor.getText().toString());
        mRealm.commitTransaction();

        mRealm.addChangeListener(this);
    }

    //    @Override
    public void onChange(Realm element) {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.removeChangeListener(this);
        mRealm.close();
        //
    }
}
