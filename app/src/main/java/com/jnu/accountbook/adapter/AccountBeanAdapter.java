package com.jnu.accountbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.accountbook.R;
import com.jnu.accountbook.bean.AccountBean;

import java.util.List;

public class AccountBeanAdapter extends RecyclerView.Adapter<AccountBeanAdapter.ViewHolder> {

    private List<AccountBean> accountBeanList;
    private Context context;


    public AccountBeanAdapter(List<AccountBean> accountBeanList, Context context) {
        this.accountBeanList = accountBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccountBean accountBean = accountBeanList.get(position);
        holder.image.setImageResource(accountBean.getImageId());
        holder.typename.setText(accountBean.getTypename());
        holder.accountType.setText(accountBean.getAccountType());
        holder.remark.setText(accountBean.getRemark());
        holder.money.setText(String.valueOf(accountBean.getMoney()));
        holder.date.setText(accountBean.getDate());
    }

    @Override
    public int getItemCount() {
        return accountBeanList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView typename;
        public TextView accountType;
        public TextView remark;
        public TextView money;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_recyclerview_image);
            typename = itemView.findViewById(R.id.item_recyclerview_typename);
            accountType = itemView.findViewById(R.id.item_recyclerview_accountType);
            remark = itemView.findViewById(R.id.item_recyclerview_remark);
            money = itemView.findViewById(R.id.item_recyclerview_money);
            date = itemView.findViewById(R.id.item_recyclerview_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
