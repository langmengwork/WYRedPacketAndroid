package com.example.lx.wyredpacketandroid.utils.customview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.lx.wyredpacketandroid.R;
import com.example.lx.wyredpacketandroid.ui.activity.wallet.WalletActivity;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class ChartMarkerView extends MarkerView{

    private final TextView chart_tip_item;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public ChartMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        chart_tip_item = findViewById(R.id.chart_tip_item);
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        chart_tip_item.setTextColor(getResources().getColor(R.color.colorWhite));
        chart_tip_item.setText("¥"+e.getY());

        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {
        if(mOffset==null){
            mOffset=new MPPointF(-(getWidth()/2),-getHeight());
        }
        return mOffset;
    }
}
