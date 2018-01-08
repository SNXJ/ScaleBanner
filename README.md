# ScaleBanner



**使用**


    像正常的LayoutManager一样

      属性设置

    //        scaleLayoutManager.setMaxVisibleItemCount(1);
            scaleLayoutManager.setItemSpace(5);
            scaleLayoutManager.setCenterScale(1.2f);
    //        scaleLayoutManager.setMoveSpeed(1.0f);//速率
            scaleLayoutManager.setOrientation(ViewPagerLayoutManager.HORIZONTAL);
            scaleLayoutManager.setMaxAlpha(1.0f);//透明度
            scaleLayoutManager.setMinAlpha(1.0f);
            scaleLayoutManager.setReverseLayout(false);//翻转
            scaleLayoutManager.setInfinite(true);//无限轮播
    //        CenterSnapHelper centerSnapHelper = new CenterSnapHelper();
    //      centerSnapHelper.attachToRecyclerView(mRecyclerView);//是否居中  null不控制//需要在setLayoutManager（）之后设置

            AutoPlaySnapHelper autoPlaySnapHelper= new AutoPlaySnapHelper(AutoPlaySnapHelper.TIME_INTERVAL,AutoPlaySnapHelper.RIGHT);//间隔 和方向     自动轮播

            autoPlaySnapHelper.attachToRecyclerView(mRecyclerView);//是否居中  null不控制//需要在setLayoutManager（）之后设置
