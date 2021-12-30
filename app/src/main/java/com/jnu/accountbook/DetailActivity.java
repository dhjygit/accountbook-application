package com.jnu.accountbook;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jnu.accountbook.utils.DBManager;

public class DetailActivity extends Activity {
    String id;
    TextView money;
    TextView typename;
    TextView inoutcomeType;
    TextView accountType;
    TextView date;
    TextView remark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();

        money = findViewById(R.id.detail_money);
        typename = findViewById(R.id.detail_typename);
        inoutcomeType = findViewById(R.id.detail_inoutcomeType);
        accountType = findViewById(R.id.detail_accountType);
        date = findViewById(R.id.detail_date);
        remark = findViewById(R.id.detail_remark);

        money.setText(intent.getStringExtra("money"));
        typename.setText(intent.getStringExtra("typename"));
        if (Float.parseFloat(intent.getStringExtra("money")) > 0) {
            inoutcomeType.setText("收入");
        } else if (Float.parseFloat(intent.getStringExtra("money")) < 0) {
            inoutcomeType.setText("支出");
        } else {
            inoutcomeType.setText("无");
        }

        accountType.setText(intent.getStringExtra("accountType"));
        date.setText(intent.getStringExtra("date"));
        remark.setText(intent.getStringExtra("remark"));

        id = intent.getStringExtra("id");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_back:
                finish();
                break;
            case R.id.detail_modify:
                Intent intent = new Intent(DetailActivity.this, RecordActivity.class);
                Intent currentIntent = getIntent();
                String imageIdStr = currentIntent.getStringExtra("imageId");
                String typenameStr = currentIntent.getStringExtra("typename");
                String accountTypeStr = currentIntent.getStringExtra("accountType");
                String remarkStr = currentIntent.getStringExtra("remark");
                String moneyStr = currentIntent.getStringExtra("money");
                String dateStr = currentIntent.getStringExtra("date");
                String inoutcomeStr;
                if (Float.parseFloat(currentIntent.getStringExtra("money")) > 0) {
                    inoutcomeStr = "收入";
                } else if (Float.parseFloat(currentIntent.getStringExtra("money")) < 0) {
                    inoutcomeStr = "支出";
                } else {
                    inoutcomeStr = "无";
                }
                String idStr = currentIntent.getStringExtra("id");

                intent.putExtra("id", idStr);
                intent.putExtra("name", "modify");
                intent.putExtra("imageId", imageIdStr);
                intent.putExtra("typename", typenameStr);
                intent.putExtra("accountType", accountTypeStr);
                intent.putExtra("remark", remarkStr);
                intent.putExtra("money", moneyStr);
                intent.putExtra("date", dateStr);
                intent.putExtra("inoutcome", inoutcomeStr);


                startActivity(intent);
                break;
            case R.id.detail_delete:
                int i = Integer.parseInt(id);
                DBManager.deleteAccountBean(i);
                finish();
                break;
        }
    }
}