package capstone.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Color;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoreTempFragment extends BaseFragment implements DataObserver {
    private LineChart lineChart;

    private ParamReceiver mReceiver;
    private Intent intent;

    public class ParamReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String param = intent.getStringExtra("CORE_UPDATE");
            if(param != null) {
                BottomBarActivity activity = (BottomBarActivity) getActivity();
                activity.updateCoreFragment(param);
            }

        }
    }

    public static CoreTempFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        CoreTempFragment fragment = new CoreTempFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(){
        mReceiver = new ParamReceiver();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction("capstone.client.BackgroundParameterUpdate.PARAM_UPDATE");
        getActivity().registerReceiver(mReceiver, ifilter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentDataManager fm = (FragmentDataManager)getActivity();
        fm.registerFragment(this);


        intent = new Intent();
        intent.setAction("CORE");
        intent.setClass(getActivity(), BackgroundParameterUpdate.class);
        getActivity().startService(intent);
        View view = inflater.inflate(R.layout.fragment_core_temp, container, false);
        TextView tv = (TextView) view.findViewById(R.id.currentCoreTemp);
        updateParam(((DRDCClient) getActivity().getApplication()).getLastCoreTemp(), tv);
        return view;
    }


    /***
     *
     * @param data the update data from from the background
     */
    @Override
    public void update(Map data) {
        lineChart = (LineChart) getActivity().findViewById(R.id.coreTempChart);
        float[] skinTemps= (float[]) data.get("coreTemp");

        List<Entry> entries = new ArrayList<Entry>();

        for(int i = 0; i< skinTemps.length; i++){
            entries.add(new Entry(i, skinTemps[i]));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        //dataSet.setHighlightEnabled(true);
        //dataSet.setColor();
        //dataSet.setValueTextColor(...); // styling, ...
        LineData lineData = new LineData(dataSet);
        //dataSet.setDrawFilled(true);
        //dataSet.setFillDrawable(gradientDrawable);
        lineChart.setData(lineData);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        YAxis yAxis = lineChart.getAxisLeft();
        //yAxis.setTypeface(...);
        yAxis.setTextSize(14f); // set the text size
        yAxis.setAxisMinimum(30f); // start at zero
        yAxis.setAxisMaximum(45f); // the axis maximum is 100
        yAxis.setTextColor(Color.WHITE);
        //yAxis.setValueFormatter(new MyValueFormatter());
        //yAxis.setGranularity(1f); // interval 1
        yAxis.setLabelCount(5, true); // force 6 labels
        lineChart.setNoDataText("Loading...");
        lineChart.invalidate(); // refresh


    }

    @Override
    public void onResume(){
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction("capstone.client.BackgroundParameterUpdate.PARAM_UPDATE");
        getActivity().registerReceiver(mReceiver, ifilter);
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        try {
            getActivity().unregisterReceiver(mReceiver);
            getActivity().stopService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        try {
FragmentDataManager fm = (FragmentDataManager)getActivity();
        fm.unregisterFragment(this);
            getActivity().unregisterReceiver(mReceiver);
            getActivity().stopService(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroyView();
    }


    public void updateParam(String param, TextView hr){
        hr.setText(param);
        hr.refreshDrawableState();
    }
}
