package com.events;

import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public class TradeCacheListener implements MapListener {

    @Override
    public void entryInserted(MapEvent mapEvent) {
        System.out.println("Entry inserted where oldValue was : " + mapEvent.getOldValue()+" and new Value is : "+mapEvent.getNewValue());
    }

    @Override
    public void entryUpdated(MapEvent mapEvent) {
        System.out.println("Entry updated where oldValue was : " + mapEvent.getOldValue()+" and new Value is : "+mapEvent.getNewValue());
    }

    @Override
    public void entryDeleted(MapEvent mapEvent) {
        System.out.println("Entry deleted where oldValue was : " + mapEvent.getOldValue());
    }
}
