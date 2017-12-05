package si.lanisnik.imagesender.presentation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import si.lanisnik.imagesender.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final int REQUEST_CODE_GALLERY = 1984;

    @BindView(R.id.preview_image_view)
    protected ImageView previewImageView;

    @BindView(R.id.send_image_button)
    protected Button sendButton;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
    }

    @OnClick(R.id.select_image_button)
    protected void onSelectClicked() {
        presenter.onSelectClicked();
    }

    @OnClick(R.id.send_image_button)
    protected void onSendClicked() {
        presenter.onSendClicked(this);
    }

    @Override
    public void enableSendButton() {
        sendButton.setEnabled(true);
    }

    @Override
    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (galleryIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void sendEmail(Uri imageUri, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Bug details");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("image/jpeg");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void displayImage(Uri uri) {
        Glide.with(this)
                .load(uri)
                .into(previewImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                presenter.onImageSelected(data.getData());
            } else {
                showErrorMessage();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    private void initPresenter() {
        presenter = new MainPresenter();
        presenter.setView(this);
    }

    private void showErrorMessage() {
        Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
    }
}