package com.example.qlct.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qlct.R;
import com.example.qlct.activity.MainActivity;
import com.example.qlct.model.Item;
import com.example.qlct.realm.RealmController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDialog extends DialogFragment {

    @BindView(R.id.spn_topic)
    Spinner spnTopic;

    private static final int GALLERY_REQUEST_CODE = 1;
//    private EditText edtTopic;
    private EditText edtName;
    private EditText edtAmount;
    private TextView txtTime;
    private ImageView imgTime;
    private ImageView imgBill;
    private Button btnSave;
    private Button btnCancel;
    private RelativeLayout loAddBill;

    private int type;
    private boolean addBill;
    private Callback callback;
    private Item item;
    private Item itemNew;

    private RealmController realmController;

    private List<String> listTopic = new ArrayList<>();
    private String topic;

    public AddDialog() {
    }

    public static AddDialog newInstance(Item item, String topic, int type, Boolean addBill, Callback callback) {
        AddDialog frag = new AddDialog();
        Bundle args = new Bundle();
        args.putString("topic", topic);
        frag.setArguments(args);
        frag.callback = callback;
        frag.type = type;
        frag.addBill = addBill;
        if (item != null){
            frag.item = item;
        }
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add, container, false);
        ButterKnife.bind(this, view);
//        edtTopic = view.findViewById(R.id.edt_topic);
        edtName = view.findViewById(R.id.edt_name);
        edtAmount = view.findViewById(R.id.edt_amount);
        txtTime = view.findViewById(R.id.txt_time);
        imgTime = view.findViewById(R.id.img_time);
        imgBill = view.findViewById(R.id.img_bill);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
        loAddBill = view.findViewById(R.id.lo_add_bill);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String topic = getArguments().getString("topic", "Enter Name");
        getDialog().setTitle(topic);

        realmController = new RealmController();

        initSpinnerTopic();

        if(item != null){
            itemNew = new Item();
            edtName.setText(item.getName());
            edtAmount.setText(item.getAmount()+"");
            txtTime.setText(item.getTime());
            if(item.getUrl() != null){
                Glide.with(getContext()).load(item.getUrl()).placeholder(R.mipmap.ic_launcher).into(imgBill);
            } else {
                imgBill.setImageResource(R.mipmap.ic_launcher);
            }
        } else {
            itemNew = new Item();
            Date date = new Date();
            String strDateFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            txtTime.setText(sdf.format(date));
            imgBill.setImageResource(R.mipmap.ic_launcher);
        }

        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        loAddBill.setVisibility(addBill ? View.VISIBLE : View.GONE);
        imgBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBillImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().isEmpty() || edtAmount.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else {
                    getItem();
                    callback.onResult(itemNew);
                    dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initSpinnerTopic() {
//        List<String> listTopic = new ArrayList<>();
        listTopic.add("Phí sinh hoạt"); listTopic.add("Học tập");
        listTopic.add("Ăn uống"); listTopic.add("Mua sắm");
        listTopic.add("Công việc"); listTopic.add("Giải trí");
        listTopic.add("Khác");

        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listTopic);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTopic.setAdapter(adapter);
        spnTopic.setSelection(0);
        spnTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                topic = listTopic.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getBillImage() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();

                    itemNew.setUrl(selectedImage.toString());
                    imgBill.setImageURI(selectedImage);
                    break;
            }
    }

    private void getDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear+=1;
                txtTime.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
            }
        }, year, month, day);
        datePickerDialog.show();
        datePickerDialog.setCancelable(false);
    }

    private void getItem() {
        itemNew.setType(type);
        itemNew.setName(edtName.getText().toString());
        itemNew.setTopic(topic);
        itemNew.setTime(txtTime.getText().toString());
        itemNew.setAmount(Long.parseLong(edtAmount.getText().toString()));
        itemNew.setMonth(getMonth(txtTime.getText().toString()));
        itemNew.setYear(getYear(txtTime.getText().toString()));
        //todo save to realm
        if(item != null){
            itemNew.setId(item.getId());
            realmController.updateItem(itemNew);
        } else {
            itemNew.setId((int) System.currentTimeMillis());
            realmController.addItem(itemNew);
        }

    }

    public static int getMonth(String time){
        int index1 = time.indexOf("/");
        int index2 = time.lastIndexOf("/");
        String month = time.substring(index1+1,index2);
        return Integer.parseInt(month);
    }

    public static int getYear(String time){
        String year = time.substring(time.length()-4);
        return Integer.parseInt(year);
    }

    public interface Callback{
        void onResult(Item item);
    }

}
