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
    @BindView(R.id.img_chart)
    ImageView imgChart;
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
                initChartMonth();
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
                initChartYear();
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

    private void initChartMonth() {
        chart.clear();
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        getListDataMonth();

        int groupCount = 12;

        ArrayList xVals = new ArrayList();
        for(String x : listMonth){
            String a = x.replace("Tháng ", "");
            xVals.add(a);
        }

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) allAddMonth.get(0)));
        yVals2.add(new BarEntry(1, (float) allSubMonth.get(0)));
        yVals1.add(new BarEntry(2, (float) allAddMonth.get(1)));
        yVals2.add(new BarEntry(2, (float) allSubMonth.get(1)));
        yVals1.add(new BarEntry(3, (float) allAddMonth.get(2)));
        yVals2.add(new BarEntry(3, (float) allSubMonth.get(2)));
        yVals1.add(new BarEntry(4, (float) allAddMonth.get(3)));
        yVals2.add(new BarEntry(4, (float) allSubMonth.get(3)));
        yVals1.add(new BarEntry(5, (float) allAddMonth.get(4)));
        yVals2.add(new BarEntry(5, (float) allSubMonth.get(4)));
        yVals1.add(new BarEntry(6, (float) allAddMonth.get(5)));
        yVals2.add(new BarEntry(6, (float) allSubMonth.get(5)));
        yVals1.add(new BarEntry(7, (float) allAddMonth.get(6)));
        yVals2.add(new BarEntry(7, (float) allSubMonth.get(6)));
        yVals1.add(new BarEntry(8, (float) allAddMonth.get(7)));
        yVals2.add(new BarEntry(8, (float) allSubMonth.get(7)));
        yVals1.add(new BarEntry(9, (float) allAddMonth.get(8)));
        yVals2.add(new BarEntry(9, (float) allSubMonth.get(8)));
        yVals1.add(new BarEntry(10, (float) allAddMonth.get(9)));
        yVals2.add(new BarEntry(10, (float) allSubMonth.get(9)));
        yVals1.add(new BarEntry(11, (float) allAddMonth.get(10)));
        yVals2.add(new BarEntry(11, (float) allSubMonth.get(10)));
        yVals1.add(new BarEntry(12, (float) allAddMonth.get(11)));
        yVals2.add(new BarEntry(12, (float) allSubMonth.get(11)));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "Thu");
        set1.setColor(Color.RED);
        set2 = new BarDataSet(yVals2, "Chi");
        set2.setColor(Color.BLUE);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(5f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(12);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        //Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
    }

    private void initChartYear() {
        chart.clear();
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        getListDataYear();

        int groupCount = 8;

        ArrayList xVals = (ArrayList) listYear;

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) allAddYear.get(0)));
        yVals2.add(new BarEntry(1, (float) allSubYear.get(0)));
        yVals1.add(new BarEntry(2, (float) allAddYear.get(1)));
        yVals2.add(new BarEntry(2, (float) allSubYear.get(1)));
        yVals1.add(new BarEntry(3, (float) allAddYear.get(2)));
        yVals2.add(new BarEntry(3, (float) allSubYear.get(2)));
        yVals1.add(new BarEntry(4, (float) allAddYear.get(3)));
        yVals2.add(new BarEntry(4, (float) allSubYear.get(3)));
        yVals1.add(new BarEntry(5, (float) allAddYear.get(4)));
        yVals2.add(new BarEntry(5, (float) allSubYear.get(4)));
        yVals1.add(new BarEntry(6, (float) allAddYear.get(5)));
        yVals2.add(new BarEntry(6, (float) allSubYear.get(5)));
        yVals1.add(new BarEntry(7, (float) allAddYear.get(6)));
        yVals2.add(new BarEntry(7, (float) allSubYear.get(6)));
        yVals1.add(new BarEntry(8, (float) allAddYear.get(7)));
        yVals2.add(new BarEntry(8, (float) allSubYear.get(7)));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "Thu");
        set1.setColor(Color.RED);
        set2 = new BarDataSet(yVals2, "Chi");
        set2.setColor(Color.BLUE);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(5f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(8);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        //Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
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
                        initChartMonth();
                    }
                } else {
                    spnMonth.setVisibility(View.GONE);
                    if(isText){
                        spnYear.setVisibility(View.VISIBLE);
                        spnYear.setSelection(yearNow);
                        getDataYear(spnYear.getSelectedItemPosition());
                        initData();
                    } else {
                        spnYear.setVisibility(View.GONE);
                        initChartYear();
                    }
                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @OnClick(R.id.img_chart)
    void showChart(){
        loChart.setVisibility(View.VISIBLE);
        loView.setVisibility(View.GONE);
        imgChart.setVisibility(View.GONE);
        imgText.setVisibility(View.VISIBLE);
        isText = false;
        if(rdgTime.getCheckedRadioButtonId() == R.id.rb_month){
            spnYear.setVisibility(View.GONE);
            spnMonth.setVisibility(View.GONE);
            initChartMonth();
        } else {
            spnMonth.setVisibility(View.GONE);
            spnYear.setVisibility(View.GONE);
            initChartYear();
        }
    }

    @OnClick(R.id.img_text)
    void showText(){
        loChart.setVisibility(View.GONE);
        loView.setVisibility(View.VISIBLE);
        imgChart.setVisibility(View.VISIBLE);
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

    List<Long> allAddMonth = new ArrayList<>();
    List<Long> allSubMonth = new ArrayList<>();
    private void getListDataMonth(){
        realmController = new RealmController();
        addList = realmController.getItemYear(1, Integer.parseInt(listYear.get(yearNow)));
        subList = realmController.getItemYear(2, Integer.parseInt(listYear.get(yearNow)));
        periodicList = realmController.getItemYear(3, Integer.parseInt(listYear.get(yearNow)));

        long t1=0, t2=0, t3=0,t4=0,t5=0,t6=0,t7=0,t8=0,t9=0,t10=0,t11=0,t12 = 0;
        for(Item i : addList){
            switch (i.getMonth()){
                case 1:
                    t1 += i.getAmount();
                    break;
                case 2:
                    t2 += i.getAmount();
                    break;
                case 3:
                    t3 += i.getAmount();
                    break;
                case 4:
                    t4 += i.getAmount();
                    break;
                case 5:
                    t5 += i.getAmount();
                    break;
                case 6:
                    t6 += i.getAmount();
                    break;
                case 7:
                    t7 += i.getAmount();
                    break;
                case 8:
                    t8 += i.getAmount();
                    break;
                case 9:
                    t9 += i.getAmount();
                    break;
                case 10:
                    t10 += i.getAmount();
                    break;
                case 11:
                    t11 += i.getAmount();
                    break;
                case 12:
                    t12 += i.getAmount();
                    break;
                default:
                    break;
            }
        }
        allAddMonth.add(t1);allAddMonth.add(t2);allAddMonth.add(t3);allAddMonth.add(t4);
        allAddMonth.add(t5);allAddMonth.add(t6);allAddMonth.add(t7);allAddMonth.add(t8);
        allAddMonth.add(t9);allAddMonth.add(t10);allAddMonth.add(t11);allAddMonth.add(t12);

        t1=0; t2=0; t3=0; t4=0; t5=0; t6=0; t7=0; t8=0; t9=0; t10=0; t11=0; t12 = 0;
        for(Item i : subList){
            switch (i.getMonth()){
                case 1:
                    t1 += i.getAmount();
                    break;
                case 2:
                    t2 += i.getAmount();
                    break;
                case 3:
                    t3 += i.getAmount();
                    break;
                case 4:
                    t4 += i.getAmount();
                    break;
                case 5:
                    t5 += i.getAmount();
                    break;
                case 6:
                    t6 += i.getAmount();
                    break;
                case 7:
                    t7 += i.getAmount();
                    break;
                case 8:
                    t8 += i.getAmount();
                    break;
                case 9:
                    t9 += i.getAmount();
                    break;
                case 10:
                    t10 += i.getAmount();
                    break;
                case 11:
                    t11 += i.getAmount();
                    break;
                case 12:
                    t12 += i.getAmount();
                    break;
                default:
                    break;
            }
        }
        allSubMonth.add(t1);allSubMonth.add(t2);allSubMonth.add(t3);allSubMonth.add(t4);
        allSubMonth.add(t5);allSubMonth.add(t6);allSubMonth.add(t7);allSubMonth.add(t8);
        allSubMonth.add(t9);allSubMonth.add(t10);allSubMonth.add(t11);allSubMonth.add(t12);

        for(Item i : periodicList){
            switch (i.getMonth()){
                case 1:
                    t1 += i.getAmount();
                    break;
                case 2:
                    t2 += i.getAmount();
                    break;
                case 3:
                    t3 += i.getAmount();
                    break;
                case 4:
                    t4 += i.getAmount();
                    break;
                case 5:
                    t5 += i.getAmount();
                    break;
                case 6:
                    t6 += i.getAmount();
                    break;
                case 7:
                    t7 += i.getAmount();
                    break;
                case 8:
                    t8 += i.getAmount();
                    break;
                case 9:
                    t9 += i.getAmount();
                    break;
                case 10:
                    t10 += i.getAmount();
                    break;
                case 11:
                    t11 += i.getAmount();
                    break;
                case 12:
                    t12 += i.getAmount();
                    break;
                default:
                    break;
            }
        }
        allSubMonth.clear();
        allSubMonth.add(t1);allSubMonth.add(t2);allSubMonth.add(t3);allSubMonth.add(t4);
        allSubMonth.add(t5);allSubMonth.add(t6);allSubMonth.add(t7);allSubMonth.add(t8);
        allSubMonth.add(t9);allSubMonth.add(t10);allSubMonth.add(t11);allSubMonth.add(t12);
    }

    List<Long> allAddYear = new ArrayList<>();
    List<Long> allSubYear = new ArrayList<>();
    private void getListDataYear(){
        realmController = new RealmController();
        addList = realmController.getItem(1);
        subList = realmController.getItem(2);
        periodicList = realmController.getItem(3);

        long n2018=0, n2019=0, n2020=0,n2021=0,n2022=0,n2023=0,n2024=0,n2025=0;
        for(Item i : addList){
            switch (i.getYear()){
                case 2018:
                    n2018 += i.getAmount();
                    break;
                case 2019:
                    n2019 += i.getAmount();
                    break;
                case 2020:
                    n2020 += i.getAmount();
                    break;
                case 2021:
                    n2021 += i.getAmount();
                    break;
                case 2022:
                    n2022 += i.getAmount();
                    break;
                case 2023:
                    n2023 += i.getAmount();
                    break;
                case 2024:
                    n2024 += i.getAmount();
                    break;
                case 2025:
                    n2025 += i.getAmount();
                    break;
                default:
                    break;
            }
        }
        allAddYear.add(n2018); allAddYear.add(n2019); allAddYear.add(n2020);
        allAddYear.add(n2021); allAddYear.add(n2022); allAddYear.add(n2023);
        allAddYear.add(n2024); allAddYear.add(n2025);


        n2018=0; n2019=0; n2020=0; n2021=0; n2022=0; n2023=0; n2024=0; n2025=0;
        for(Item i : subList){
            switch (i.getYear()){
                case 2018:
                    n2018 += i.getAmount();
                    break;
                case 2019:
                    n2019 += i.getAmount();
                    break;
                case 2020:
                    n2020 += i.getAmount();
                    break;
                case 2021:
                    n2021 += i.getAmount();
                    break;
                case 2022:
                    n2022 += i.getAmount();
                    break;
                case 2023:
                    n2023 += i.getAmount();
                    break;
                case 2024:
                    n2024 += i.getAmount();
                    break;
                case 2025:
                    n2025 += i.getAmount();
                    break;
                default:
                    break;
            }
        }
        allSubYear.add(n2018); allSubYear.add(n2019); allSubYear.add(n2020);
        allSubYear.add(n2021); allSubYear.add(n2022); allSubYear.add(n2023);
        allSubYear.add(n2024); allSubYear.add(n2025);

        for(Item i : periodicList){
            switch (i.getYear()){
                case 2018:
                    n2018 += i.getAmount();
                    break;
                case 2019:
                    n2019 += i.getAmount();
                    break;
                case 2020:
                    n2020 += i.getAmount();
                    break;
                case 2021:
                    n2021 += i.getAmount();
                    break;
                case 2022:
                    n2022 += i.getAmount();
                    break;
                case 2023:
                    n2023 += i.getAmount();
                    break;
                case 2024:
                    n2024 += i.getAmount();
                    break;
                case 2025:
                    n2025 += i.getAmount();
                    break;
                default:
                    break;
            }
        }
        allSubYear.clear();
        allSubYear.add(n2018); allSubYear.add(n2019); allSubYear.add(n2020);
        allSubYear.add(n2021); allSubYear.add(n2022); allSubYear.add(n2023);
        allSubYear.add(n2024); allSubYear.add(n2025);
    }
}
