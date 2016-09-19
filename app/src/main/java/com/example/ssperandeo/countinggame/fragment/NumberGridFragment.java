package com.example.ssperandeo.countinggame.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
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
    public int mSolvedTimes = 0;
    public List<Number> mNumbers;

    private RecyclerView mCountingAppRecyclerView;
    private NumberAdapter mAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        View view = inflater.inflate( R.layout.fragment_numbers, container, false );

        mCountingAppRecyclerView = (RecyclerView) view.findViewById( R.id.fragment_number_recycler_view );

        mCountingAppRecyclerView.setLayoutManager( new GridLayoutManager(getActivity(), mNumberOfColumns) );

        createNumbers();
        updateUI();

        return view;

    }


    private void createNumbers(){

        mNumbers = new ArrayList<Number>();

        boolean solved;

        for( int x = 1; x <= mMaxNumbers; x++ ){

            if( x < mCurrentNumber ){
                solved = true;
            }else{
                solved = false;
            }
            mNumbers.add( new Number(x, solved) );

        }

    }


    private void updateUI(){

        for( Number numberObject : mNumbers ){

            if( numberObject.mNumber < mCurrentNumber ){
                numberObject.mSolved = true;
            }else{
                numberObject.mSolved = false;
            }

        }

        if( mSolvedTimes > 0 && mCurrentNumber == 1 ){
            Collections.shuffle( mNumbers );
        }

        if( mSolvedTimes > 1 ){
            Collections.shuffle( mNumbers );
        }

        mAdapter = new NumberAdapter( mNumbers );
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
            mMainView.setOnClickListener(this);

        }

        @Override
        public void onClick( View view ){

            //Log.v("NumberHolder::onClick", "mCurrentNumber = " + Integer.toString(mCurrentNumber) );

            if( mCurrentNumber == mNumber.mNumber ){

                mCurrentNumber++;
                if( mCurrentNumber == mMaxNumbers + 1 ){
                    mCurrentNumber = 1;
                    mSolvedTimes++;
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

            if( holder.mNumber.mSolved ){
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if( currentApiVersion > 15 ){
                    holder.mMainView.setBackground( ResourcesCompat.getDrawable(getResources(), R.drawable.back_solved, null) );
                }
                holder.mMainView.setAlpha((float) 0.50);
            }

        }



        @Override
        public int getItemCount() {

            return mNumbers.size();

        }

    }



}
