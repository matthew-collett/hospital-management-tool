package team6.project.controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;
import java.util.function.Supplier;

public class SearchAction implements DocumentListener {

    private final Supplier<String> filterSupplier;
    private final DefaultListModel<String> searchModel;
    private final List<String> dataList;

    public SearchAction(final Supplier<String> filterSupplier, final ListModel<String> searchModel, final List<String> dataList) {
        this.filterSupplier = filterSupplier;
        this.searchModel = (DefaultListModel<String>) searchModel;
        this.dataList = dataList;
    }

    @Override
    public void insertUpdate(final DocumentEvent e) {
        filter();
    }

    @Override
    public void removeUpdate(final DocumentEvent e) {
        filter();
    }

    @Override
    public void changedUpdate(final DocumentEvent e) {
    }

    private void filter() {
        dataList.forEach(this::filter);
    }

    private void filter(final String str) {
        final String filter = filterSupplier.get();
        if (!str.toLowerCase().contains(filter.toLowerCase())) {
            searchModel.removeElement(str);
        } else {
            if (!searchModel.contains(str)) {
                searchModel.addElement(str);
            }
        }
    }
}
