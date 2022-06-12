// Generated code from Butter Knife. Do not modify!
package com.example.qlct.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.qlct.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddDialog_ViewBinding implements Unbinder {
  private AddDialog target;

  @UiThread
  public AddDialog_ViewBinding(AddDialog target, View source) {
    this.target = target;

    target.spnTopic = Utils.findRequiredViewAsType(source, R.id.spn_topic, "field 'spnTopic'", Spinner.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spnTopic = null;
  }
}
