/***********************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/
package org.eclipse.birt.chart.ui.swt.composites;

import org.eclipse.birt.chart.model.attribute.FormatSpecifier;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Actuate Corporation
 *  
 */
public class FormatSpecifierDialog implements SelectionListener
{
    private transient Shell shell = null;

    private transient FormatSpecifierComposite editor = null;

    private transient Button btnAccept = null;

    private transient Button btnCancel = null;

    private transient FormatSpecifier formatspecifier = null;

    private transient FormatSpecifier fsBackup = null;

    /**
     *  
     */
    public FormatSpecifierDialog(FormatSpecifier formatspecifier)
    {
        super();
        this.formatspecifier = formatspecifier;
        fsBackup = (FormatSpecifier) EcoreUtil.copy(formatspecifier);

        shell = new Shell(Display.getCurrent(), SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        shell.setLayout(new FillLayout());
        placeComponents();
        shell.setText("Format Specifier Dialog:");
        shell.setSize(332, 255);
        shell.setLocation(Display.getCurrent().getClientArea().width / 2 - (shell.getSize().x / 2), Display
            .getCurrent().getClientArea().height
            / 2 - (shell.getSize().y / 2));
        shell.open();
        while (!shell.isDisposed())
        {
            if (!shell.getDisplay().readAndDispatch())
            {
                shell.getDisplay().sleep();
            }
        }
    }

    private void placeComponents()
    {
        GridLayout glContent = new GridLayout();
        glContent.marginHeight = 7;
        glContent.marginWidth = 7;
        glContent.verticalSpacing = 5;

        Composite cmpContent = new Composite(shell, SWT.NONE);
        cmpContent.setLayout(glContent);

        editor = new FormatSpecifierComposite(cmpContent, SWT.NONE, formatspecifier);
        GridData gdEditor = new GridData(GridData.FILL_HORIZONTAL);
        editor.setLayoutData(gdEditor);

        GridLayout glButtons = new GridLayout();
        glButtons.numColumns = 2;
        glButtons.horizontalSpacing = 5;

        Composite cmpButtons = new Composite(cmpContent, SWT.NONE);
        GridData gdButtons = new GridData(GridData.FILL_HORIZONTAL);
        cmpButtons.setLayoutData(gdButtons);
        cmpButtons.setLayout(glButtons);

        btnAccept = new Button(cmpButtons, SWT.PUSH);
        GridData gdBTNAccept = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdBTNAccept.grabExcessHorizontalSpace = true;
        btnAccept.setLayoutData(gdBTNAccept);
        btnAccept.setText("Ok");
        btnAccept.addSelectionListener(this);

        btnCancel = new Button(cmpButtons, SWT.PUSH);
        GridData gdBTNCancel = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gdBTNCancel.grabExcessHorizontalSpace = false;
        btnCancel.setLayoutData(gdBTNCancel);
        btnCancel.setText("Cancel");
        btnCancel.addSelectionListener(this);
    }

    public FormatSpecifier getFormatSpecifier()
    {
        return formatspecifier;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e)
    {
        if (e.getSource().equals(btnAccept))
        {
            formatspecifier = editor.getFormatSpecifier();
            shell.dispose();
        }
        else if (e.getSource().equals(btnCancel))
        {
            formatspecifier = fsBackup;
            shell.dispose();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e)
    {
        // TODO Auto-generated method stub

    }
}