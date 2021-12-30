package com.jnu.accountbook;


import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.jnu.accountbook.adapter.TypeBeanAdapter;
import com.jnu.accountbook.bean.AccountBean;
import com.jnu.accountbook.bean.TypeBean;
import com.jnu.accountbook.utils.DBManager;
import com.jnu.accountbook.utils.KeyBoardUtil;
import com.jnu.accountbook.utils.RemarkDialog;
import com.jnu.accountbook.utils.SelectTimeDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordActivity extends FragmentActivity {
    KeyboardView keyboardView;
    EditText moneyEdit;
    Button inoutcomeBtn;
    Button accountBtn;
    Button dateBtn;
    Button remarkBtn;
    TextView title;
    TextView type;

    GridView gridView;
    List<TypeBean> typeList;

    int currentImageId;
    String currentTypename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record);

        inoutcomeBtn = findViewById(R.id.record_inoutcome);
        accountBtn = findViewById(R.id.record_accoutType);
        dateBtn = findViewById(R.id.record_date);
        remarkBtn = findViewById(R.id.record_remark);
        moneyEdit = findViewById(R.id.record_edit);
        title = findViewById(R.id.record_title);
        type = findViewById(R.id.record_type);

        if (getIntent().getStringExtra("name") != null && getIntent().getStringExtra("name").equals("modify")) {
            inoutcomeBtn.setText(getIntent().getStringExtra("inoutcome"));
            accountBtn.setText(getIntent().getStringExtra("accountType"));
            dateBtn.setText(getIntent().getStringExtra("date"));
            remarkBtn.setText(getIntent().getStringExtra("remark"));
            moneyEdit.setText(getIntent().getStringExtra("money").replace("-", ""));
            TextView again = findViewById(R.id.record_again);
            again.setText("");
            title.setText("修改");
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateBtn.setText(simpleDateFormat.format(new Date()));
            title.setText("添加");
        }

        initGridView();
        initKeyBoard();


    }

    private void initGridView() {
        gridView = findViewById(R.id.record_gridview);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        typeList = new ArrayList<>();
        List<TypeBean> typeBeanList = DBManager.getTypeBeanList(0);
        typeList.addAll(typeBeanList);
        currentImageId = typeList.get(0).getsImageId();
        currentTypename = typeList.get(0).getTypename();
        type.setText(typeList.get(0).getTypename());

        TypeBeanAdapter typeBeanAdapter = new TypeBeanAdapter(this, typeList);
        gridView.setAdapter(typeBeanAdapter);

        typeBeanAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeBeanAdapter.setPosition(i);
                typeBeanAdapter.notifyDataSetChanged();
                currentImageId = typeBeanList.get(i).getsImageId();
                currentTypename = typeBeanList.get(i).getTypename();
                type.setText(typeBeanList.get(i).getTypename());
            }
        });
    }

    private void initKeyBoard() {
        keyboardView = findViewById(R.id.record_keyboard);
        moneyEdit = findViewById(R.id.record_edit);
        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(keyboardView, moneyEdit);
        keyBoardUtil.setOnEnsureListener(() -> {
            if (title.getText().equals("添加"))
                insertAccountBean();
            else
                modifyAccountBean();
            finish();
        });
    }

    private void insertAccountBean() {
        String accountType = accountBtn.getText().toString();
        String remark = remarkBtn.getText().toString();
        String moneyString = moneyEdit.getText().toString();
        float money = 0;
        if (!moneyString.equals("")) {
            if (inoutcomeBtn.getText().toString().equals("支出"))
                money = Float.parseFloat("-" + moneyString);
            else
                money = Float.parseFloat(moneyString);
        }
        String date = dateBtn.getText().toString();
        DBManager.insertAccountBean(new AccountBean(currentImageId, currentTypename, accountType, remark, money, date));
    }

    private void modifyAccountBean() {
        String accountType = accountBtn.getText().toString();
        String remark = remarkBtn.getText().toString();
        String moneyString = moneyEdit.getText().toString();
        float money = 0;
        if (!moneyString.equals("")) {
            if (inoutcomeBtn.getText().toString().equals("支出"))
                money = Float.parseFloat("-" + moneyString);
            else
                money = Float.parseFloat(moneyString);
        }
        String date = dateBtn.getText().toString();
        int id = Integer.parseInt(getIntent().getStringExtra("id"));
        DBManager.modifyAccountBean(new AccountBean(id, currentImageId, currentTypename, accountType, remark, money, date));
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_back:
                finish();
                break;
            case R.id.record_again:
                insertAccountBean();
                break;
            case R.id.record_inoutcome:
                if (inoutcomeBtn.getText().toString().equals("支出"))
                    inoutcomeBtn.setText("收入");
                else
                    inoutcomeBtn.setText("支出");
                break;
            case R.id.record_accoutType:
                break;
            case R.id.record_date:
                SelectTimeDialog selectTimeDialog = new SelectTimeDialog(this);
                selectTimeDialog.setEnsureListener((year, month, day) -> {
                    dateBtn.setText(year + "-" + month + "-" + day);
                    selectTimeDialog.cancel();
                });
                selectTimeDialog.show();
                break;
            case R.id.record_remark:
                RemarkDialog remarkDialog = new RemarkDialog(this);
                remarkDialog.show();
                remarkDialog.setOnEnsureListener(new RemarkDialog.OnEnsureListener() {
                    @Override
                    public void ensure() {
                        String editText = remarkDialog.getEditText();
                        remarkDialog.cancel();
                        remarkBtn.setText(editText);
                    }
                });
                break;
        }
    }
}