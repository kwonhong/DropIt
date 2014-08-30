package Charts;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

import com.example.winterproject.Classes.Percentage_Item;

public class TimePieChart {
	
	int[] Mycolors = new int[] { Color.YELLOW, Color.WHITE, Color.BLUE, Color.CYAN , Color.GRAY, Color.RED};
	List<Percentage_Item> items = new ArrayList<Percentage_Item> ();
	
	int[] colors;
	double[] values = new double[items.size()];
	String[] labels = new String[items.size()];

	public TimePieChart(double[] values, String[] labels) {
		this.values = values;
		this.labels = labels;
		this.colors = new int[labels.length];
		for (int i =0; i<colors.length; i++) {
			colors[i] = Mycolors[i];
		}
	}
	
	public GraphicalView getGraphic(Context context) 
	{	
		
	    
	   
		
		DefaultRenderer renderer = buildCategoryRenderer(colors);
	    renderer.setShowLabels(true);
	    renderer.setLabelsTextSize(20);
	    renderer.setInScroll(true);
	    renderer.setStartAngle(90);
	    renderer.setPanEnabled(false);// Disable User Interaction
	    renderer.setZoomEnabled(false);
	    renderer.setScale((float) 1.0);
	    renderer.setApplyBackgroundColor(true);
	    renderer.setBackgroundColor(Color.BLACK); 
	    renderer.setShowLegend(false);
	    
	    MultipleCategorySeries categorySeries = new MultipleCategorySeries( "Sample");
	    categorySeries.add(labels, values);
	 
	 return ChartFactory.getDoughnutChartView(context, categorySeries, renderer);
	}
	
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
	    DefaultRenderer renderer = new DefaultRenderer();
	    for (int color : colors) {
	        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	        r.setColor(color);
	        renderer.addSeriesRenderer(r);

	    }
	    return renderer;
	}
}
