package dk.via.and1.and1_garage_managing_app.data.garage;

import java.util.Date;

public class Garage {
    private Date gateCloseTime;
    private Date lightOffTime;
    private String city, postCode, street, streetNo;
    private int gateTimer;
    private int lightTimer;

    public Garage()
    {
    }

    public Garage(Date gateCloseTime, Date lightOffTime, String city, String postCode, String street, String streetNo, int gateTimer, int lightTimer)
    {
        this.gateCloseTime = gateCloseTime;
        this.lightOffTime = lightOffTime;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.streetNo = streetNo;
        this.gateTimer = gateTimer;
        this.lightTimer = lightTimer;
    }

    public Date getGateCloseTime()
    {
        return gateCloseTime;
    }

    public void setGateCloseTime(Date gateCloseTime)
    {
        this.gateCloseTime = gateCloseTime;
    }

    public Date getLightOffTime()
    {
        return lightOffTime;
    }

    public void setLightOffTime(Date lightOffTime)
    {
        this.lightOffTime = lightOffTime;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getStreetNo()
    {
        return streetNo;
    }

    public void setStreetNo(String streetNo)
    {
        this.streetNo = streetNo;
    }

    public int getGateTimer()
    {
        return gateTimer;
    }

    public void setGateTimer(int gateTimer)
    {
        this.gateTimer = gateTimer;
    }

    public int getLightTimer()
    {
        return lightTimer;
    }

    public void setLightTimer(int lightTimer)
    {
        this.lightTimer = lightTimer;
    }
}
