package mod.event;

import java.util.ArrayList;
import java.util.HashMap;

import modapi.event.IEvent;
import modapi.event.listener.IEventListener;
import voxelengine.log.LogManager;
import voxelengine.log.Logger;

public class EventSpeaker {

	public static final EventSpeaker instance = new EventSpeaker();

	private Logger log = LogManager.requestLogger("Event Management System");

	// What a mouthful.
	private HashMap<Class<? extends IEvent>, ArrayList<IEventListener>> listeners = new HashMap<Class<? extends IEvent>, ArrayList<IEventListener>>();

	public void registerListener(IEventListener listener, Class<? extends IEvent> eventClass) {
			ArrayList<IEventListener> list = listeners.get(eventClass);

			if (list == null) {
				list = new ArrayList<IEventListener>();
			}

			list.add(listener);
			
			listeners.put(eventClass, list);
			
			System.out.println(listeners.get(eventClass));
	}

	public void fireEvent(IEvent e) {
		ArrayList<IEventListener> list = listeners.get(e.getClass());

		if (list == null) {
			log.info("No listeners found for: " + e.getClass());
			return;
		}

		for (IEventListener listener : list) {
			listener.listenEvent(e);
		}

	}

}
