package edu.bdu.tools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

public class TestBarChartDemo {
	private static CategoryDataset createDataset()
	{
		//row keys...
		String series1 = "第一";
		String series2 = "第二";
		String series3 = "第三";
		
		//column keys...
		String category1 = "Category 1";
		String category2 = "Category 2";
		String category3 = "Category 3";
		String category4 = "Category 4";
		String category5 = "Category 5";
		
		//create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(1.0, series1, category1);
	    dataset.addValue(4.0, series1, category2);
	    dataset.addValue(3.0, series1, category3);
	    dataset.addValue(5.0, series1, category4);
	    dataset.addValue(5.0, series1, category5);
	    
	    dataset.addValue(5.0, series2, category1);
	    dataset.addValue(7.0, series2, category2);
	    dataset.addValue(6.0, series2, category3);
	    dataset.addValue(8.0, series2, category4);
	    dataset.addValue(4.0, series2, category5);
	    
	    dataset.addValue(4.0, series3, category1);
	    dataset.addValue(3.0, series3, category2);
	    dataset.addValue(2.0, series3, category3);
	    dataset.addValue(3.0, series3, category4);
	    dataset.addValue(6.0, series3, category5);
	    
	    return dataset;
		
	}
	
	/**
	 * Creates a sample chart.
	 * @param dataset the dataset.
	 * @return The chart.
	 */
	
    private static JFreeChart createChart(CategoryDataset dataset)
    {
    	//create the chart...
    	JFreeChart chart = ChartFactory.createBarChart(
    			"测试",//chart title
    			"时间测试",//domain axis label
    			"时间值",//range axis label
    			dataset,//data
    			PlotOrientation.VERTICAL,//orientation
    			true, //include legend
    			true,//tooltips?
    			false//URLs?
    		);
    	
    	//NOW DO OPTIONAL CUSTOMISATION OF CHART...
    	
		//get a reference to the plot for further customisation...
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
       
		//set the range axis to display integers only...
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		// disable bar outlines...
		BarRenderer renderer = (BarRenderer)plot.getRenderer();
		
		renderer.setDrawBarOutline(false);
		
		//the SWTGraphics2D class doesn't handle GradientPaint well, so
		//replace the gradient painter from the default theme with a
		//standard painter...
		renderer.setBarPainter(new StandardBarPainter());
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		
		domainAxis.setCategoryLabelPositions(
		CategoryLabelPositions.createUpRotationLabelPositions(
				Math.PI/6.0));
		
		//OPTIONAL CUSTOMISATION COMPLETED.
		
		return chart;
		
		
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JFreeChart chart = createChart(createDataset());
        
        Display display = new Display();
        
        Shell shell = new Shell(display);
        
        shell.setSize(600,300);
        
        shell.setLayout(new FillLayout());
        
        shell.setText("Test for jfreechart running with SWT");
        
        ChartComposite frame = new ChartComposite(shell, SWT.NONE,chart,
        		true);
        frame.pack();
        shell.open();
        
        while(!shell.isDisposed()){
        	if(!display.readAndDispatch())
        		display.sleep();
        }
        
	}

}
