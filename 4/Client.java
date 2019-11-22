public class Client {
    private String passport;
    private  String name;
    public Client (String passport, String name) {
        if (passport.length() != 6) {
            throw new IllegalArgumentException("Invalid passport: " + passport);
        }        
        this.passport = passport;
        setName(name);        
    }   
    public String getPassport() { //getter
        return /*this.*/passport;
    }
    public String getName() {
        return name;
    }
    private void setName(String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        this.name = name;        
    }
    private boolean setPassport(String passport) { //setter
        if (passport.length() == 6) {
            this.passport = passport;   
            return true;
        }
        return false;
    }
}
