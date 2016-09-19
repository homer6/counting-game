package com.example.ssperandeo.countinggame.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssperandeo.countinggame.R;
import com.example.ssperandeo.countinggame.model.Number;

import java.util.ArrayList;
import java.util.List;


public class NumberGridFragment extends Fragment{

    private int maxNumbers = 30;
    private int currentNumber = 1;

    private RecyclerView mCountingAppRecyclerView;
    private NumberAdapter mAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        View view = inflater.inflate( R.layout.fragment_numbers, container, false );

        mCountingAppRecyclerView = (RecyclerView) view.findViewById( R.id.fragment_number_recycler_view );

        mCountingAppRecyclerView.setLayoutManager( new GridLayoutManager(getActivity(), 3) );

        updateUI();

        return view;

    }


    private void updateUI(){

        List<Number> numbers = new ArrayList<Number>();

        for( int x = currentNumber; x <= maxNumbers; x++ ){
            numbers.add( new Number(x) );
        }

        mAdapter = new NumberAdapter( numbers );
        mCountingAppRecyclerView.setAdapter( mAdapter );

    }



    private class NumberHolder extends RecyclerView.ViewHolder {

        public View mMainView;
        public TextView mNumberTextView;

        public NumberHolder( View view ){
            super(view);
            mMainView = view;
            mNumberTextView = (TextView) mMainView.findViewById( R.id.textView );
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

            Number number = mNumbers.get(position);
            holder.mNumberTextView.setText( Integer.toString(number.mNumber) );

        }



        @Override
        public int getItemCount() {

            return mNumbers.size();

        }

    }



}
