package beans;

import javax.faces.bean.*;
import javax.validation.constraints.*;

@ManagedBean
@RequestScoped
public class Thermometer {

	int thermometerId;
	@NotNull(message="Thermometer needs a name")
	String name;
	@NotNull(message="Please set a temperature")
	@Max(400)
    double setTemp;
	double temp;
	int userId;
	boolean active;
	String scale;

	public Thermometer() {}
	
	public Thermometer(String name, double setTemp, int userId, String scale) {
        this.name = name;
        this.setTemp = temp;
        this.userId = userId;
        this.scale = scale;
        this.thermometerId = 0;
    }
	
	public boolean notifyTemp() {
        if (this.temp == this.setTemp)
            return true;
        else
            return false;
    }

    public double addDegrees(double temp) {
        this.temp += temp;
        return this.temp;
    }
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSetTemp() {
		return setTemp;
	}
	public void setSetTemp(double setTemp) {
		this.setTemp = setTemp;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}

	public int getId() {
		return thermometerId;
	}

	public void setId(int thermometerId) {
		this.thermometerId = thermometerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	
	
}
