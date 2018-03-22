package com.zou.fund.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zou.fund.R;
import com.zou.fund.parse.P_lsjz;
import com.zou.fund.sqlbean.SQL_lsjz;
import com.zou.fund.util.Network;

import org.litepal.crud.DataSupport;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by li on 2018/3/15.
 */

public class Activity_lsji extends AppCompatActivity {
    private LineChartView lineChart;
    Network network;
    String code;
    List<SQL_lsjz> list=new ArrayList<>();

    private List<Float> pointValues = new ArrayList<>();
    private List<String> axisXValues = new ArrayList<String>();

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result= (String) msg.obj;
            P_lsjz p_lsjz=new P_lsjz(result);
            list= DataSupport.select("lsjz","date","zhangfu").order("date asc").find(SQL_lsjz.class);
            pointfromt();
            getAxisXLables();//获取x轴的标注
            getAxisPoints();//获取坐标点
            initLineChart();//初始化
        }
    };
    public void pointfromt(){
        float base=Float.parseFloat(list.get(0).getLsjz());
        for (int i=0;i<list.size();i++){

            float lsjz=Float.parseFloat(list.get(i).getLsjz());
            float point=(lsjz/base-1)*100;
            pointValues.add(point);
        };

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsjz_chars);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        network=Network.getnetwork();
        code = getIntent().getStringExtra("code");
        String url="http://fund.jrj.com.cn/json/archives/history/netvalue?fundCode="+code+"&obj=obj&date=2017";
        network.Loadhtpp(handler,url,1);
        lineChart = (LineChartView) findViewById(R.id.char_lsjz);
    }
    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < list.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(list.get(i).getDate()));
        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < list.size(); i++) {
            mPointValues.add(new PointValue(i, pointValues.get(i)));
        }
    }

    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
       line.setPointRadius(2);
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        axisX.setName("");  //表格名称
       axisX.setTextSize(15);//设置字体大小
        axisX.setMaxLabelChars(5); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线
        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
         axisY.setName("净值");//y轴标注
          axisY.setTextColor(Color.BLACK);
//        axisY.setTextSize(15);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setScrollEnabled(true);
        lineChart.setMaxZoom((float) 3);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 1000;
        lineChart.setCurrentViewport(v);
    }
}