package com.gmail.michzuerch.teachersassistant.ui.views;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

public interface HasConfirmation {

	ConfirmDialog getConfirmDialog();

	void setConfirmDialog(ConfirmDialog confirmDialog);
}
