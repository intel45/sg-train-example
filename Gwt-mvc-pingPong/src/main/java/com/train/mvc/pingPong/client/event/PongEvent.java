package com.train.mvc.pingPong.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by IntelliJ IDEA.
 * Author: Hu jing ling
 * Date: 8/12/11
 * Description: Pong Event
 */
public class PongEvent extends GwtEvent<PongEventHandler>{

    public static Type<PongEventHandler> TYPE = new Type<PongEventHandler>();

    @Override
    public Type<PongEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PongEventHandler handler) {
        handler.onEvent(this);
    }
}
