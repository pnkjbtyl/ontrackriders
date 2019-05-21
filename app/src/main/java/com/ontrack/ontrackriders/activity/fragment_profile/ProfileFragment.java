package com.ontrack.ontrackriders.activity.fragment_profile;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ontrack.ontrackriders.MyApp;
import com.ontrack.ontrackriders.R;
import com.ontrack.ontrackriders.activity.edit_profile.EditProfileActivity;
import com.ontrack.ontrackriders.utils.PathUtil;
import com.ontrack.ontrackriders.utils.Pref;
import com.ontrack.ontrackriders.webservice.IBaseUrl;
import com.ontrack.ontrackriders.webservice.Ret;
import com.ontrack.ontrackriders.webservice.WebInterface;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import org.json.JSONObject;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements View.OnClickListener,IFragProfileView,IBaseUrl{
    private Uri destination=null;
    private String filePath;
    private static final String GET_PROFILE_PIC= BASE_URL+"users/setprofilepic/";
    @BindView(R.id.imageViewProfileCamera)
    ImageView imageViewProfileCamera;
    @BindView(R.id.imageViewProfilePic)
    ImageView imageViewProfilePic;
    @BindView(R.id.imageViewEditProfile)
    ImageView imageViewEditProfile;
    @BindView(R.id.textViewProfileName)
    TextView textViewProfileName;
    @BindView(R.id.textViewProfileAge)
    TextView textViewProfileAge;
    @BindView(R.id.textViewProfileDob)
    TextView textViewProfileDob;
    @BindView(R.id.textViewProfileGender)
    TextView textViewProfileGender;
    @BindView(R.id.textViewProfileDl)
    TextView textViewProfileDl;
    @BindView(R.id.textViewProfileId)
    TextView textViewProfileId;
    @BindView(R.id.textViewProfileLoc)
    TextView textViewProfileLoc;
    @BindView(R.id.textViewProfileStatus)
    TextView textViewProfileStatus;
    @BindView(R.id.textViewProfileBloodGroup)
    TextView textViewProfileBloodGroup;
    @BindView(R.id.textViewProfileSmoke)
    TextView textViewProfileSmoke;
    @BindView(R.id.textViewProfileDrink)
    TextView textViewProfileDrink;
    @BindView(R.id.textViewProfileSpecs)
    TextView textViewProfileSpecs;
    private FragProfilePresenter fragProfilePresenter;
    private static final String TAG="ProfileFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Opened Profile Fragment");
        View view= inflater.inflate(R.layout.fragment_profile,container,false);
        ButterKnife.bind(this,view);
        fragProfilePresenter=new FragProfilePresenter(MyApp.getContext(),this);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewEditProfile.setOnClickListener(this);
        imageViewProfileCamera.setOnClickListener(this);
        fetchprofile();

    }

    private void fetchprofile() {
        //app profile information will get captured here.
        Log.d(TAG,"Profile Fetch Initiation");
        WebInterface webInterface= Ret.getClient().create(WebInterface.class);
        Call<FetchProfileResponse> call=webInterface.requestFetchProfile();
        call.enqueue(new Callback<FetchProfileResponse>() {
            @Override
            public void onResponse(Call<FetchProfileResponse> call, Response<FetchProfileResponse> response) {
                if(response.code()==200 && response.isSuccessful())
                {
                    Log.d(TAG,"Profile Details Fetched");
                    FetchProfileResponse fetchProfileResponse=response.body();
                    String name=fetchProfileResponse.getData().getName();
                    String age=fetchProfileResponse.getData().getAge();
                    String dob=fetchProfileResponse.getData().getDob();
                    String gender=fetchProfileResponse.getData().getGender();
                    String dl=fetchProfileResponse.getData().getDrivingLicence();
                    String idNo=fetchProfileResponse.getData().getIdentificationNo();
                    String location=fetchProfileResponse.getData().getLocation();
                    String maritalStatus=fetchProfileResponse.getData().getMaritalStatus();
                    String bloodGroup=fetchProfileResponse.getData().getBloodGroup();
                    String smoke=fetchProfileResponse.getData().getSmoke();
                    String drink=fetchProfileResponse.getData().getDrink();
                    String spectacles=fetchProfileResponse.getData().getSpectacles();

                    Log.d(TAG,"NAME=> "+name+"\n"+ "AGE=> "+age+"\n");

                    textViewProfileName.setText(name);
                    textViewProfileAge.setText(age);
                    textViewProfileDob.setText(dob);
                    textViewProfileGender.setText(gender);
                    textViewProfileDl.setText(dl);
                    textViewProfileId.setText(idNo);
                    textViewProfileLoc.setText(location);
                    textViewProfileStatus.setText(maritalStatus);
                    textViewProfileBloodGroup.setText(bloodGroup);
                    textViewProfileSmoke.setText(smoke);
                    textViewProfileDrink.setText(drink);
                    textViewProfileSpecs.setText(spectacles);

                }
                else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toasty.info(getContext(), jObjError.getString("message")).show();
                        Log.d(TAG,jObjError.getString("message"));
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<FetchProfileResponse> call, Throwable t) {
                Log.d(TAG,t.getLocalizedMessage());
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageViewEditProfile)
        {
            Log.d(TAG,"Edit Profile Activity Opens");
            Intent intent=new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.imageViewProfileCamera)
        {
            Log.d(TAG,"Start Image cropper activity and get file path");
            getProfilePic();
        }

    }

    private void getProfilePic() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON).
                setOutputCompressQuality(70)
                .start(MyApp.getContext(),this);


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"On Resume Method Called");
        fetchprofile();
    }

    /*---------------------------------------CROP IMAGE HANDLING-------------------------------------------*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                destination = result.getUri();
                Log.d(TAG,"Data Passed To Presenter");
                try {
                    filePath= PathUtil.getPath(MyApp.getContext(),destination);
                    Log.d(TAG,"Image Path is=> "+ filePath);
                    fragProfilePresenter.requestSetProfilePic(filePath);

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d(TAG,"Error"+error.getMessage());
            }
        }
    }


    @Override
    public void startProgress() {

    }

    @Override
    public void stopProgress() {

    }

    @Override
    public void onComplete(String message) {
        Toasty.success(MyApp.getContext(),message).show();
        //setProfilePic();

    }

    private void setProfilePic()
    {
        String profilePicId= Pref.getUserProfilePicId(MyApp.getContext());
        Log.d(TAG, "Profile pic id: " + profilePicId);
            GlideUrl glideUrl = new GlideUrl(GET_PROFILE_PIC + "462",
                    new LazyHeaders.Builder()
                            .addHeader("x-access-code", Pref.getUserToken(MyApp.getContext()))
                            .build());

            Glide.with(this)
                    .load(glideUrl).placeholder(R.mipmap.ic_launcher_round)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(imageViewProfilePic);
        }


}
