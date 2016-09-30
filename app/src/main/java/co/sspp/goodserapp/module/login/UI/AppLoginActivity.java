package co.sspp.goodserapp.module.login.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.base.BaseActivity;
import co.sspp.goodserapp.module.login.bean.LoginInfo;
import co.sspp.goodserapp.rx.RxSchedulersHelper;
import co.sspp.goodserapp.rx.RxSubscribe;
import co.sspp.goodserapp.rx.api.Api;
import co.sspp.goodserapp.rx.rxbus.RxBus;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.utils.MD5Utils;
import co.sspp.goodserapp.weight.CodeButton;
import co.sspp.goodserapp.weight.MessageToast;

public class AppLoginActivity extends BaseActivity {


    @BindView(R.id.rb1)
    RadioButton mRb1;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.head)
    ImageView mHead;


    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.miss_password)
    TextView mMissPassword;
    @BindView(R.id.qq)
    ImageView mQq;


    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;


    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.bt_code)
    CodeButton mBtCode;
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);
        AutoUtils.auto(this);

        ButterKnife.bind(this);


        mRb1.setChecked(true);

        mRg.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == mRb1.getId()) {
                mLlPhone.setVisibility(View.GONE);
                mLlName.setVisibility(View.VISIBLE);
            } else {
                mLlPhone.setVisibility(View.VISIBLE);
                mLlName.setVisibility(View.GONE);
            }

        });


        mBtCode.setTextAfter("s后重新获取").setTextBefore("获取验证码").setLenght(60 * 1000);


        RxTextView.textChanges(mPhone)
                .skip(1)
                .subscribe(charSequence -> {
                    if (charSequence.length() > 10) mBtCode.setEnabled(true);
                    else mBtCode.setEnabled(false);
                });


    }

    @OnClick({R.id.button, R.id.register, R.id.miss_password, R.id.qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:


                login();

//                Observable.timer(2, TimeUnit.SECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(aLong -> {
//
//                            mButton.stopAnim(() -> finish());
//
//                        });

                break;
            case R.id.register:

                gotoActivity(AppRegisterActivity.class);

                break;
            case R.id.miss_password:
                break;
            case R.id.qq:
                break;
        }
    }

    /**
     *
     */
    private void login() {


        String a = "sad";

        RxBus.getDefault().post(a);

        String name = mName.getText().toString();

        String password = MD5Utils.small16md5(mPassword.getText().toString());

        Api.getDefault()
                .nameLogin(name, password, 2, "")
                .compose(RxSchedulersHelper.applySchedulers())
                .subscribe(new RxSubscribe<LoginInfo>(this) {
                    @Override
                    protected void _onNext(LoginInfo loginInfo) {
                        LoginInfo.RetDataEntity retData = loginInfo.getRetData();
                        retData.getMobileLogKey();
                    }

                    @Override
                    protected void _onError(String message) {
                        MessageToast.show(message,MessageToast.Style.INFO);
                    }
                });


    }


}
