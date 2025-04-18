/*
 * MIT LICENSE
 * Copyright 2000-2023 Simplified Logic, Inc
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions: The above copyright 
 * notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", 
 * WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.simplifiedlogic.nitro.jlink.calls.argument;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.ArgValueType;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcArgument.ArgValue;
import com.simplifiedlogic.nitro.jlink.intf.DebugLogging;
import com.simplifiedlogic.nitro.jlink.impl.NitroConstants;

/**
 * Wrapper for JLink's com.ptc.pfc.pfcArgument.ArgValue
 * @author Adam Andrews
 *
 */
public class CallArgValue {

	private ArgValue value;
	
	public CallArgValue(ArgValue value) {
		this.value = value;
	}
	
	public static CallArgValue createASCIIStringArgValue(String value) throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("pfcArgument,CreateASCIIStringArgValue", 0, NitroConstants.DEBUG_JLINK_KEY);
		ArgValue val = pfcArgument.CreateASCIIStringArgValue(value);
		if (val==null)
			return null;
		return new CallArgValue(val);
	}

	public int getArgValueType() throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("ArgValue,Getdiscr", 0, NitroConstants.DEBUG_JLINK_KEY);
        ArgValueType type = value.Getdiscr();
        return type.getValue();
	}
	
	public int getIntValue() throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("ArgValue,GetIntValue", 0, NitroConstants.DEBUG_JLINK_KEY);
		return value.GetIntValue();
	}

	public double getDoubleValue() throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("ArgValue,GetDoubleValue", 0, NitroConstants.DEBUG_JLINK_KEY);
		return value.GetDoubleValue();
	}

	public boolean getBoolValue() throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("ArgValue,GetBoolValue", 0, NitroConstants.DEBUG_JLINK_KEY);
		return value.GetBoolValue();
	}

	public String getStringValue() throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("ArgValue,GetStringValue", 0, NitroConstants.DEBUG_JLINK_KEY);
		return value.GetStringValue();
	}

	public String getASCIIStringValue() throws jxthrowable {
        if (NitroConstants.DEBUG_JLINK) DebugLogging.sendTimerMessage("ArgValue,GetASCIIStringValue", 0, NitroConstants.DEBUG_JLINK_KEY);
		return value.GetASCIIStringValue();
	}

	public ArgValue getValue() {
		return value;
	}
}
