/*******************************************************************************
 * Copyright (c) 2007 Alphonse Van Assche.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alphonse Van Assche - initial API and implementation
 *******************************************************************************/
package org.eclipse.linuxtools.rpm.rpmlint.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.linuxtools.rpm.rpmlint.Activator;
import org.eclipse.linuxtools.rpm.rpmlint.parser.RpmlintParser;
import org.eclipse.linuxtools.rpm.ui.editor.markers.SpecfileErrorHandler;

public class RpmlintMarkerRemoveVisitor implements IResourceVisitor {

	public boolean visit(IResource resource) throws CoreException {
		if (Activator.SPECFILE_EXTENSION.equals(resource.getFileExtension())) {
			RpmlintParser.getInstance().deleteMarkers(resource);
			// remove internal marks
			resource.deleteMarkers(SpecfileErrorHandler.SPECFILE_ERROR_MARKER_ID, false, IResource.DEPTH_ZERO);
		}
		return true;
	}
}
