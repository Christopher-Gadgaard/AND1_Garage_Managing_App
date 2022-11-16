package dk.via.and1.and1_garage_managing_app.data.garage;

import java.util.Date;

import dk.via.and1.and1_garage_managing_app.data.user.UserInfo;

public class GarageEntry
{
    private UserInfo userInfo;
    private Date date;
    private GarageAction action;

    public GarageEntry(UserInfo userInfo, Date date, GarageAction action)
    {
        this.userInfo = userInfo;
        this.date = date;
        this.action = action;
    }

    public UserInfo getUser()
    {
        return userInfo;
    }

    public void setUser(UserInfo userInfo)
    {
        this.userInfo = userInfo;
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
