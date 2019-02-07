package tn.leaderscodes.planetecroisiere.tools;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;


public class SubRegionSectionHeader implements Section<SubRegion> {

    List<SubRegion> childList;
    String sectionText;

    public SubRegionSectionHeader(List<SubRegion> childList, String sectionText) {
        this.childList = childList;
        this.sectionText = sectionText;
    }

    @Override
    public List<SubRegion> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }
}
