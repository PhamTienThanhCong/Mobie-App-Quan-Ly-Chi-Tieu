package com.example.qlct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlct.R;
import com.example.qlct.activity.MainActivity;
import com.example.qlct.adapter.AddAdapter;
import com.example.qlct.adapter.SubAdapter;
import com.example.qlct.dialog.AddDialog;
import com.example.qlct.model.Item;
import com.example.qlct.realm.RealmController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubFragment extends Fragment {

    @BindView(R.id.rcv_sub)
    RecyclerView rcvSub;

    private List<Item> subList = new ArrayList<>();
    private SubAdapter subAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RealmController realmController;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_sub, container, false);
        ButterKnife.bind(this, view);
        initData();
        initViews();
        return view;
    }

    private void initData() {
        realmController = new RealmController();

        subList = realmController.getItem(2);
    }

    private void initViews() {
        subAdapter = new SubAdapter(getContext(), subList);
        subAdapter.setOnItemClick(new SubAdapter.OnItemClick() {
            @Override
            public void onItemClick(int pos) {
                Item item = subList.get(pos);
                showEditDialog(item);
            }

            @Override
            public void onDelete(int pos) {
                Item item = subList.get(pos);
                realmController.deleteItem(item);
                subList = realmController.getItem(2);
                subAdapter.notifyDataSetChanged();
            }
        });
        rcvSub.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rcvSub.setLayoutManager(layoutManager);
        rcvSub.setAdapter(subAdapter);
    }

    private void showEditDialog(Item item) {
        AddDialog editDialog = AddDialog.newInstance(item, "Some Title", 2, true, new AddDialog.Callback() {
            @Override
            public void onResult(Item item) {
                //todo update lai data get tu realm sau khi sua
                subList = realmController.getItem(2);
                subAdapter.notifyDataSetChanged();
            }
        });
        editDialog.show(getActivity().getSupportFragmentManager(), "dialog_edit");
        editDialog.setCancelable(false);
    }

    @OnClick(R.id.btn_add)
    void add(){
        final AddDialog addDialog = AddDialog.newInstance(null, "Some Title", 2, true, new AddDialog.Callback() {
            @Override
            public void onResult(Item item) {
                //todo update lai data get tu realm sau khi them
                subList = realmController.getItem(2);
                subAdapter.notifyDataSetChanged();
            }
        });
        addDialog.show(getActivity().getSupportFragmentManager(), "dialog_sub");
        addDialog.setCancelable(false);
    }


}
