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

import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.Config;

@ActionDescription("Sets a game configuration property.")
public class UISetConfigProperty implements Action {

	@ActionProperty(required = true)
	@ActionPropertyDescription("Property name")
	private String name;

	@ActionProperty
	@ActionPropertyDescription("Property value")
	private String value;

	@Override
	public void init(World w) {
	}

	@Override
	public boolean run(VerbRunner cb) {
		Config.getInstance().setPref(name, value);
		Config.getInstance().savePrefs();

		return false;
	}
}
