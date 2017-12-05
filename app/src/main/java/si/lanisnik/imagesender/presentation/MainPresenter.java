package si.lanisnik.imagesender.presentation;

import android.content.Context;
import android.net.Uri;

import java.lang.ref.WeakReference;

import si.lanisnik.imagesender.presentation.base.model.DeviceInformation;
import si.lanisnik.imagesender.presentation.util.DeviceInfoManager;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public class MainPresenter implements MainContract.Presenter {

    private WeakReference<MainContract.View> view;
    private Uri selectedImageUri;

    @Override
    public void onSelectClicked() {
        view.get().openGallery();
    }

    @Override
    public void onSendClicked(Context context) {
        if (selectedImageUri != null) {
            DeviceInfoManager deviceInfoManager = new DeviceInfoManager();
            DeviceInformation deviceInformation = deviceInfoManager.getDeviceInformation(context);
            String emailContent = constructEmailContent(deviceInformation);
            view.get().sendEmail(selectedImageUri, emailContent);
        }
    }

    @Override
    public void onImageSelected(Uri imageUri) {
        selectedImageUri = imageUri;
        view.get().displayImage(imageUri);
        view.get().enableSendButton();
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void destroy() {
        view.clear();
        view = null;
    }

    private String constructEmailContent(DeviceInformation info) {
        return "Device manufacturer: " + info.getManufacturer() + "\n" +
                "Device name: " + info.getModel() + "\n" +
                "Screen size (inch): " + info.getScreenInformation().getSizeInches() + "\n" +
                "Screen size (dp): " + info.getScreenInformation().getWidthDp() + "x" + info.getScreenInformation().getHeightDp() + "\n" +
                "Screen size (px): " + info.getScreenInformation().getWidthPx() + "x" + info.getScreenInformation().getHeightPx() + "\n" +
                "Screen density: " + info.getScreenInformation().getDensity();
    }
}
