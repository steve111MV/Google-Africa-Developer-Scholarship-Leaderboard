package cg.stevendende.gadsleaderboard.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import cg.stevendende.gadsleaderboard.R;
import cg.stevendende.gadsleaderboard.databinding.ActivitySubmitBinding;
import cg.stevendende.gadsleaderboard.interfaces.GadsApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {

    private ActivitySubmitBinding binding;
    GadsApiService service;

    private String firstName;
    private String lastName;
    private String email;
    private String projectRepository;

    final String EMAIL_PATERN1 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final String EMAIL_PATERN2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
    final String ERROR_BLANK_FIELD = "Input cannot be blank";
    final String ERROR_EMAIL_INVALID = "Enter a valid email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubmitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitProject();
            }
        });

    }


    private void submitProject(){

        if (binding.editFirstName.getText().toString().isEmpty()) {
            binding.editFirstName.setError(ERROR_BLANK_FIELD);
            return;
        }
        if (binding.editLastName.getText().toString().isEmpty()) {
            binding.editLastName.setError(ERROR_BLANK_FIELD);
        }
        if (binding.editEmail.getText().toString().isEmpty()) {
            binding.editEmail.setError(ERROR_BLANK_FIELD);
        }
        if (binding.editGithubLink.getText().toString().isEmpty()) {
            binding.editGithubLink.setError(ERROR_BLANK_FIELD);
        }
        if (! (binding.editEmail.getText().toString().matches((EMAIL_PATERN1))
                || (binding.editEmail.getText().toString().matches(EMAIL_PATERN2))) ) {
            binding.editEmail.setError(ERROR_EMAIL_INVALID);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        service = retrofit.create(GadsApiService.class);
        service.postProject(firstName, lastName, email, projectRepository).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i("cases", response.isSuccessful()?"Submitted":"failure");

                if(response.isSuccessful()){
                    displayAlertDialog(R.drawable.success_image, getResources().getString(R.string.alert_success));
                } else {
                    displayAlertDialog(R.drawable.warning_sign, getResources().getString(R.string.alert_failure));
                }
                //show dialogs
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                displayAlertDialog(R.drawable.warning_sign, getResources().getString(R.string.alert_failure));
            }
        });
    }

    private void displayAlertDialog(int image, String title){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_submit, null);
        builder.setView(dialogView);

        ImageView imageView =  dialogView.findViewById(R.id.image);
        Glide.with(this)
                .load(image)
                .into(imageView);

        TextView titleTV =  dialogView.findViewById(R.id.title);
        titleTV.setText(title);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
