package zkProjekt;

import java.util.HashMap;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

public class NawigationViewModel {
	
    @Command
    public void addNewSamochod() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sSamochod", null);
        map.put("recordMode", "NEW");
        Executions.createComponents("CRUD.zul", null, map);
    }
}
