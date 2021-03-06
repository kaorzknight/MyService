package suthison.kasidis.myservice.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import suthison.kasidis.myservice.MainActivity;
import suthison.kasidis.myservice.R;

/**
 * Created by admin on 12/17/2017.
 */

public class CalculateFragment extends Fragment {
    //    Explicit
    private String moneyString;
    private double factorADouble;
    private EditText editText;

    public static CalculateFragment calculateInstance(String moneyString,
                                                      double factorDouble) {
        CalculateFragment calculateFragment = new CalculateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Money", moneyString);
        bundle.putDouble("Factor", factorDouble);
        calculateFragment.setArguments(bundle);
        return calculateFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Get value from argument
        getValueFromArgument();
//        Show HintMoney
        showHintMoney();
//        Create Toolbar
        createToolbar();
//        Calculate Controller
        calculateController();
    }   //Main Method

    private void showHintMoney() {
        editText = getView().findViewById(R.id.edtMoney);
        editText.setHint(moneyString);
    }

    private void getValueFromArgument() {
        moneyString   = getArguments().getString("Money");
        factorADouble = getArguments().getDouble("Factor");
    }

    private void calculateController() {
        Button button = getView().findViewById(R.id.btnCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myMoneyString = editText.getText().toString().trim();
//                Get Value From Edit Text
//                Check Space
                if (myMoneyString.isEmpty()) {
//                    Have Space
                    myAlertDialog(getString(R.string.have_space), getString(R.string.message_space));
                } else {
//                    No Space
                    Double moneyADouble = Double.parseDouble(myMoneyString);
                    double answerADouble = moneyADouble * factorADouble;
                    String answerString = Double.toString(answerADouble);
                    myAlertDialog("Your Money",answerString);
                }
            }   // onClick
        });
    }

    private void myAlertDialog(String titleString, String messageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.alert);
        builder.setCancelable(false);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCalculate);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar()
                .setTitle("Calculate");
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);

        return view;
    }
}


