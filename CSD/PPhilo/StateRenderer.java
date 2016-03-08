// CSD Mar 2013 Juansa Sendra

 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

class StateRenderer extends JLabel implements ListCellRenderer {
    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
    public StateRenderer() {setOpaque(true);} 
    public Component getListCellRendererComponent(
			JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		State est=(State)value;
        setText(est.toString()); setFont(new Font("Courier",Font.BOLD,16));
        setBackground(isSelected?HIGHLIGHT_COLOR:Color.white);
        setForeground(isSelected?Color.white: Color.black); 
        return this;
    }
}

