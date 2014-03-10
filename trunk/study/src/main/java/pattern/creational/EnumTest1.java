package pattern.creational;

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY 
}
public class EnumTest1 {
    Day day;
    public EnumTest1(Day day) {
        this.day = day;
    }
    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY:
                System.out.println("Mondays are bad.");
                break;
                    
            case SATURDAY: case SUNDAY:
                System.out.println("Weekends are best.");
                break;
                        
            default:
                break;
        }
    }
    
    public static void main(String[] args) {
        EnumTest1 firstDay = new EnumTest1(Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest1 thirdDay = new EnumTest1(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
    }
}

