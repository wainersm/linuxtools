/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Jeff Briggs, Henry Hughes, Ryan Morse
 *******************************************************************************/

package org.eclipse.linuxtools.systemtap.ui.ide.actions;

import java.util.ArrayList;

import org.eclipse.linuxtools.internal.systemtap.ui.ide.preferences.IDEPreferenceConstants;
import org.eclipse.linuxtools.internal.systemtap.ui.ide.uistructures.StapSettingsDialog;
import org.eclipse.ui.PlatformUI;


/**
 * This class is a descendant of <code>RunScriptAction</code> that allows for additional arguments, specified
 * by the user, to be passed to stap. Its behavior is very similar to the <code>RunScriptAction</code> action.
 * @author Ryan Morse
 * @see org.eclipse.linuxtools.systemtap.ui.ide.actions.RunScriptHandler
 * @since 2.0
 */
public class RunScriptOptionsHandler extends RunScriptHandler {
	public RunScriptOptionsHandler() {
		super();
	}

	/**
	 * This method executes the same code as the <code>buildStandardScript</code> with one change,
	 * being that instead of calling the <code>getImportedTapsets</code> method from the parent class, it
	 * calls the <code>getCommandLineOptions</code> method, which will contain all the information that the
	 * tapset method contained, as well as whatever optional arguments the user specifies.
	 * @return Command line arguments suitable to pass to <code>Runtime.exec</code> in order to run the script as requested
	 * @see RunScriptHandler#buildStandardScript()
	 */
	protected String[] buildOptionsScript() {
		ArrayList<String> cmdList = new ArrayList<String>();
		String[] script;

		getImportedTapsets(cmdList);

		if(isGuru())
			cmdList.add("-g"); //$NON-NLS-1$

		getCommandLineOptions(cmdList);

		script = finalizeScript(cmdList);

		return script;
	}

	/**
	 * This method prompts the user to select optional command line arguments to use when running this
	 * script, and adds them to the <code>ArrayList</code> passed in.
	 * @param cmdList The <code>ArrayList</code> to add the arguments to.
	 */
	protected void getCommandLineOptions(ArrayList<String> cmdList) {
		StapSettingsDialog ssd = new StapSettingsDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		ssd.open();

		boolean[] cmdOpts = ssd.getStapOpts();
		String[] cmdOptVals = ssd.getStapOptVals();

		if((null != cmdOpts) && (null != cmdOptVals)) {
			int i;
			//Get commandline check options
			for(i=0; i<cmdOpts.length; i++) {
				if(cmdOpts[i])
					cmdList.add(IDEPreferenceConstants.P_STAP[i][0]);
			}

			//Get rest of commandline options
			for(i=0; i<cmdOptVals.length; i++) {
				if(null != cmdOptVals[i] && cmdOptVals[i].trim().length() > 0) {
					if("-v".equals(IDEPreferenceConstants.P_STAP[i+cmdOpts.length][0])) { //$NON-NLS-1$
						cmdList.add("-" + cmdOptVals[i]); //$NON-NLS-1$
					} else if("-p NUM".equals(IDEPreferenceConstants.P_STAP[i+cmdOpts.length][0])) { //$NON-NLS-1$
						cmdList.add("-p" + cmdOptVals[i]); //$NON-NLS-1$
					} else {
						cmdList.add(IDEPreferenceConstants.P_STAP[i+cmdOpts.length][0].substring(0,2));

						cmdList.add(cmdOptVals[i-cmdOpts.length]);
					}
				}
			}
		} else
			continueRun = false;
	}
}