/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.library.items;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.smarthome.core.library.CoreItemFactory;
import org.eclipse.smarthome.core.library.types.DateTimeType;
import org.eclipse.smarthome.core.library.types.RefreshType;
import org.eclipse.smarthome.core.library.types.StringType;
import org.eclipse.smarthome.core.items.GenericItem;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;
import org.eclipse.smarthome.core.types.TypeParser;
import org.eclipse.smarthome.core.types.UnDefType;

/**
 * A StringItem can be used for any kind of string to either send or receive
 * from a device.
 * 
 * @author Kai Kreuzer - Initial contribution and API
 *
 */
public class StringItem extends GenericItem {
	
	private static List<Class<? extends State>> acceptedDataTypes = new ArrayList<Class<? extends State>>();
	private static List<Class<? extends Command>> acceptedCommandTypes = new ArrayList<Class<? extends Command>>();

	static {
		acceptedDataTypes.add(StringType.class);
		acceptedDataTypes.add((DateTimeType.class));
		acceptedDataTypes.add(UnDefType.class);

		acceptedCommandTypes.add(RefreshType.class);
		acceptedCommandTypes.add(StringType.class);		
	}
	
	public StringItem(String name) {
		super(CoreItemFactory.STRING, name);
	}

	public List<Class<? extends State>> getAcceptedDataTypes() {
		return acceptedDataTypes;
	}

	public List<Class<? extends Command>> getAcceptedCommandTypes() {
		return acceptedCommandTypes;
	}
	
	@Override
	public State getStateAs(Class<? extends State> typeClass) {
		ArrayList<Class<? extends State>> list = new ArrayList<Class<? extends State>>();
		list.add(typeClass);
		State convertedState = TypeParser.parseState(list, state.toString());
		if(convertedState!=null) {
			return convertedState;
		} else {
			return super.getStateAs(typeClass);
		}
	}
}