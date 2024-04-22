package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import beans.Thermometer;
import beans.User;

import org.jfree.chart.*;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

import data.ThermDataService;

@ManagedBean(name="ThermometerController")
@SessionScoped
public class ThermometerController {
	
	private Thermometer therm;
	private ThermDataService dao;
	
	public ThermometerController() {
		dao = new ThermDataService();
	}
	
	public void startTherm(Thermometer thermometer) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
		int userId = (Integer) faceletContext.getAttribute("userId");
		this.therm = thermometer;
		therm.setActive(true);
		therm.setUserId(userId);
		try {
			dao.create(this.therm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		externalContext.redirect("ThermDashboard.xhtml");
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
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (notified) {
            System.out.println("BEEP BEEP BEEP");
        }
        
        HashMap<String, Double> temps = new HashMap<String, Double>();
	}
	
	public Thermometer getThermometer(int userId) {
		this.therm = dao.getUserThermometer(userId);
		return this.therm;
	}
	
}
