package cz.profinit.csobp.mdchecker.plugins;

import javax.swing.JComponent;


/**
 * Diff Plugin slouzi k vytvorezeni diffu mezi dvemi dodavkami.
 * 
 * @author: vjinoch
 */
public class DiffPlugin extends MDPlugin {

    private String oldWar;

    private String newWar;

    public DiffPlugin(String oldWar, String newWar) {
        this.oldWar = oldWar;
        this.newWar = newWar;
    }

    public String getOldWar() {
        return oldWar;
    }

    public void setOldWar(String oldWar) {
        this.oldWar = oldWar;
    }

    public String getNewWar() {
        return newWar;
    }

    public void setNewWar(String newWar) {
        this.newWar = newWar;
    }

	public String getValue() {
		return toString();
	}

	@Override
	public String toString() {
		return "DiffPlugin [oldWar=" + oldWar + ", newWar=" + newWar + "]";
	}


	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	
}
