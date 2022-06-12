package com.example.qlct.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlct.dialog.AddDialog;
import com.example.qlct.R;
import com.example.qlct.adapter.PeriodicAdapter;
import com.example.qlct.model.Item;
import com.example.qlct.realm.RealmController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeriodicFragment extends Fragment {

    @BindView(R.id.rcv_period)
    RecyclerView rcvPeriodic;

    private List<Item> periodicList = new ArrayList<>();
    private PeriodicAdapter periodicAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RealmController realmController;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_periodic, container, false);
        ButterKnife.bind(this, view);
        initData();
        initViews();
        return view;
    }

    private void initData() {
        realmController = new RealmController();

        periodicList = realmController.getItem(3);
    }

    private void initViews() {
        periodicAdapter = new PeriodicAdapter(getContext(), periodicList);
        periodicAdapter.setOnItemClick(new PeriodicAdapter.OnItemClick() {
            @Override
            public void onItemClick(int pos) {
                Item item = periodicList.get(pos);
                showEditDialog(item);
            }

            @Override
            public void onDelete(int pos) {
                Item item = periodicList.get(pos);
                realmController.deleteItem(item);
                periodicList = realmController.getItem(3);
                periodicAdapter.notifyDataSetChanged();
            }
        });
        rcvPeriodic.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rcvPeriodic.setLayoutManager(layoutManager);
        rcvPeriodic.setAdapter(periodicAdapter);
    }

    @OnClick(R.id.btn_add)
    void add(){
        final AddDialog addDialog = AddDialog.newInstance(null, "Some Title", 3, false, new AddDialog.Callback() {
            @Override
            public void onResult(Item item) {
                //todo update lai data get tu realm
                periodicList = realmController.getItem(3);
                periodicAdapter.notifyDataSetChanged();
            }
        });
        addDialog.show(getActivity().getSupportFragmentManager(), "dialog_period");
        addDialog.setCancelable(false);
    }

    private void showEditDialog(Item item) {
        AddDialog editDialog = AddDialog.newInstance(item, "Some Title", 3, false, new AddDialog.Callback() {
            @Override
            public void onResult(Item item) {
                //todo update lai data get tu realm sau khi sua
                periodicList = realmController.getItem(3);
                periodicAdapter.notifyDataSetChanged();
            }
        });
        editDialog.show(getActivity().getSupportFragmentManager(), "dialog_edit");
        editDialog.setCancelable(false);
    }

}
