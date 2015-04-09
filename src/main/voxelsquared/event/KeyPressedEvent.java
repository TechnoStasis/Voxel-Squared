package voxelsquared.event;

import modapi.event.IEvent;

public class KeyPressedEvent implements IEvent {

	int key;
	
	public KeyPressedEvent(int keyID)
	{
		key = keyID;
	}
	
	public int getKeyID()
	{
		return key;
	}
	
}
