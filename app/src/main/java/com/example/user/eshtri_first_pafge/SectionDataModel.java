package com.example.user.eshtri_first_pafge;

import java.util.ArrayList;

/**
 * Created by pratap.kesaboyina on 30-11-2015.
 */
public class SectionDataModel {

    private String headerTitle;
    private ArrayList<SingleItemModel> allItemsInSection;

    public SectionDataModel() {
        super();

    }

    public SectionDataModel(final String headerTitle, final ArrayList<SingleItemModel> allItemsInSection) {
        super();
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }

    public final String getHeaderTitle() {
        return this.headerTitle;
    }

    public final void setHeaderTitle(final String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public final ArrayList<SingleItemModel> getAllItemsInSection() {
        return this.allItemsInSection;
    }

    public final void setAllItemsInSection(final ArrayList<SingleItemModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

}
