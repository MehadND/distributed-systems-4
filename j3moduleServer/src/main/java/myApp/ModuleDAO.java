package myApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ModuleDAO {

instance;
	
	private Map<Integer, Module> modulesMap = new HashMap<Integer, Module>();
	
	private ModuleDAO() {
		Module m1 = new Module();
		m1.setId(1);
		m1.setName("Distributed Systems");
		m1.setLecturer("EG");
		m1.setHoursPerWeek(4);
		
		modulesMap.put(1, m1);

		Module m2 = new Module();
		m2.setId(2);
		m2.setName("WebDev");
		m2.setLecturer("EG");
		m2.setHoursPerWeek(2);
		
		modulesMap.put(2, m2);
		
	}
	
	public List<Module> getModules()
	{
		List<Module> modules= new ArrayList<Module>();
		modules.addAll(modulesMap.values());
		return modules;
	}
	
	public Module getModule(int id) {
		return modulesMap.get(id);
	}
	
}
