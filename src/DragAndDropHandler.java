import java.awt.Image;

import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

@SuppressWarnings("serial")
public class DragAndDropHandler extends TransferHandler {
	
	private boolean importable;
	
	public DragAndDropHandler(boolean importable) {
		super("icon");
		this.importable = importable;
	}

    public boolean importData(TransferSupport support) {
    	if (importable) {
    		super.importData(support);
    	}
    	return false;
    }
}
