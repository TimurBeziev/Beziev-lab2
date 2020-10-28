package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PHDPannel extends CreateGUIItems {
    public JPanel phdPannel;
    public JButton okButton;

    public PHDPannel() throws IOException {
        phdPannel = new JPanel(new GridLayout(1, 1));
        okButton = addButton("src/img/phdImage.png");
        phdPannel.add(okButton);

        phdPannel.setBounds(350, 200, okButton.getWidth(), okButton.getHeight());
        phdPannel.setOpaque(true);
    }

    public JPanel GetPGDPannel() {
        return phdPannel;
    }
    public void SetCancelButtonListener(ActionListener listener) {
        okButton.addActionListener(listener);
    }
}


