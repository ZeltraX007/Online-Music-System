package com.example.musicappserver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.musicappserver.Model.GetSongs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneSongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneSongFragment extends Fragment {

    int position;
    GetSongs song;
    private ImageView imageSong;
    JcPlayerView jcPlayerView;
    private AudioVisualization audioVisualization;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnBack;

    public OneSongFragment() {
    }

    public OneSongFragment(int position) {
        // Required empty public constructor
        this.position = position;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneSongFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OneSongFragment newInstance(String param1, String param2) {
        OneSongFragment fragment = new OneSongFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_song, container, false);
        // minimize
//        btnBack = view.findViewById(R.id.btnBack);
        jcPlayerView = view.findViewById(R.id.jcplayer1);

        if(getActivity() instanceof AllSongsActivity)
            jcAudios = ((AllSongsActivity)getActivity()).getJcAudios();
        else {
            jcAudios = ((SongActivity) getActivity()).getJcAudios();
        }
        jcPlayerView.initPlaylist(jcAudios,null);
        jcPlayerView.playAudio(jcAudios.get(position));
        jcPlayerView.createNotification();
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resizeFragment(OneSongFragment.this,500,100);
//            }
//        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // you can extract AudioVisualization interface for simplifying things
        audioVisualization = (AudioVisualization) view.findViewById(R.id.visualizer_view);
        VisualizerDbmHandler vizualizerHandler = DbmHandler.Factory.newVisualizerHandler(getContext(), 0);
        audioVisualization.linkTo(vizualizerHandler);
    }
    @Override
    public void onResume() {
        super.onResume();
        audioVisualization.onResume();
    }

    @Override
    public void onPause() {
        audioVisualization.onPause();
        super.onPause();
    }
    @Override
    public void onDestroyView() {
        audioVisualization.release();
        super.onDestroyView();
    }

    public void setSong(GetSongs song){
        this.song = song;
    }

    private void resizeFragment(Fragment f, int newWidth, int newHeight) {
        if (f != null) {
            View view = f.getView();
            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(newWidth, newHeight);
            view.setLayoutParams(p);
            view.requestLayout();
        }
    }

}