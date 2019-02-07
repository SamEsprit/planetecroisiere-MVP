package tn.leaderscodes.planetecroisiere.tools;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Price;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;


public class TravelTarifSectionHeader implements Section<Price> {

    List<Price> childList;
    String sectionText;

    public TravelTarifSectionHeader(List<Price> Price, String sectionText) {
        this.childList = Price;
        this.sectionText = sectionText;
    }

    @Override
    public List<Price> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }
}
