package Control;

import Model.Image;
import View.Ui.ApplicationDisplay;
import Model.NoImageException;

public class PrevCommand implements Command {
    private final ApplicationDisplay applicationDisplay;

    public PrevCommand(ApplicationDisplay applicationDisplay) {
        this.applicationDisplay = applicationDisplay;
    }
    @Override
    public void execute() {
        try {
            Image imageToDisplay = applicationDisplay.getImageDisplay().getImage().prev();
            applicationDisplay.getImageDisplay().show(imageToDisplay);
            applicationDisplay.setTitle(imageToDisplay.getName());
        } catch (NoImageException e) {

        }
    }
}
