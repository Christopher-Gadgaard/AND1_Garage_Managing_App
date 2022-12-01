package dk.via.and1.and1_garage_managing_app.data.garage;

import java.util.Date;

public class GarageAction
{
    private String userId;
    private Date date;
    private GarageActions garageActions;


    public GarageAction(String userId, Date date, GarageActions garageActions)
    {
        this.userId = userId;
        this.date = date;
        this.garageActions = garageActions;
    }

    GarageAction()
    {}

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public GarageActions getGarageActions()
    {
        return garageActions;
    }

    public void setGarageActions(GarageActions garageActions)
    {
        this.garageActions = garageActions;
    }
}
