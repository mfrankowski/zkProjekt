package zkProjekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import zkProjekt.SamochodyData;
import zkProjekt.Samochod;

public class ListViewModel {

    private List<Samochod> samochodList = new ArrayList<Samochod>();
    private Samochod curSelectedSamochod;
    private Integer curSelectedSamochodIndex;
	public ListModel<Samochod> getSamochodModel() {
		return new ListModelList<Samochod>(samochodList);
	}
    public Integer getCurSelectedSamochodIndex() {
		return curSelectedSamochodIndex;
	}

	public void setCurSelectedSamochodIndex(Integer curSelectedSamochodIndex) {
		this.curSelectedSamochodIndex = curSelectedSamochodIndex;
	}

	public Samochod getCurSelectedSamochod() {
        return curSelectedSamochod;
    }

    public void setCurSelectedSamochod(Samochod curSelectedSamochod) {
        this.curSelectedSamochod = curSelectedSamochod;
    }
    @Init
    public void initSetup() {
    	new SamochodyData();
    	samochodList = SamochodyData.getAllSamochody();
    }

    public List<Samochod> getallSamochody() {
        return samochodList;
    }

    @Command
    public void addNewSamochod() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sSamochod", null);
        map.put("recordMode", "NEW");
        Executions.createComponents("CRUD.zul", null, map);
    }

    @Command
    public void editThisSamochod() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sSamochod", this.curSelectedSamochod);
        map.put("recordMode", "EDIT");
        setCurSelectedSamochodIndex(samochodList.indexOf(curSelectedSamochod));
        Executions.createComponents("CRUD.zul", null, map);
    }


    @GlobalCommand
    @NotifyChange("allSamochody")
    public void updateSamochodList(@BindingParam("pSamochod") Samochod cust1,
            @BindingParam("recordMode") String recordMode) {
        if (recordMode.equals("NEW")) {
        	samochodList.add(cust1);
        }
        if (recordMode.equals("EDIT")) {
        	samochodList.set(this.curSelectedSamochodIndex, cust1);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Command
    public void deleteThisSamochod() {

        String str = "Samochód \""
                + curSelectedSamochod.getNumRej()
                + "\" bêdzie usuniêty.";

        Messagebox.show(str, "Confirm", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        if (((Integer) event.getData()).intValue() == Messagebox.OK) {
                        	samochodList.remove(samochodList
                                    .indexOf(curSelectedSamochod));
                            BindUtils.postNotifyChange(null, null,
                            		ListViewModel.this, "allSamochody");
                        }
                    }
                });
    }

}
