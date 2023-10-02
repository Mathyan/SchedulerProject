package com.mathyan.controller;

import java.util.EventListener;

/**
 * This interface represents a listener for update events.
 * <p>
 * The update method is called when the schedule table needs to be updated.
 */
public interface UpdateEventListener extends EventListener{
        /**
        * This method is called when the schedule table needs to be updated.
        *
        * @param e the update event
        */
        public void update(UpdateEvent e);
}
