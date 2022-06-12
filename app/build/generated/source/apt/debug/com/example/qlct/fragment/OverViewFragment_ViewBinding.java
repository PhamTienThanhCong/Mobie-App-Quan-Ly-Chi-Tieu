// Generated code from Butter Knife. Do not modify!
package com.example.qlct.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.qlct.R;
import com.github.mikephil.charting.charts.BarChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OverViewFragment_ViewBinding implements Unbinder {
  private OverViewFragment target;

  private View view2131230812;

  private View view2131230813;

  @UiThread
  public OverViewFragment_ViewBinding(final OverViewFragment target, View source) {
    this.target = target;

    View view;
    target.loView = Utils.findRequiredViewAsType(source, R.id.lo_view, "field 'loView'", LinearLayout.class);
    target.loChart = Utils.findRequiredViewAsType(source, R.id.lo_chart, "field 'loChart'", LinearLayout.class);
    target.txtTotalRevenue = Utils.findRequiredViewAsType(source, R.id.txt_total_revenue, "field 'txtTotalRevenue'", TextView.class);
    target.txtTotalExpenditure = Utils.findRequiredViewAsType(source, R.id.txt_total_expenditure, "field 'txtTotalExpenditure'", TextView.class);
    target.txtSurplus = Utils.findRequiredViewAsType(source, R.id.txt_surplus, "field 'txtSurplus'", TextView.class);
    view = Utils.findRequiredView(source, R.id.img_chart, "field 'imgChart' and method 'showChart'");
    target.imgChart = Utils.castView(view, R.id.img_chart, "field 'imgChart'", ImageView.class);
    view2131230812 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showChart();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_text, "field 'imgText' and method 'showText'");
    target.imgText = Utils.castView(view, R.id.img_text, "field 'imgText'", ImageView.class);
    view2131230813 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showText();
      }
    });
    target.rdgTime = Utils.findRequiredViewAsType(source, R.id.rdg_time, "field 'rdgTime'", RadioGroup.class);
    target.rbMonth = Utils.findRequiredViewAsType(source, R.id.rb_month, "field 'rbMonth'", RadioButton.class);
    target.rbYear = Utils.findRequiredViewAsType(source, R.id.rb_year, "field 'rbYear'", RadioButton.class);
    target.chart = Utils.findRequiredViewAsType(source, R.id.chart, "field 'chart'", BarChart.class);
    target.spnMonth = Utils.findRequiredViewAsType(source, R.id.spn_month, "field 'spnMonth'", Spinner.class);
    target.spnYear = Utils.findRequiredViewAsType(source, R.id.spn_year, "field 'spnYear'", Spinner.class);
    target.txtMess = Utils.findRequiredViewAsType(source, R.id.mess, "field 'txtMess'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OverViewFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loView = null;
    target.loChart = null;
    target.txtTotalRevenue = null;
    target.txtTotalExpenditure = null;
    target.txtSurplus = null;
    target.imgChart = null;
    target.imgText = null;
    target.rdgTime = null;
    target.rbMonth = null;
    target.rbYear = null;
    target.chart = null;
    target.spnMonth = null;
    target.spnYear = null;
    target.txtMess = null;

    view2131230812.setOnClickListener(null);
    view2131230812 = null;
    view2131230813.setOnClickListener(null);
    view2131230813 = null;
  }
}
