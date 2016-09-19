package com.example.ssperandeo.countinggame.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssperandeo.countinggame.R;
import com.example.ssperandeo.countinggame.model.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NumberGridFragment extends Fragment{

    public int mMaxNumbers = 20;
    public int mCurrentNumber = 1;
    public int mNumberOfColumns = 5;

    private RecyclerView mCountingAppRecyclerView;
    private NumberAdapter mAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        View view = inflater.inflate( R.layout.fragment_numbers, container, false );

        mCountingAppRecyclerView = (RecyclerView) view.findViewById( R.id.fragment_number_recycler_view );

        mCountingAppRecyclerView.setLayoutManager( new GridLayoutManager(getActivity(), mNumberOfColumns) );

        updateUI();

        return view;

    }


    private void updateUI(){

        List<Number> numbers = new ArrayList<Number>();

        for( int x = mCurrentNumber; x <= mMaxNumbers; x++ ){
            numbers.add( new Number(x) );
        }

        Collections.shuffle( numbers );

        mAdapter = new NumberAdapter( numbers );
        mCountingAppRecyclerView.setAdapter( mAdapter );

    }



    private class NumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public View mMainView;
        public TextView mNumberTextView;
        public Number mNumber;



        public NumberHolder( View view ){
            super(view);
            mMainView = view;
            mNumberTextView = (TextView) mMainView.findViewById( R.id.textView );
            view.setOnClickListener(this);
        }

        @Override
        public void onClick( View view ){

            //Log.v("NumberHolder::onClick", "mCurrentNumber = " + Integer.toString(mCurrentNumber) );

            if( mCurrentNumber == mNumber.mNumber ){

                mCurrentNumber++;
                if( mCurrentNumber == mMaxNumbers + 1 ){
                    mCurrentNumber = 1;
                    Toast.makeText( getActivity(), "Yay! You did it!", Toast.LENGTH_LONG ).show();
                }
                updateUI();

            }

        }

    }


    private class NumberAdapter extends RecyclerView.Adapter<NumberHolder> {

        private List<Number> mNumbers;



        public NumberAdapter( List<Number> numbers ){
            mNumbers = numbers;
        }



        @Override
        public NumberHolder onCreateViewHolder( ViewGroup parent, int viewType ){

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate( R.layout.counting_cell, parent, false );
            return new NumberHolder( view );

        }



        @Override
        public void onBindViewHolder( NumberHolder holder, int position ){

            holder.mNumber = mNumbers.get(position);
            holder.mNumberTextView.setText( Integer.toString(holder.mNumber.mNumber) );

        }



        @Override
        public int getItemCount() {

            return mNumbers.size();

        }

    }



}
