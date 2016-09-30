package co.sspp.goodserapp.module.login.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.base.BaseActivity;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.weight.ClearEditText;
import co.sspp.goodserapp.weight.CodeButton;
import co.sspp.goodserapp.weight.PasswordEditText;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-09-29 19:57                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */

public class AppRegisterActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.head)
    ImageView mHead;
    @BindView(R.id.phone)
    ClearEditText mPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.bt_code)
    CodeButton mBtCode;
    @BindView(R.id.password)
    PasswordEditText mPassword;
    @BindView(R.id.ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_register);

        AutoUtils.auto(this);

        ButterKnife.bind(this);


        mBtCode.setTextAfter("s后重新获取").setTextBefore("获取验证码").setLenght(60 * 1000);

    }

    @OnClick({R.id.back, R.id.bt_code, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.bt_code:
                break;
            case R.id.button:

                register();

                break;
        }
    }

    private void register() {



//        Api.getDefault()
//                .appRegister();

    }
}
