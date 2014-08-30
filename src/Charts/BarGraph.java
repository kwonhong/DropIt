package Charts;

import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.Classes.Work;

public class BarGraph{

	List<Percentage_Item> items;
	HashMap<String, List<Work>> works;
	
	
	public BarGraph(List<Percentage_Item> items, HashMap<String, List<Work>> works) {
		// getting all the percentage items and works from the previous activity
		this.items = items;
		this.works = works;
	}
	
	
	public GraphicalView getGraphic(Context context) 
	{	
		int[] margins = {0,20,0,20};
		double[] losts = new double[items.size()];
		
		//calculating the percentage lost for each percentage items
		for (int i =0; i<items.size(); i++) {
			
			double outof;
			double mark;
			double total_lost = 0;

			List<Work> worklist = works.get(items.get(i).getName());
			for (int j=0; j<worklist.size(); j++) {
				outof= worklist.get(j).getOut_of();
				mark = worklist.get(j).getMark();
				total_lost += mark/outof;
			}
			//adding all the percentages for each assignments and divide by the number of assignments
			if (worklist.size() != 0)
				total_lost = 1- total_lost/worklist.size(); 
			
			losts[i] =  total_lost* (items.get(i).getPercentage());
		}
		
		CategorySeries series = new CategorySeries("Bar1");
        for(int i=0; i < losts.length; i++){
            series.add("Bar"+(i+1),losts[i]);
        }
        
        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();  // collection of series under one object.,there could any
        dataSet.addSeries(series.toXYSeries());  
        
        XYSeriesRenderer renderer = new XYSeriesRenderer();     // one renderer for one series
        renderer.setColor(Color.YELLOW);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 5.5d);
        renderer.setLineWidth((float) 10.5d);
        renderer.setChartValuesSpacing(20);
            
        
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();   // collection multiple values for one renderer or series
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setYAxisMax(100);
        mRenderer.setYAxisMin(0);
        mRenderer.setXAxisMax(5);
        mRenderer.setXAxisMin(0.5);
        mRenderer.setZoomEnabled(false);
        mRenderer.setPanEnabled(false);
        mRenderer.setScale((float) 1.4);
        mRenderer.setMargins(margins);
        mRenderer.setBarSpacing(0.1);
		
        return ChartFactory.getBarChartView(context, dataSet, mRenderer, Type.DEFAULT);
		
//		XYSeries loan1Series = new XYSeries("hello1");
//	    XYSeries loan2Series = new XYSeries("hello2");
//	    
//		for (int i = 0; i < items.size(); i++) {
//			if (i%2 == 0)
//			loan1Series.add(i/2+1, items.get(i).getPercentage());
//			else
//			loan2Series.add(i/2+1, items.get(i).getPercentage());
//		}
//		loan2Series.add(2, 30);
//        
//        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//        // Adding loan 1 Series to the dataset
//        dataset.addSeries(loan1Series);
//        // Adding loan 2 Series to dataset
//        dataset.addSeries(loan2Series);   
//
//        XYSeriesRenderer loan1Renderer = new XYSeriesRenderer();
//        loan1Renderer.setColor(Color.YELLOW);
//        loan1Renderer.setFillPoints(true);
//        loan1Renderer.setChartValuesTextSize(20);
//        loan1Renderer.setLineWidth(0.2f);
//        loan1Renderer.setDisplayChartValues(true);
//
//        // Creating XYSeriesRenderer to customize expenseSeries
//        XYSeriesRenderer loan2Renderer = new XYSeriesRenderer();
//        loan2Renderer.setColor(Color.parseColor("#5eae1f"));
//        loan2Renderer.setFillPoints(true);
//        loan2Renderer.setChartValuesTextSize(20);
//        loan2Renderer.setLineWidth(0.2f);
//        loan2Renderer.setDisplayChartValues(true); 
//        
//        
//        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
//        //multiRenderer.setXLabels(0);
//        multiRenderer.setChartTitle("Difference between Two loan");
//        //multiRenderer.setXTitle("Year 2012");
//       // multiRenderer.setYTitle("Amount in Dollars");
//        multiRenderer.setZoomButtonsVisible(false);
//        multiRenderer.setZoomEnabled(false);
//        multiRenderer.setPanEnabled(false);
//        multiRenderer.setInScroll(true);
//        multiRenderer.setClickEnabled(false);
//        multiRenderer.setXAxisMin(0);
//        multiRenderer.setXAxisMax(items.size());
//        multiRenderer.setYAxisMax(100);
//        multiRenderer.setYAxisMin(0);
//        multiRenderer.setFitLegend(true);
//        //multiRenderer.setLegendHeight(50);
//
//        //For apply background
//        multiRenderer.setApplyBackgroundColor(true);
//        multiRenderer.setBackgroundColor(Color.WHITE);
//        multiRenderer.setMarginsColor(Color.WHITE);
//
//
//        //multiRenderer.setLegendTextSize(20);
//
//        multiRenderer.setAxisTitleTextSize(20);
//        multiRenderer.setChartTitleTextSize(28);
//        multiRenderer.setLabelsTextSize(18);
//        multiRenderer.setLegendTextSize(18);
//        //multiRenderer.setLegendHeight(5);
//        // for x axis
//        //multiRenderer.setXLabelsAlign(Align.CENTER);
//        multiRenderer.setXLabels(0);
//        //for y axis
//        multiRenderer.setYLabelsAlign(Align.RIGHT);
//
//        // main axis 
//        multiRenderer.setAxisTitleTextSize(22);
//        multiRenderer.setLabelsColor(Color.BLACK);
//
//        //multiRenderer.setFitLegend(true);
//        //multiRenderer.setZoomRate(0.2f); 
//        //multiRenderer.setMargins(new int[] { 70, 50, 50, 30 });
//        //multiRenderer.setBarSpacing(0.2f);
//        multiRenderer.addSeriesRenderer(loan1Renderer);
//        multiRenderer.addSeriesRenderer(loan2Renderer);
	    
	   
	    
		/*Intent intent = ChartFactory.getBarChartIntent(context, dataset,mRenderer, Type.DEFAULT);
		return intent;*/
	}

}
