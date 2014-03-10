//
//consisting of a source of command objects and a series of processing objects. 
//Each processing object contains a set of logic that describes the types of command objects that it can handle, and how to pass off those that it cannot to the next processing object in the chain. 
//A mechanism also exists for adding new processing objects to the end of this chain.
package pattern.behavior;

abstract class Logger 
{
    public static int ERR3 = 3;
    public static int NOTICE5 = 5;
    public static int DEBUG7 = 7;
    protected int mask;
 
    // The next element in the chain of responsibility
    protected Logger next;
    public Logger setNext( Logger l)
    {
        next = l;
        return l;
    }
 
    public void message( String msg, int priority )
    {
        if ( priority <= mask ) 
        {
            writeMessage( msg );
        }
        if ( next != null )
        {
            next.message( msg, priority );
        }
    }
 
    abstract protected void writeMessage( String msg );
 
}
class EmailLogger extends Logger 
{
 
    public EmailLogger( int mask ) { this.mask = mask; }
 
    protected void writeMessage( String msg )
    {
        System.out.println( "Sending via email: " + msg );
    }
}
class StderrLogger extends Logger 
{
 
    public StderrLogger( int mask ) { this.mask = mask; }
 
    protected void writeMessage( String msg )
    {
        System.err.println( "Sending to stderr: " + msg );
    }
}
class StdoutLogger extends Logger 
{
 
    public StdoutLogger( int mask ) { this.mask = mask; }
 
    protected void writeMessage( String msg )
    {
        System.out.println( "Writing to stdout: " + msg );
    }
}
public class ChainOfResponsibility {
    public static void main( String[] args )
    {
        // Build the chain of responsibility
        Logger l,l1;
        l1 = l = new StdoutLogger( Logger.DEBUG7 );
        l1 = l1.setNext(new EmailLogger( Logger.NOTICE5 ));
        l1 = l1.setNext(new StderrLogger( Logger.ERR3 ));
 
        // Handled by StdoutLogger
        l.message( "Entering function y.", Logger.DEBUG7 );
 
        // Handled by StdoutLogger and EmailLogger
        l.message( "Step1 completed.", Logger.NOTICE5 );
 
        // Handled by all three loggers
        l.message( "An error has occurred.", Logger.ERR3 );
    }

}
