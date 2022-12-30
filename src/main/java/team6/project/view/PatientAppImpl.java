package team6.project.view;

import team6.project.model.AppModel;
import team6.project.model.AppModelImpl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static java.util.Objects.nonNull;

public class PatientAppImpl extends PatientApp {

    private final AppModel model;

    public PatientAppImpl() {

        super("Everett Chalmers Hospital");

        model = new AppModelImpl();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setView(new LoginView(this));

        final URL url = getClass().getResource("/assets/icon.png");

        if (nonNull(url)) {
            setIconImage(new ImageIcon(url).getImage());
        }
    }

    public void start() {
        setMinimumSize(new Dimension(600, 0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setView(final JPanel view) {
        setContentPane(view);
        pack();
        revalidate();
        repaint();
    }

    @Override
    public AppModel getModel() {
        return model;
    }
}
