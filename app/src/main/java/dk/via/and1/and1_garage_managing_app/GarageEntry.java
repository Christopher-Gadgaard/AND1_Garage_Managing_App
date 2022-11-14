package dk.via.and1.and1_garage_managing_app;

import java.util.Date;

public class GarageEntry
{
    private User user;
    private Date date;
    private GarageAction action;

    public GarageEntry(User user, Date date, GarageAction action)
    {
        this.user = user;
        this.date = date;
        this.action = action;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public GarageAction getAction()
    {
        return action;
    }

    public void setAction(GarageAction action)
    {
        this.action = action;
    }
}
