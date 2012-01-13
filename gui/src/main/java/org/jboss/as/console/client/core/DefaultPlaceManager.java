/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.core;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import org.jboss.as.console.client.core.message.MessageCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heiko Braun
 * @date 2/4/11
 */
public class DefaultPlaceManager extends PlaceManagerImpl {

    private MessageCenter messageCenter;
    private BootstrapContext bootstrap;

    @Inject
    public DefaultPlaceManager(
            EventBus eventBus,
            TokenFormatter tokenFormatter, MessageCenter messageCenter, BootstrapContext bootstrap ) {
        super(eventBus, tokenFormatter);
        this.messageCenter = messageCenter;
        this.bootstrap = bootstrap;
    }

    @Override
    public void revealErrorPlace(String invalidHistoryToken) {

        Log.debug("Discard \"" + invalidHistoryToken + "\". Fallback to default place");
        revealDefaultPlace();
    }

    public void revealDefaultPlace() {

        List<PlaceRequest> places = new ArrayList<PlaceRequest>();
        places.add(bootstrap.getDefaultPlace());

        revealPlaceHierarchy(places);
    }

    @Override
    public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {

        // TODO: beware of XSS
        revealPlace( new PlaceRequest(NameTokens.signInPage) );
    }
}
