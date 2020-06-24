package challenge;

public abstract class Utils {
    public static final String INVALID_COLOR = "Invalid color ";
    public static final String INVALID_PLATE = "Invalid plate ";
    public static final String INVALID_POINTS = "Invalid points ";
    public static final String INVALID_LICENSE = "Invalid license ";
    public static final String INVALID_NAME = "Invalid name ";
    public static final String INVALID_AGE = "Invalid age ";
    public static final String NO_DRIVER = "No driver ";
    public static final String PARKING_FULL = "Parking is full";
    public static final String UNDER_AGE_DRIVER = "Under age driver ";

    public static boolean validColor(Cor cor){
        for (Cor c : Cor.values()){
            if (c.name().equals(cor.name())){
                return true;
            }
        }
        return false;
    }

    public static boolean validAge(int age){
        if(age >= 0 && age <= 130){
            return true;
        }else {
            return false;
        }
    }

    public static boolean validPoints(int points){
        if (points >= 0){
            return true;
        }else {
            return false;
        }
    }

    public static boolean validString(String string){
        return string != null && !string.isEmpty();
    }
}
