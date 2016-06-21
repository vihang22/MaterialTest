package com.ckeeda.materialtest;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private static final String PREF_FILE = "preftext";
    private final String KEY_LEARNDRAWER = "drawer_leared";

    private ActionBarDrawerToggle mActionDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mLearneDrawer;
    private boolean mFromSavedInstanceState;
    private View mContainerView;
    private RecyclerView drawer_recycler;
    private Recycler_Adapter drawer_adapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mLearneDrawer = Boolean.valueOf(readfromPreference(getActivity(),KEY_LEARNDRAWER,"false"));
        if(savedInstanceState != null)
            mFromSavedInstanceState=true;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawer_recycler = (RecyclerView)view.findViewById(R.id.drawer_recyclerview);

        drawer_adapter = new Recycler_Adapter(getActivity(),getdata());
        drawer_recycler.setAdapter(drawer_adapter);
        drawer_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));



    }

    public static List<Information> getdata(){
        List<Information> data = new ArrayList<>();
        String title[] = {"Hetal","Vihang","Sid","Palak"};
        for(int i=0;i<title.length;i++){
            Information current = new Information();
            current.iconid = R.drawable.next;
            current.title = title[i];
            Log.d("TITLE", "getdata:"+title[i]);
            data.add(current);
        }
        return data;
    }

    public void setUp(int byId, DrawerLayout viewById, final Toolbar toolbar) {
        mContainerView=getActivity().findViewById(byId);
        mDrawerLayout = viewById;
        mActionDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mLearneDrawer){
                    mLearneDrawer=true;
                    savetoPreference(getActivity(),KEY_LEARNDRAWER,mLearneDrawer+"");
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1-slideOffset/2);
            }
        };


        mDrawerLayout.setDrawerListener(mActionDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
               mActionDrawerToggle.syncState();
                if(!mLearneDrawer && !mFromSavedInstanceState){
                    mDrawerLayout.openDrawer(mContainerView);
                }
            }
        });
    }




    public static void savetoPreference(Context context,String PrefName,String PrefValue){
        SharedPreferences sharepref = context.getSharedPreferences(PREF_FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepref.edit();
        editor.putString(PrefName,PrefValue);
        editor.apply();

    }

    public static String readfromPreference(Context context,String PrefName,String DefaultValue){
        SharedPreferences sharepref = context.getSharedPreferences(PREF_FILE,Context.MODE_PRIVATE);
        return sharepref.getString(PrefName,DefaultValue);

    }
}
