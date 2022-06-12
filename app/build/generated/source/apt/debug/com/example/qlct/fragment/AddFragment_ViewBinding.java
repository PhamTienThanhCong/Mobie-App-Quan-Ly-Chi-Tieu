// Generated code from Butter Knife. Do not modify!
package com.example.qlct.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.qlct.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddFragment_ViewBinding implements Unbinder {
  private AddFragment target;

  private View view2131230755;

  @UiThread
  public AddFragment_ViewBinding(final AddFragment target, View source) {
    this.target = target;

    View view;
    target.rcvAdd = Utils.findRequiredViewAsType(source, R.id.rcv_add, "field 'rcvAdd'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btn_add, "method 'add'");
    view2131230755 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.add();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rcvAdd = null;

    view2131230755.setOnClickListener(null);
    view2131230755 = null;
  }
}
