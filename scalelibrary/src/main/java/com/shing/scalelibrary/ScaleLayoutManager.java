package com.shing.scalelibrary;

import android.content.Context;
import android.view.View;


@SuppressWarnings({"WeakerAccess", "unused"})
public class ScaleLayoutManager extends ViewPagerLayoutManager {

    private int itemSpace;
    private float centerScale;
    private float moveSpeed;
    private float maxAlpha;
    private float minAlpha;

    public ScaleLayoutManager(Context context, int itemSpace) {
        this(new Builder(context, itemSpace));
    }

    public ScaleLayoutManager(Context context, int itemSpace, int orientation) {
        this(new Builder(context, itemSpace).setOrientation(orientation));
    }

    public ScaleLayoutManager(Context context, int itemSpace, int orientation, boolean reverseLayout) {
        this(new Builder(context, itemSpace).setOrientation(orientation).setReverseLayout(reverseLayout));
    }

    public ScaleLayoutManager(Builder builder) {
        this(builder.context, builder.itemSpace, builder.centerScale, builder.maxAlpha, builder.minAlpha,
                builder.orientation, builder.moveSpeed, builder.maxVisibleItemCount, builder.shrinkSpace, builder.reverseLayout);
    }

    private ScaleLayoutManager(Context context, int itemSpace, float centerScale, float maxAlpha, float minAlpha,
                               int orientation, float moveSpeed, int maxVisibleItemCount, int shrinkSpace, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        setIntegerDy(true);
        setShrinkSpace(shrinkSpace);
        setMaxVisibleItemCount(maxVisibleItemCount);
        this.itemSpace = itemSpace;
        this.centerScale = centerScale;
        this.moveSpeed = moveSpeed;
        this.maxAlpha = maxAlpha;
        this.minAlpha = minAlpha;
    }

    public int getItemSpace() {
        return itemSpace;
    }

    public float getCenterScale() {
        return centerScale;
    }

    public int getOrientation() {
        return super.getOrientation();
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getMaxAlpha() {
        return maxAlpha;
    }

    public float getMinAlpha() {
        return minAlpha;
    }

    public void setItemSpace(int itemSpace) {
        assertNotInLayoutOrScroll(null);
        if (this.itemSpace == itemSpace) return;
        this.itemSpace = itemSpace;
        removeAllViews();
    }

    public void setCenterScale(float centerScale) {
        assertNotInLayoutOrScroll(null);
        if (this.centerScale == centerScale) return;
        this.centerScale = centerScale;
        removeAllViews();
    }

    public void setMaxAlpha(float maxAlpha) {
        assertNotInLayoutOrScroll(null);
        if (maxAlpha > 1) maxAlpha = 1;
        if (this.maxAlpha == maxAlpha) return;
        this.maxAlpha = maxAlpha;
        requestLayout();
    }

    public void setMinAlpha(float minAlpha) {
        assertNotInLayoutOrScroll(null);
        if (minAlpha < 0) minAlpha = 0;
        if (this.minAlpha == minAlpha) return;
        this.minAlpha = minAlpha;
        requestLayout();
    }

    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }

    public void setMoveSpeed(float moveSpeed) {
        assertNotInLayoutOrScroll(null);
        if (this.moveSpeed == moveSpeed) return;
        this.moveSpeed = moveSpeed;
    }

    @Override
    protected float setInterval() {
        return mDecoratedMeasurement * ((centerScale - 1) / 2 + 1) + itemSpace;
    }

    @Override
    protected void setItemViewProperty(View itemView, float targetOffset) {
        float scale = calculateScale(targetOffset + mSpaceMain);
        itemView.setScaleX(scale);
        itemView.setScaleY(scale);
        final float alpha = calAlpha(targetOffset);
        itemView.setAlpha(alpha);
    }

    private float calAlpha(float targetOffset) {
        final float offset = Math.abs(targetOffset);
        float alpha = (minAlpha - maxAlpha) / mInterval * offset + maxAlpha;
        if (offset >= mInterval) alpha = minAlpha;
        return alpha;
    }

    @Override
    protected float getDistanceRatio() {
        if (moveSpeed == 0) return Float.MAX_VALUE;
        return 1 / moveSpeed;
    }

    /**
     * @param x start positon of the view you want scale
     * @return the scale rate of current scroll mOffset
     */
    private float calculateScale(float x) {
        float deltaX = Math.abs(x - (mOrientationHelper.getTotalSpace() - mDecoratedMeasurement) / 2f);
        float diff = 0f;
        if ((mDecoratedMeasurement - deltaX) > 0) diff = mDecoratedMeasurement - deltaX;
        return (centerScale - 1f) / mDecoratedMeasurement * diff + 1;
    }

    public static class Builder {
        private static final float SCALE_RATE = 1.2f;
        private static final float DEFAULT_SPEED = 1f;
        private static float MIN_ALPHA = 1f;
        private static float MAX_ALPHA = 1f;

        private int itemSpace;
        private int shrinkSpace;
        private int orientation;
        private float centerScale;
        private float moveSpeed;
        private float maxAlpha;
        private float minAlpha;
        private boolean reverseLayout;
        private Context context;
        private int maxVisibleItemCount;

        public Builder(Context context, int itemSpace) {
            this.itemSpace = itemSpace;
            this.context = context;
            orientation = HORIZONTAL;
            centerScale = SCALE_RATE;
            this.moveSpeed = DEFAULT_SPEED;
            maxAlpha = MAX_ALPHA;
            minAlpha = MIN_ALPHA;
            reverseLayout = false;
            maxVisibleItemCount = ViewPagerLayoutManager.DETERMINE_BY_MAX_AND_MIN;
        }

        public Builder setOrientation(int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder setCenterScale(float centerScale) {
            this.centerScale = centerScale;
            return this;
        }

        public Builder setReverseLayout(boolean reverseLayout) {
            this.reverseLayout = reverseLayout;
            return this;
        }

        public Builder setMaxAlpha(float maxAlpha) {
            if (maxAlpha > 1) maxAlpha = 1;
            this.maxAlpha = maxAlpha;
            return this;
        }

        public Builder setMinAlpha(float minAlpha) {
            if (minAlpha < 0) minAlpha = 0;
            this.minAlpha = minAlpha;
            return this;
        }

        public Builder setMoveSpeed(float moveSpeed) {
            this.moveSpeed = moveSpeed;
            return this;
        }

        public Builder setMaxVisibleItemCount(int maxVisibleItemCount) {
            this.maxVisibleItemCount = maxVisibleItemCount;
            return this;
        }

        public Builder setShrinkSpace(int shrinkSpace) {
            this.shrinkSpace = shrinkSpace;
            return this;
        }

        public ScaleLayoutManager build() {
            return new ScaleLayoutManager(this);
        }
    }
}

