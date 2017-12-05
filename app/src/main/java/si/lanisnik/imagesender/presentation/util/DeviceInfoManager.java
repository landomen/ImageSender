package si.lanisnik.imagesender.presentation.util;

import android.content.Context;
import android.util.DisplayMetrics;

import si.lanisnik.imagesender.presentation.base.model.DeviceInformation;
import si.lanisnik.imagesender.presentation.base.model.ScreenInformation;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public class DeviceInfoManager {

    public DeviceInformation getDeviceInformation(Context context) {
        return new DeviceInformation(android.os.Build.MANUFACTURER, android.os.Build.MODEL, getScreenInformation(context));
    }

    private ScreenInformation getScreenInformation(Context context) {
        ScreenInformation screenInformation = new ScreenInformation();

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        screenInformation.setWidthPx(metrics.widthPixels);
        screenInformation.setHeightPx(metrics.heightPixels);
        screenInformation.setDensity(metrics.densityDpi);

        screenInformation.setWidthDp(screenInformation.getWidthPx() / metrics.density);
        screenInformation.setHeightDp(screenInformation.getHeightPx() / metrics.density);

        double x = Math.pow(screenInformation.getWidthPx() / metrics.xdpi, 2);
        double y = Math.pow(screenInformation.getHeightPx() / metrics.ydpi, 2);
        screenInformation.setSizeInches(Math.sqrt(x + y));

        return screenInformation;
    }
}
