/*******************************************************************************
 * Copyright (c) 2015, 2016 Red Hat.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial Contribution
 *******************************************************************************/
package org.eclipse.linuxtools.docker.ui.launch;

import org.eclipse.linuxtools.docker.core.IDockerContainerInfo;

public interface IContainerLaunchListener extends IRunConsoleListener {

	void done(); // called when container finishes

	void containerInfo(IDockerContainerInfo info);

}
