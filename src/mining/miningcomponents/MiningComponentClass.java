package mining.miningcomponents;

public abstract class MiningComponentClass implements MiningComponent {

	@Override
	public abstract byte getComponentID();
	@Override
	public abstract void processData(String data);



}
