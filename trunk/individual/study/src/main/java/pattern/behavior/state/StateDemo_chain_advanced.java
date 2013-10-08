package pattern.behavior.state;


class CeilingFanPullChain2
{
    private State2 m_current_state;

    public CeilingFanPullChain2()
    {
        m_current_state = new Off2();
    }
    public void set_state(State2 s)
    {
        m_current_state = s;
    }
    public void pull()
    {
        m_current_state.pull(this);
    }
}

interface State2
{
  void pull(CeilingFanPullChain2 wrapper);
  State2 getNextState();
}

final class End implements State2
{
    public void pull(CeilingFanPullChain2 wrapper)
    {
        System.out.println("Ended");
    }

	public State2 getNextState() {
		return new Low2();
	}
}
final class Off2 implements State2
{
    public void pull(CeilingFanPullChain2 wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   low speed");
        wrapper.pull();
    }

	public State2 getNextState() {
		return new Low2();
	}
}

final class Low2 implements State2
{
    public void pull(CeilingFanPullChain2 wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   medium speed");
        wrapper.pull();
    }
	public State2 getNextState() {
		return new Medium2();
	}
}

final class Medium2 implements State2
{
    public void pull(CeilingFanPullChain2 wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   high speed");
        wrapper.pull();
    }
	public State2 getNextState() {
		return new High2();
	}
}

final class High2 implements State2
{
    public void pull(CeilingFanPullChain2 wrapper)
    {
        wrapper.set_state(getNextState());
        System.out.println("   turning off");
        wrapper.pull();
    }
	public State2 getNextState() {
		return new End();
	}
}

public class StateDemo_chain_advanced
{
    public static void main(String[] args)
    {
        CeilingFanPullChain2 chain = new CeilingFanPullChain2();
        chain.pull();
    }
}


