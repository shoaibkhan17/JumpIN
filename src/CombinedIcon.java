import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class CombinedIcon implements Icon {
    private Icon frontIcon;
    private Icon backIcon;

    public CombinedIcon(Icon frontIcon, Icon backIcon) {
        this.frontIcon = frontIcon;
        this.backIcon = backIcon;
    }

    @Override
    public int getIconWidth() {
    	if (frontIcon != null) {
            return Math.max(frontIcon.getIconWidth(), backIcon.getIconWidth());
    	}
    	return backIcon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
    	if (frontIcon != null) {
    		return Math.max(frontIcon.getIconHeight(), backIcon.getIconHeight());
    	}
    	return backIcon.getIconHeight();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
    	backIcon.paintIcon(c, g, x, y);
    	if (frontIcon != null) {
    		frontIcon.paintIcon(c, g, x + 8, y - 5);
    	}
    }
}