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
package com.simplifiedlogic.nitro.rpc;

import com.ptc.pfc.pfcExceptions.XToolkitError;

/**
 * Exception extension specifically for errors generated by JShell Native Layer
 * 
 * @author Adam Andrews
 */
public class JCToolkitException extends JCException {

	private static final String TOOLKIT_PREFIX = "pfcExceptions::";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String toolkitName;
	
	/**
	 * 
	 */
	public JCToolkitException(int errorCode) {
		super();
		setErrorCode(errorCode);
	}

	/**
	 * @param message
	 */
	public JCToolkitException(String message, int errorCode) {
		super(message);
		if (message.startsWith(TOOLKIT_PREFIX))
			this.toolkitName = message.substring(TOOLKIT_PREFIX.length()).trim();
		setErrorCode(errorCode);
	}

	/**
	 * @param cause
	 */
	public JCToolkitException(Throwable cause, int errorCode) {
		super(cause);
		setErrorCode(errorCode);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JCToolkitException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		if (message.startsWith(TOOLKIT_PREFIX))
			this.toolkitName = message.substring(TOOLKIT_PREFIX.length()).trim();
		setErrorCode(errorCode);
	}

	public boolean isInstanceOf(Class<? extends XToolkitError> clazz) {
		if (this.toolkitName==null)
			return false;
		if (clazz.getSimpleName().equals(this.toolkitName))
			return true;
		return false;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getToolkitName() {
		return toolkitName;
	}

	public void setToolkitName(String toolkitName) {
		this.toolkitName = toolkitName;
	}

}
