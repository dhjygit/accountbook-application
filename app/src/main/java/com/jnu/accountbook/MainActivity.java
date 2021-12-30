package com.jnu.accountbook;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.accountbook.adapter.AccountBeanAdapter;
import com.jnu.accountbook.bean.AccountBean;
import com.jnu.accountbook.utils.DBManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private AccountBeanAdapter accountBeanAdapter;
    private List<AccountBean> accountBeanList;

    private TextView incomeMoney;
    private TextView outcomeMoney;
    private TextView allMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        incomeMoney = findViewById(R.id.main_income);
        outcomeMoney = findViewById(R.id.main_outcome);
        allMoney = findViewById(R.id.main_all);


        drawerLayout = findViewById(R.id.drawer_layout);
        findViewById(R.id.open_drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

//        //关闭事件
//        findViewById(R.id.close_drawer).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.closeDrawer(Gravity.LEFT);
//            }
//        });

//        DBManager.insertAccountBean(new AccountBean(R.mipmap.ic_fushi_fs, "餐饮", "支付宝", "你好你地㮯索菱股份地", 20.0F, "2021-12-21"));
//        DBManager.insertAccountBean(new AccountBean(R.mipmap.ic_fushi_fs, "餐饮", "支付宝", "你好你地㮯索菱股份地", 20.0F, "2021-12-21"));
//        DBManager.insertAccountBean(new AccountBean(R.mipmap.ic_fushi_fs, "餐饮", "支付宝", "你好你地㮯索菱股份地", 20.0F, "2021-12-21"));
//        DBManager.insertAccountBean(new AccountBean(R.mipmap.ic_fushi_fs, "餐饮", "支付宝", "你好你地㮯索菱股份地", 20.0F, "2021-12-21"));
//        DBManager.insertAccountBean(new AccountBean(R.mipmap.ic_fushi_fs, "餐饮", "支付宝", "你好你地㮯索菱股份地", 20.0F, "2021-12-21"));
//        DBManager.insertAccountBean(new AccountBean(R.mipmap.ic_fushi_fs, "餐饮", "支付宝", "你好你地㮯索菱股份地", 20.0F, "2021-12-21"));
        initRecycleView();


    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.main_recycleview);

        accountBeanList = new ArrayList<>();
        List<AccountBean> accountBean = DBManager.getAccountBean();
        accountBeanList.addAll(accountBean);

        accountBeanAdapter = new AccountBeanAdapter(accountBeanList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(accountBeanAdapter);

        accountBeanAdapter.setOnRecyclerViewItemClickListener((position) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id", String.valueOf(accountBeanList.get(position).getId()));
            intent.putExtra("money", String.valueOf(accountBeanList.get(position).getMoney()));
            intent.putExtra("typename", accountBeanList.get(position).getTypename());
            intent.putExtra("accountType", accountBeanList.get(position).getAccountType());
            intent.putExtra("date", accountBeanList.get(position).getDate());
            intent.putExtra("remark", accountBeanList.get(position).getRemark());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<AccountBean> list = DBManager.getAccountBean();
        accountBeanList.clear();
        accountBeanList.addAll(list);
        accountBeanAdapter.notifyDataSetChanged();
        calcMoney();
    }

    private void calcMoney() {

        float incomemoney = 0;
        float outcomemoney = 0;
        for (AccountBean accountBean : accountBeanList) {
            if (accountBean.getMoney() >= 0) {
                incomemoney += accountBean.getMoney();
            } else {
                outcomemoney += Math.abs(accountBean.getMoney());
            }
        }
        float allmoney = incomemoney - outcomemoney;
        incomeMoney.setText(String.valueOf(incomemoney));
        outcomeMoney.setText(String.valueOf(outcomemoney));
        if (allmoney >= 0)
            allMoney.setTextColor(Color.rgb(54, 125, 88));
        else
            allMoney.setTextColor(Color.rgb(151, 62, 78));
        allMoney.setText(String.valueOf(allmoney));

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_add:
                Intent intent = new Intent(this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.open_drawer:
                break;
        }
    }
}