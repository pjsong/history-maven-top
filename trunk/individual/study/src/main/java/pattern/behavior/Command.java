package pattern.behavior;


/*the CommandInt interface*/
interface CommandInt {
    void execute();
}
 
/*the CommandInt for turning on the light*/
class FlipUpCommand implements CommandInt {
 
   private Light theLight;
 
   public FlipUpCommand(Light light) {
        this.theLight=light;
   }
 
   public void execute(){
      theLight.turnOn();
   }
}
 
/*the CommandInt for turning off the light*/
class FlipDownCommand implements CommandInt {
 
   private Light theLight;
 
   public FlipDownCommand(Light light) {
        this.theLight=light;
   }
 
   public void execute() {
      theLight.turnOff();
   }
}
/*the Invoker class*/
class Switch {
 
    private CommandInt flipUpCommand;
    private CommandInt flipDownCommand;
 
    public Switch(CommandInt flipUpCmd, CommandInt flipDownCmd) {
         this.flipUpCommand = flipUpCmd;
         this.flipDownCommand = flipDownCmd;
    }
 
    public void flipUp() {
         flipUpCommand.execute();
    }
 
    public void flipDown() {
         flipDownCommand.execute();
    }
}
 
/*Receiver class*/
class Light {
 
     public Light() {  }
 
     public void turnOn() {
        System.out.println("The light is on");
     }
 
     public void turnOff() {
        System.out.println("The light is off");
     }
}

 
/*The test class or client*/
public class Command {
 
   public static void main(String[] args) {
       Light lamp = new Light();
       CommandInt switchUp = new FlipUpCommand(lamp);
       CommandInt switchDown = new FlipDownCommand(lamp);
 
       // See criticism of this model above:
       // The switch itself should not be aware of an lamp details (switchUp, switchDown) either directly or indirectly
       Switch s = new Switch(switchUp,switchDown);
 
       try {
           if (args[0].equalsIgnoreCase("ON")) {
                s.flipUp();
           } else if (args[0].equalsIgnoreCase("OFF")) {
               s.flipDown();
           } else {
               System.out.println("Argument \"ON\" or \"OFF\" is required.");
           }
       } catch (Exception e){
           System.out.println("Arguments required.");
       }
   }
}
