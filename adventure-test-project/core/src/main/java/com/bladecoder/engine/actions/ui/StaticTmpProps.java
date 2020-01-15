/*******************************************************************************
 * Copyright 2014 Rafael Garcia Moreno.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.bladecoder.engine.actions.ui;

import java.util.Map.Entry;
import java.util.Properties;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;

@ActionDescription("Temporal properties persistence between chapters.")
public class StaticTmpProps implements Action {
	private enum Operation {
		RESET, SAVE, LOAD
	};

	private static final Properties tmp = new Properties();

	@ActionProperty(required = true)
	@ActionPropertyDescription("The system.")
	private Operation operation = Operation.RESET;

	@Override
	public void init(World w) {
	}

	@Override
	public boolean run(VerbRunner cb) {

		UI ui = BladeEngine.getAppUI();
		World world = ui.getWorld();

		if (operation == Operation.RESET) {
			tmp.clear();
		} else if (operation == Operation.SAVE) {
			tmp.clear();
			tmp.putAll(world.getCustomProperties());
		} else if (operation == Operation.LOAD) {
			for (Entry<Object, Object> entry : tmp.entrySet()) {
				world.getCustomProperties().put((String) entry.getKey(), (String) entry.getValue());
			}
		}

		return false;
	}
}
