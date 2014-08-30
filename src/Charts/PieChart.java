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

public class PieChart {
	
	int[] Mycolors = new int[] { Color.YELLOW, Color.WHITE, Color.BLUE, Color.CYAN };
	List<Percentage_Item> items = new ArrayList<Percentage_Item> ();

	public PieChart(List<Percentage_Item> items) {
		this.items = items;
	}
	
	public GraphicalView getGraphic(Context context) 
	{	
		int[] colors = new int[items.size()];
		double[] values = new double[items.size()];
		String[] labels = new String[items.size()];
	    for (int i = 0; i < items.size(); i++) {
	        colors[i] = Mycolors[i];
	        values[i] = items.get(i).getPercentage();
	        labels[i] = items.get(i).getName();
	    }
	   
		
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
