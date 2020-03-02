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
package com.bladecoder.engine.actions;

import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.Config;
import com.bladecoder.engine.util.EngineLogger;

@ActionDescription("Execute actions inside the If/EndIf if the game property is an integer number and meets the condition.")
public class IfIntegerPropertyAction extends AbstractIfAction {

	public enum ComparisonType {
		EQUALS, GREATER, LESS, GREATER_EQUALS, LESS_EQUALS
	}

	@ActionProperty(required = true, defaultValue = "EQUALS")
	@ActionPropertyDescription("The comparison type")
	private ComparisonType comparison;

	@ActionProperty(required = true)
	@ActionPropertyDescription("The property name")
	private String prop;

	@ActionProperty(required = true, defaultValue = "0")
	@ActionPropertyDescription("The property value")
	private String value;

	private World w;

	@Override
	public void init(World w) {
		this.w = w;
	}

	@Override
	public boolean run(VerbRunner cb) {
		String valDest = w.getCustomProperty(prop);
		int currentVal = 0;
		int iValue = 0;

		if (valDest == null)
			valDest = Config.getInstance().getProperty(prop, null);

		if (valDest == null)
			valDest = Config.getInstance().getPref(prop, null);

		if (valDest != null) {
			try {
				currentVal = Integer.parseInt(valDest);
				iValue = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				EngineLogger.error("Property has not an integer value: " + prop + "=" + valDest);
				return false;
			}
		}

		if ((comparison == ComparisonType.EQUALS && iValue != currentVal)
				|| (comparison == ComparisonType.GREATER && iValue <= currentVal)
				|| (comparison == ComparisonType.LESS && iValue >= currentVal)
				|| (comparison == ComparisonType.GREATER_EQUALS && iValue < currentVal)
				|| (comparison == ComparisonType.LESS_EQUALS && iValue > currentVal)) {
			gotoElse(cb);
		}

		return false;
	}

}
