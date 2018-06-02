package edu.bdu.tools;
import java.awt.Font;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

/**
 * This demo shows a time series chart that has multiple range axes.
 * @author lenov
 *
 */
public class TestPieChartDemo {
	
	/**
	 * Create a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private static PieDataset createDataset(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("One", new Double(43.2));
		dataset.setValue("Two", new Double(10.0));
		dataset.setValue("Three", new Double(27.5));
		dataset.setValue("Four", new Double(17.5));
		dataset.setValue("Five", new Double(11.0));
		dataset.setValue("Six", new Double(19.4));
		
		return dataset;
	}
	/**
	 * Creates a chart.
	 * @param dataset the dataset.
	 * @return A chart.
	 */
	private static JFreeChart createChart(PieDataset dataset){
		
		JFreeChart chart = ChartFactory.createPieChart(
				"Pie Chart Demo 1",//chart title
				 dataset,//data 
				 true,//include legend
				 true,
				 false
				);
		
		PiePlot plot = (PiePlot)chart.getPlot();
		
		plot.setSectionOutlinesVisible(false);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		
		return chart;
	}

	/**
	 * Starting point for the demonstration application.
	 * @param args ignored.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFreeChart chart = createChart(createDataset());
		
		Display display = new Display();
		
		Shell shell = new Shell(display);
		
		shell.setSize(600, 400);
		
		shell.setLayout(new FillLayout());
		
		shell.setText("Text for jfreechart running with SWT");
		
		final ChartComposite frame = new ChartComposite(shell, SWT.NONE, chart, true);
		
		//frame.setDisplayToolTips(false);
		frame.pack();
		shell.open();
		
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		

	}

}
