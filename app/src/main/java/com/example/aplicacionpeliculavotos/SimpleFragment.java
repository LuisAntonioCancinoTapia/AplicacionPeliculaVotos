package com.example.aplicacionpeliculavotos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;
    public int mRadioButtonChoice = NONE;
    private static final String CHOICE = "choice";

    public int conta1 = 0;
    public int conta2 = 0;
    Button btnpeli1,btnpeli2,btnresultado;
    TextView tvpeli1,tvpeli2;


    OnFragmentInteractionListener mListener;

    interface  OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }

    public static SimpleFragment newInstance(int choice){
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE,choice);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new ClassCastException(context.toString()+getResources().getString(R.string.exception_message));
        }
    }



    public SimpleFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(String param1, String param2) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = root.findViewById(R.id.radio_group1);


        btnpeli1 = (Button) root.findViewById(R.id.buttonpeli1);
        btnpeli1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conta1++;
                tvpeli1.setText(Integer.toString(conta1));
            }
        });
        btnpeli2 = (Button) root.findViewById(R.id.buttonpeli2);
        btnpeli2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conta2++;
                tvpeli2.setText(Integer.toString(conta2));
            }
        });
        tvpeli1 = (TextView) root.findViewById(R.id.tvpelicula1);
        tvpeli1.setText(Integer.toString(conta1));
        tvpeli2 = (TextView) root.findViewById(R.id.tvpelicula2);
        tvpeli2.setText(Integer.toString(conta2));


        btnresultado = (Button) root.findViewById(R.id.btnresultado);
        btnresultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conta1>conta2){
                    Toast.makeText(getActivity(),"LA PELÍCULA 1 GANÓ",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"LA PELÍCULA 2 GANÓ",Toast.LENGTH_LONG).show();
                }
            }
        });


        if (getArguments().containsKey(CHOICE)){
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            if (mRadioButtonChoice != NONE){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                TextView tv = root.findViewById(R.id.fragment_header);
                switch (index){
                    case YES:
                        mListener.onRadioButtonChoice(YES);
                        tv.setText(R.string.yes_message);
                        break;
                    case NO:
                        tv.setText(R.string.no_message);
                        mListener.onRadioButtonChoice(NO);
                        break;
                    default:
                        mRadioButtonChoice=NONE;
                        mListener.onRadioButtonChoice(NONE);
                        break;
                }

            }
        });
        return root;
    }
}