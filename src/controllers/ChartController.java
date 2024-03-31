package controllers;

//import java.util.HashMap;
//import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import org.jfree.chart.*;
//import org.jfree.data.general.SeriesException;
//import org.jfree.data.time.*;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.ui.ApplicationFrame;

@ManagedBean(name="ChartController")
@ViewScoped
public class ChartController /*extends ApplicationFrame*/ {

//	public ChartController(final String title, HashMap<Second, Double> values) {
//		super(title);
//	    final XYDataset dataset = newDataset(values);
//	    final JFreeChart chart = createChart(dataset);         
//	    final ChartPanel chartPanel = new ChartPanel( chart );         
//	    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
//	    chartPanel.setMouseZoomable( true , false );         
//	    setContentPane( chartPanel );
//	}
//	
//	private XYDataset dataset() {
//		final TimeSeries series = new TimeSeries("Temperature");         
//	    Second current = new Second();
//	    
//	    double value = 100.0;         
//	      
//	      for (int i = 0; i < 4000; i++) {
//	         
//	         try {
//	            value = value + Math.random( ) - 0.5;                 
//	            series.add(current, new Double( value ) );                 
//	            current = ( Second ) current.next( ); 
//	         } catch ( SeriesException e ) {
//	            System.err.println("Error adding to series");
//	         }
//	      }
//
//	      return new TimeSeriesCollection(series);
//	}
//	
//	private XYDataset newDataset(HashMap<Second, Double> values) {
//		final TimeSeries series = new TimeSeries("Temperature");
//		
//		for (Map.Entry<Second, Double> entry : values.entrySet()) {
//			Second s = entry.getKey();
//			Double v = entry.getValue();
//			series.add(s, v);
//		}
//		
//		return new TimeSeriesCollection(series);
//	}
//	
//	private JFreeChart createChart( final XYDataset dataset ) {
//	      return ChartFactory.createTimeSeriesChart(             
//	         "Temperature", 
//	         "Seconds",              
//	         "Value",              
//	         dataset,             
//	         false,              
//	         false,              
//	         false);
//	   }
}
