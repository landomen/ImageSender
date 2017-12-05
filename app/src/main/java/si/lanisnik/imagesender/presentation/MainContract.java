package si.lanisnik.imagesender.presentation;

import android.content.Context;
import android.net.Uri;

import si.lanisnik.imagesender.presentation.base.BasePresenter;
import si.lanisnik.imagesender.presentation.base.BaseView;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public interface MainContract {

    interface View extends BaseView {
        void openGallery();

        void sendEmail(Uri imageUri, String content);

        void displayImage(Uri uri);

        void enableSendButton();
    }

    interface Presenter extends BasePresenter<View> {
        void onSelectClicked();

        void onSendClicked(Context context);

        void onImageSelected(Uri imageUri);
    }
}
