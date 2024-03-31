package controllers;

import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.bean.*;

import beans.Thermometer;

import org.jfree.chart.*;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

import data.ThermDataService;

@ManagedBean(name="ThermometerController")
@ViewScoped
public class ThermometerController {
	
	private Thermometer therm;
	private ThermDataService dao;
	
	public String startTherm(Thermometer thermometer) {
		dao = new ThermDataService();
		this.therm = thermometer;
		therm.setActive(true);
		therm.setScale("F");
		therm.setUserId(1);
		try {
			dao.create(this.therm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Therm: " + therm.getName() + " " + therm.getSetTemp());
		return "ThermDashboard.xhtml";
	}
	
	public void onUpdate() {
		//HashMap<Second, Double> values = new HashMap<Second, Double>();
		boolean notified = therm.notifyTemp();
        while(!notified) {
            try {
            	//Second s = new Second();
                double rand = (double) (Math.random() * ((3 - 1) + 1));
                therm.addDegrees(Math.round(rand));
                System.out.println("Temp is " + therm.getTemp());
                notified = therm.notifyTemp();
                if (notified)
                    break;
                //values.put(s, therm.getTemp());
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (notified) {
            System.out.println("BEEP BEEP BEEP");
//            final ChartController thermChart = new ChartController("Thermometer Data", values);         
//    		thermChart.pack();        
//    		RefineryUtilities.positionFrameRandomly(thermChart);         
//    	    thermChart.setVisible(true);
        }
        
        HashMap<String, Double> temps = new HashMap<String, Double>();
	}
	
}
