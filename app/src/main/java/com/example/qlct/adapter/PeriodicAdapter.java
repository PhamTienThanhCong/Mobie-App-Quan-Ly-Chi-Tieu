package com.example.qlct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.qlct.model.Item;
import com.example.qlct.R;
import com.example.qlct.realm.RealmController;

import java.util.List;

public class PeriodicAdapter extends RecyclerView.Adapter<PeriodicAdapter.ViewHolder> {

    private List<Item> periodicList;
    private Context context;

    private RealmController realmController;

    private PeriodicAdapter.OnItemClick onItemClick;

    public void setOnItemClick(PeriodicAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public PeriodicAdapter(Context context, List<Item> periodicList) {
        this.periodicList = periodicList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_periodic, viewGroup, false);
        realmController = new RealmController();
        return new ViewHolder(itemView, 0);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Item item = periodicList.get(i);
        viewHolder.txtTopic.setText(item.getTopic());
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtAmount.setText(item.getAmount()+"");
        viewHolder.txtTime.setText(item.getTime());
        viewHolder.cbPeriodic.setChecked(item.isChecked());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(i);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onDelete(i);
            }
        });

        viewHolder.cbPeriodic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Item itemNew = new Item();
                itemNew.setType(item.getType());
                itemNew.setName(item.getName());
                itemNew.setTopic(item.getTopic());
                itemNew.setTime(item.getTime());
                itemNew.setAmount(item.getAmount());
                itemNew.setId(item.getId());
                itemNew.setUrl(item.getUrl());
                itemNew.setMonth(item.getMonth());
                itemNew.setYear(item.getYear());
                itemNew.setChecked(isChecked);
                realmController.updateItem(itemNew);
            }
        });

    }

    @Override
    public int getItemCount() {
        return periodicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTopic;
        TextView txtName;
        TextView txtAmount;
        TextView txtTime;
        CheckBox cbPeriodic;
        Button btnDelete;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txt_periodic_topic);
            txtName = itemView.findViewById(R.id.txt_periodic_name);
            txtAmount = itemView.findViewById(R.id.txt_periodic_amount);
            txtTime = itemView.findViewById(R.id.txt_periodic_time);
            cbPeriodic = itemView.findViewById(R.id.rb_periodic);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public interface OnItemClick{
        void onItemClick(int pos);
        void onDelete(int pos);
    }

}
