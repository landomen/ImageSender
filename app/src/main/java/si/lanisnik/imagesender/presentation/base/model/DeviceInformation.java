package si.lanisnik.imagesender.presentation.base.model;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public class DeviceInformation {
    private String manufacturer;
    private String model;
    private ScreenInformation screenInformation;

    public DeviceInformation(String manufacturer, String model, ScreenInformation screenInformation) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.screenInformation = screenInformation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public ScreenInformation getScreenInformation() {
        return screenInformation;
    }
}
