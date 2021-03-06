/*******************************************************************************
 * Copyright (c) 2015-2016 Open Analytics NV and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.linuxtools.internal.docker.editor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.linuxtools.internal.docker.editor.syntax.SyntaxProblemReporter;
import org.eclipse.ui.editors.text.TextEditor;

public class DockerEditor extends TextEditor {

	public DockerEditor() {
		super();
		setSourceViewerConfiguration(new DockerConfiguration(this));
		setDocumentProvider(new DockerDocumentProvider());
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		try {
			IResource resource = getEditorInput().getAdapter(IResource.class);
			IDocument document = getDocumentProvider().getDocument(getEditorInput());
			new SyntaxProblemReporter().checkAndApply(document, 0, document.getLength(), resource);
		} catch (CoreException | BadLocationException e) {
			Activator.log(IStatus.ERROR, "Failed to perform syntax check", e);
		}
		super.doSave(progressMonitor);
	}
}
