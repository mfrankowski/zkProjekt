package zkProjekt;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import zkProjekt.Samochod;

public class CRUDViewModel {

    @Wire("#CRUD")
    private Window win;

    private Samochod selectedSamochod;
    private String recordMode;
 

    public String getRecordMode() {
        return recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public Samochod getSelectedSamochod() {
        return selectedSamochod;
    }

    public void setSelectedSamochod(Samochod selectedSamochod) {
        this.selectedSamochod = selectedSamochod;
    }

    @Init
    public void initSetup(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("sSamochod") Samochod c1,
            @ExecutionArgParam("recordMode") String recordMode)
            throws CloneNotSupportedException {
        Selectors.wireComponents(view, this, false);

        setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
            this.selectedSamochod = new Samochod();
        }

        if (recordMode.equals("EDIT")) {
            this.selectedSamochod = (Samochod) c1.clone();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
        Map args = new HashMap();
        args.put("pSamochod", this.selectedSamochod);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updateSamochodList", args);
        win.detach();
    }

    @Command
    public void closeThis() {
        win.detach();
    }
    
}