package com.example.aplicacionpeliculavotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state of Fragment";
    private int mRadioButtonChoice = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if (isFragmentDisplayed){
                mButton.setText(R.string.close);
            }
        }

        mButton = (Button)findViewById(R.id.open_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: llamada a un nuevo fragmento / cerrar el Fragment
                if (!isFragmentDisplayed){
                    displayNewFragment();
                }else {
                    closeFragment();
                }
            }
        });
    }

    public void displayNewFragment(){
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);
        //TODO: Obtener el FragmentManager e iniciar la transacción
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //TODO: Agregar el Fragment
        fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;


    }
    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment)fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment!=null){
            fragmentManager.beginTransaction().remove(simpleFragment).commit();
            mButton.setText(R.string.open);
            isFragmentDisplayed=false;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this,"La elección fue "+Integer.toString(choice),Toast.LENGTH_SHORT).show();
    }

}