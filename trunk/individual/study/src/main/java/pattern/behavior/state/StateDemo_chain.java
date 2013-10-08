package pattern.behavior.state;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CeilingFanPullChain
{
    private State m_current_state;

    public CeilingFanPullChain()
    {
        m_current_state = new Off();
    }
    public void set_state(State s)
    {
        m_current_state = s;
    }
    public void pull()
    {
        m_current_state.pull(this);
    }
}

interface State
{
  void pull(CeilingFanPullChain wrapper);
  State getNextState();
}

class Off implements State
{
    public void pull(CeilingFanPullChain wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   low speed");
    }

	public State getNextState() {
		return new Low();
	}
}

final class Low implements State
{
    public void pull(CeilingFanPullChain wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   medium speed");
    }
	public State getNextState() {
		return new Medium();
	}
}

final class Medium implements State
{
    public void pull(CeilingFanPullChain wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   high speed");
    }
	public State getNextState() {
		return new High();
	}
}

final class High implements State
{
    public void pull(CeilingFanPullChain wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   turning off");
    }
	public State getNextState() {
		return new Off();
	}
}

public class StateDemo_chain
{
    public static void main(String[] args)
    {
        CeilingFanPullChain chain = new CeilingFanPullChain();
        while (true)
        {
            System.out.print("Press ");
            get_line();
            chain.pull();
        }
    }
    static String get_line()
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in))
          ;
        String line = null;
        try
        {
            line = in.readLine();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return line;
    }
}

