import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
/**
 * CombinedIcon class is a class which contains the properties of the icon in the game
 * Implements the Icon interface
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class CombinedIcon implements Icon {
    private Icon frontIcon;
    private Icon backIcon;

    /**
     * Default constructor initializing instance variables
     * @param frontIcon icon on the front
     * @param backIcon icon on the back 
     */
    public CombinedIcon(Icon frontIcon, Icon backIcon) {
        this.frontIcon = frontIcon;
        this.backIcon = backIcon;
    }

    /**
     * method that gets the icon width
     */
    @Override
    public int getIconWidth() {
    	if (frontIcon != null) {
            return Math.max(frontIcon.getIconWidth(), backIcon.getIconWidth());
    	}
    	return backIcon.getIconWidth();
    }

    /**
     * method that gets the icon height
     */
    @Override
    public int getIconHeight() {
    	if (frontIcon != null) {
    		return Math.max(frontIcon.getIconHeight(), backIcon.getIconHeight());
    	}
    	return backIcon.getIconHeight();
    }

    /**
     * method which sets the attributes of the icon
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
    	backIcon.paintIcon(c, g, x, y);
    	if (frontIcon != null) {
    		frontIcon.paintIcon(c, g, x + 8, y - 5);
    	}
    }
}