package edu.bdu.tools;

import java.awt.Color;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.RectangleInsets;
public class TestTimeSeriesDemo {
	/**
	 * Creates a chart.
	 * @param dataset a dataset.
	 * @return A chart.
	 */
	private static JFreeChart createChart(XYDataset dataset){
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				    "Legal & General Unit Trust Prices",//title
				    "Date", //x-axis label
				    "Price Per Unit",//y-axis label
				    dataset,//data 
				    true, //create legend?
				    true, //generate tooltips?
				    false//generate URLs?
				    );
		chart.setBackgroundPaint(Color.white);
		
		XYPlot plot = (XYPlot)chart.getPlot();
		
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		
		XYItemRenderer r = plot.getRenderer();
	    if(r instanceof XYLineAndShapeRenderer){
	    	XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
	    	
	    	renderer.setBaseShapesVisible(true);
	    	renderer.setBaseShapesFilled(true);
	    }
	    
	    DateAxis axis = (DateAxis)plot.getDomainAxis();
	    axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
	    
	    return chart;
	}
    
	/**
	 * Creates a dataset,consisting of two series of monthly data.
	 * 
	 * @return The dataset.
	 */
	private static XYDataset createDataset(){
		
		TimeSeries s1 = new TimeSeries("L&G European Index Trust");
		
		s1.add(new Month(2, 2001), 181.8);
		s1.add(new Month(3, 2001), 167.3);
		s1.add(new Month(4, 2001), 153.8);
		s1.add(new Month(5, 2001), 167.6);
		s1.add(new Month(6, 2001), 158.8);
		s1.add(new Month(7, 2001), 148.3);
		s1.add(new Month(8, 2001), 153.9);
		s1.add(new Month(9, 2001), 142.7);
		s1.add(new Month(10, 2001), 123.2);
		s1.add(new Month(11, 2001), 131.8);
		s1.add(new Month(12, 2001), 139.6);
		s1.add(new Month(1, 2002), 142.9);
		s1.add(new Month(2, 2002), 138.7);
		s1.add(new Month(3, 2002), 137.3);
		s1.add(new Month(4, 2002), 143.9);
		s1.add(new Month(5, 2002), 139.8);
		s1.add(new Month(6, 2002), 137.0);
		s1.add(new Month(7, 2002), 132.8);
		
		TimeSeries s2 = new TimeSeries("L&G Index Trust");
		s2.add(new Month(2, 2001), 129.6);
	    s2.add(new Month(3, 2001), 123.2);
	    s2.add(new Month(4, 2001), 117.2);
	    s2.add(new Month(5, 2001), 124.1);
	    s2.add(new Month(6, 2001), 122.6);
	    s2.add(new Month(7, 2001), 119.2);
	    s2.add(new Month(8, 2001), 116.5);
	    s2.add(new Month(9, 2001), 112.7);
	    s2.add(new Month(10, 2001), 101.5);
	    s2.add(new Month(11, 2001), 106.1);
	    s2.add(new Month(12, 2001), 110.3);
	    s2.add(new Month(1, 2002), 111.7);
	    s2.add(new Month(2, 2002), 111.0);
	    s2.add(new Month(3, 2002), 109.6);
	    s2.add(new Month(4, 2002), 113.2);
	    s2.add(new Month(5, 2002), 111.6);
	    s2.add(new Month(6, 2002), 108.8);
	    s2.add(new Month(7, 2002), 101.6);
	    
	    TimeSeriesCollection dataset = new TimeSeriesCollection();
	    dataset.addSeries(s1);
	    dataset.addSeries(s2);
	    
	    return dataset;
		
		
	}
/**
 * Starting point for the demonstration application.
 * @param args ignored.
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final JFreeChart chart = createChart(createDataset());
		
		final Display display = new Display();
		
		Shell shell = new Shell();
		
		shell.setSize(600, 300);
		
		shell.setLayout(new FillLayout());
		
		shell.setText("Time series demo for jfreechart running with SWT");
		
		ChartComposite frame = new ChartComposite(shell,
				SWT.NONE, chart, true);
		
		frame.setDisplayToolTips(true);
		frame.setHorizontalAxisTrace(false);
		frame.setVerticalAxisTrace(false);
		shell.open();
		
		while(!shell.isDisposed()){
			
			if(!display.readAndDispatch())
				display.sleep();
		}

	}

}
