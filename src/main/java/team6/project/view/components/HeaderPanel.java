package team6.project.view.components;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;
import static java.awt.Font.SANS_SERIF;
import static java.lang.String.format;
import static javax.swing.Box.createHorizontalGlue;
import static javax.swing.Box.createVerticalStrut;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;
import static team6.project.view.helper.IconHelper.getIcon;

public class HeaderPanel extends JPanel {

    public HeaderPanel(final String title) {

        setLayout(new BoxLayout(this, Y_AXIS));

        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, X_AXIS));

        final JLabel icon = new JLabel();
        icon.setIcon(getIcon(getClass(), "/assets/logo.png", 70, 70));
        contentPanel.add(icon);
        contentPanel.add(createHorizontalGlue());

        final JLabel text = new JLabel(format("Patient Information Tool - %s", title));
        text.setFont(new Font(SANS_SERIF, BOLD, 14));
        contentPanel.add(text);

        add(contentPanel);
        add(createVerticalStrut(16));
    }
}
