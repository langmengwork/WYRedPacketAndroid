package com.example.lx.wyredpacketandroid.utils.recycleviewutil;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lx.wyredpacketandroid.utils.CodeUtil;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private int id;

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (id == CodeUtil.SPAC_ONE) {
            outRect.bottom = mSpace;
            return;
        } else if (id == CodeUtil.SPAC_TWO) {
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.left = mSpace;
            }
        } else if (id == CodeUtil.SPAC_THREE) {
            outRect.bottom = mSpace;
            outRect.left = mSpace;
        }
//        outRect.left = mSpace;
//        outRect.right = mSpace;

//        if (parent.getChildAdapterPosition(view) == 0) {
//            outRect.top = mSpace;
//        }

    }

    public SpaceItemDecoration(int space,int id) {
        this.mSpace = space;
        this.id = id;
    }
}