package com.example.qlct.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.qlct.R;
import com.example.qlct.model.Item;
import com.example.qlct.realm.RealmController;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OverViewFragment extends Fragment {

    @BindView(R.id.lo_view)
    LinearLayout loView;
    @BindView(R.id.lo_chart)
    LinearLayout loChart;
    @BindView(R.id.txt_total_revenue)
    TextView txtTotalRevenue;
    @BindView(R.id.txt_total_expenditure)
    TextView txtTotalExpenditure;
    @BindView(R.id.txt_surplus)
    TextView txtSurplus;
    @BindView(R.id.img_text)
    ImageView imgText;
    @BindView(R.id.rdg_time)
    RadioGroup rdgTime;
    @BindView(R.id.rb_month)
    RadioButton rbMonth;
    @BindView(R.id.rb_year)
    RadioButton rbYear;
    @BindView(R.id.chart)
    BarChart chart;
    @BindView(R.id.spn_month)
    Spinner spnMonth;
    @BindView(R.id.spn_year)
    Spinner spnYear;

    @BindView(R.id.mess)
    TextView txtMess;

    private int monthNow;
    private int yearNow;

    private boolean isText = true;

    private float barWidth = 0.3f;
    private float barSpace = 0f;
    private float groupSpace = 0.4f;

    private RealmController realmController;
    private List<Item> addList = new ArrayList<>();
    private List<Item> subList = new ArrayList<>();
    private List<Item> periodicList = new ArrayList<>();

    private List<String> listYear = new ArrayList<>();
    private List<String> listMonth = new ArrayList<>();

    private long allAdd;
    private long allSub;
    private long soDu;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);
        initView();
        getMonthNow();
        initSpinnerMonth();
        initSpinnerYear();
        getYearNow();
        getDataMonth(spnMonth.getSelectedItemPosition()+1, Integer.parseInt(listYear.get(yearNow)));
        initData();
        return view;
    }

    public void setData(String mess){
        txtMess.setText(mess);
    }

    private void initSpinnerMonth() {
        listMonth = new ArrayList<>();
        listMonth.add("Tháng 1"); listMonth.add("Tháng 2");
        listMonth.add("Tháng 3"); listMonth.add("Tháng 4");
        listMonth.add("Tháng 5"); listMonth.add("Tháng 6");
        listMonth.add("Tháng 7"); listMonth.add("Tháng 8");
        listMonth.add("Tháng 9"); listMonth.add("Tháng 10");
        listMonth.add("Tháng 11"); listMonth.add("Tháng 12");


        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listMonth);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnMonth.setAdapter(adapter);
        spnMonth.setSelection(monthNow-1);
        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getDataMonth(i, Integer.parseInt(listYear.get(yearNow)));
                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initSpinnerYear() {
        listYear = new ArrayList<>();
        listYear.add("2018"); listYear.add("2019");
        listYear.add("2020"); listYear.add("2021");
        listYear.add("2022"); listYear.add("2023");
        listYear.add("2024"); listYear.add("2025");

        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listYear);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnYear.setAdapter(adapter);
        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getDataYear(i);
                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initData() {
        allAdd = 0;
        allSub = 0;
        soDu = 0;
        if(addList.size() != 0){
            for(Item i : addList){
                allAdd += i.getAmount();
            }
        }
        if(subList.size() != 0){
            for(Item i : subList){
                allSub += i.getAmount();
            }
        }
        if(periodicList.size() != 0){
            for(Item i : periodicList){
                if(i.isChecked()){
                    allSub += i.getAmount();
                }
            }
        }

        soDu = allAdd - allSub;

        txtTotalRevenue.setText(allAdd+"");
        txtTotalExpenditure.setText(allSub+"");
        txtSurplus.setText(soDu+"");
        loChart.setVisibility(View.GONE);
    }

    private void initView() {
        rdgTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_month){
                    spnYear.setVisibility(View.GONE);
                    if(isText){
                        spnMonth.setVisibility(View.VISIBLE);
                        spnMonth.setSelection(monthNow-1);
                        getDataMonth(spnMonth.getSelectedItemPosition(), Integer.parseInt(listYear.get(yearNow)));
                        initData();
                    } else {
                        spnMonth.setVisibility(View.GONE);
                    }
                } else {
                    spnMonth.setVisibility(View.GONE);
                    if(isText){
                        spnYear.setVisibility(View.VISIBLE);
                        spnYear.setSelection(yearNow);
                        getDataYear(spnYear.getSelectedItemPosition());
                        initData();
                    }
                }
            }
        });
    }


    @OnClick(R.id.img_text)
    void showText(){
        loChart.setVisibility(View.GONE);
        loView.setVisibility(View.VISIBLE);
        imgText.setVisibility(View.GONE);
        isText = true;
        if(rdgTime.getCheckedRadioButtonId() == R.id.rb_month){
            spnYear.setVisibility(View.GONE);
            spnMonth.setVisibility(View.VISIBLE);
            spnMonth.setSelection(monthNow-1);
            getDataMonth(spnMonth.getSelectedItemPosition(), Integer.parseInt(listYear.get(yearNow)));
            initData();
        } else {
            spnMonth.setVisibility(View.GONE);
            spnYear.setVisibility(View.VISIBLE);
            spnYear.setSelection(yearNow);
            getDataYear(spnYear.getSelectedItemPosition());
            initData();
        }
    }

    private void getDataMonth(int pos, int year){
        int month = pos + 1;
        realmController = new RealmController();
        addList = realmController.getItemYearMonth(1, year, month);
        subList = realmController.getItemYearMonth(2, year, month);
        periodicList = realmController.getItemYearMonth(3, year, month);
    }

    private void getDataYear(int pos){
        int year = Integer.parseInt(listYear.get(pos));
        realmController = new RealmController();
        addList = realmController.getItemYear(1, year);
        subList = realmController.getItemYear(2, year);
        periodicList = realmController.getItemYear(3, year);
    }

    private void getMonthNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(c);

        monthNow = Integer.parseInt(date.substring(3,5));
    }

    private void getYearNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(c);

        String year = date.substring(6);
        for (int i=0; i<listYear.size(); i++){
            if(year.equals(listYear.get(i))){
                yearNow = i;
            }
        }
    }
}
