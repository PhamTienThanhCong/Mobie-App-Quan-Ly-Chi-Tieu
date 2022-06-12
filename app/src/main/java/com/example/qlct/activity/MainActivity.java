package com.example.qlct.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.qlct.adapter.PagerAdapter;
import com.example.qlct.dialog.AddDialog;
import com.example.qlct.fragment.AddFragment;
import com.example.qlct.fragment.OverViewFragment;
import com.example.qlct.fragment.PeriodicFragment;
import com.example.qlct.R;
import com.example.qlct.fragment.SubFragment;
import com.example.qlct.message.MessageListener;
import com.example.qlct.message.MessageReceiver;
import com.example.qlct.model.Item;
import com.example.qlct.model.Mess;
import com.example.qlct.realm.RealmController;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MessageListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private RealmController realmController;

    private PagerAdapter adapter;
    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MessageReceiver.bindListener(this);
        realmController = new RealmController();
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS},
                MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
    }

    private void setupViewPager() {
        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OverViewFragment(), "Thống kê");
        adapter.addFragment(new AddFragment(), "Thu");
        adapter.addFragment(new SubFragment(), "Chi");
        adapter.addFragment(new PeriodicFragment(), "Định kỳ");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void messageReceived(String sender, String body) {
//        OverViewFragment frm = (OverViewFragment) getSupportFragmentManager().getFragments().get(0);
//        frm.setData(body);
//        Log.d("TAG", "MY_PERMISSIONS_REQUEST_SMS_RECEIVE" + body);

//        AddFragment addFragment = (AddFragment) getSupportFragmentManager().getFragments().get(1);
        receiveMess(sender, body);

    }

    int type;

    public void receiveMess(String sender, String body) {
//        String bank = getBank(sender);
        String bank = sender;
        String amount = getAmount(body);
        Mess message = new Mess(bank, type, amount);

        addFromBank(message);
    }

    private String getBank(String sender) {
        if (MessageReceiver.LIST_BANK.contains(sender)) {
            return sender;
        }
        return null;
    }

    private String getAmount(String bodyMess) {
        String string2 = "";
        String amount = "";
        if (bodyMess != null) {
            if (bodyMess.contains("+")) {
                type = 1;
                string2 = bodyMess.split("\\+")[1];

                for (int i = 0; i < string2.length(); i++) {
                    try {
                        Integer.parseInt(string2.charAt(i) + "");
                        amount = amount + "" + string2.charAt(i);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        if (!(string2.charAt(i) + "").equals(" ")
                                && !(string2.charAt(i) + "").equals(",")
                                && !(string2.charAt(i) + "").equals(".")
                                && !(string2.charAt(i) + "").equals("(")
                                && !(string2.charAt(i) + "").equals(")")
                        ) {
                            break;
                        }
                    }
                }
            } else if (bodyMess.contains("-")) {
                type = 2;
                string2 = bodyMess.split("-")[1];
                for (int i = 0; i < string2.length(); i++) {
                    try {
                        Integer.parseInt(string2.charAt(i) + "");
                        amount = amount + "" + string2.charAt(i);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        if (!(string2.charAt(i) + "").equals(" ")
                                && !(string2.charAt(i) + "").equals(",")
                                && !(string2.charAt(i) + "").equals(".")
                                && !(string2.charAt(i) + "").equals("(")
                                && !(string2.charAt(i) + "").equals(")")) {
                            break;
                        }
                    }
                }
            }
            return amount;
        }
        return null;
    }

    public void addFromBank(Mess mess) {
        Date date = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String d = sdf.format(date);

        Item item = new Item((int) System.currentTimeMillis(), mess.type, mess.bank, "Ngân hàng", d, null, Long.parseLong(mess.amount), null, AddDialog.getMonth(d), AddDialog.getYear(d));
        realmController.addItem(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SMS_RECEIVE) {
            Log.i("TAG", "MY_PERMISSIONS_REQUEST_SMS_RECEIVE --> YES");
        } else {
            Log.i("TAG", "MY_PERMISSIONS_REQUEST_SMS_RECEIVE --> NO");
        }
    }
}
