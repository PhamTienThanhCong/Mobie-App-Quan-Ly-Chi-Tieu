package com.example.qlct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlct.R;
import com.example.qlct.adapter.AddAdapter;
import com.example.qlct.dialog.AddDialog;
import com.example.qlct.model.Item;
import com.example.qlct.realm.RealmController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFragment extends Fragment {

    @BindView(R.id.rcv_add)
    RecyclerView rcvAdd;

    private List<Item> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RealmController realmController;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        initData();
        initViews();
        return view;
    }

    private void initData() {
        realmController = new RealmController();

        addList = realmController.getItem(1);
    }

    private void initViews() {
        addAdapter = new AddAdapter(getContext(), addList);
        addAdapter.setOnItemClick(new AddAdapter.OnItemClick() {
            @Override
            public void onItemClick(int pos) {
                Item item = addList.get(pos);
                showEditDialog(item);
            }

            @Override
            public void onDelete(int pos) {
                Item item = addList.get(pos);
                realmController.deleteItem(item);
                addList = realmController.getItem(1);
                addAdapter.notifyDataSetChanged();
            }
        });
        rcvAdd.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rcvAdd.setLayoutManager(layoutManager);
        rcvAdd.setAdapter(addAdapter);
    }

    private void showEditDialog(Item item) {
        AddDialog editDialog = AddDialog.newInstance(item, "Some Title", 1, true, new AddDialog.Callback() {
            @Override
            public void onResult(Item item) {
                //todo update lai data get tu realm sau khi sua
                addList = realmController.getItem(1);
                addAdapter.notifyDataSetChanged();
            }
        });
        editDialog.show(getActivity().getSupportFragmentManager(), "dialog_edit");
        editDialog.setCancelable(false);
    }

    @OnClick(R.id.btn_add)
    void add() {
        final AddDialog addDialog = AddDialog.newInstance(null, "Some Title", 1, true, new AddDialog.Callback() {
            @Override
            public void onResult(Item item) {
                //todo update lai data get tu realm sau khi them moi
                addList = realmController.getItem(1);
                addAdapter.notifyDataSetChanged();
            }
        });
        addDialog.show(getActivity().getSupportFragmentManager(), "dialog_add");
        addDialog.setCancelable(false);
    }
}
