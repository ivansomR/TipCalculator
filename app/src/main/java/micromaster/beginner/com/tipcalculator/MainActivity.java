package micromaster.beginner.com.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_billAmount)
    EditText et_billAmount;
    @BindView(R.id.tv_tipPercent)
    TextView tv_tipPercent;
    @BindView(R.id.tv_tipTotal)
    TextView tv_tipTotal;
    @BindView(R.id.tv_billTotalRes)
    TextView tv_BillTotalRes;

    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float REGULAR_TIP_PERCENTAGE = 10;
    float DEFAULT_TIP_PERCENTAGE = 15;
    float EXCELLENT_TIP_PERCENTAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();

    }

    private void setTipValues() {

        tv_tipPercent.setText(getString(R.string.msg_tipPct, percentage));
        tv_tipTotal.setText(getString(R.string.msg_tipTotal, tipTotal));
        tv_BillTotalRes.setText(getString(R.string.msg_billTotalRes, finalBillAmount));
    }

    @OnClick({R.id.btn_sad, R.id.btn_normal, R.id.btn_happy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sad:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.btn_normal:
                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.btn_happy:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.et_billAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {

        if (percentage == 0)
            percentage = DEFAULT_TIP_PERCENTAGE;

        if(!et_billAmount.getText().toString().equals("") && !et_billAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(et_billAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount*percentage)/100;
        finalBillAmount = totalBillAmount + tipTotal;

    }
}