package dk.via.and1.and1_garage_managing_app.data.user;

public class User
{
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String licensePlate;
    private Boolean isAdmin;


    public User()
    {
    }

    public User(String firstName, String lastName, String phoneNo, String email,
                String licensePlate, Boolean isAdmin)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.licensePlate = licensePlate;
        this.isAdmin = isAdmin;
    }


    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public Boolean getIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin)
    {
        isAdmin = admin;
    }
}
