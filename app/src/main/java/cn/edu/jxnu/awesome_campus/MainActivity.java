/*
 * Copyright (c) 2016.  MummyDing & KevinWu
 *    Awesome-Campus is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *    Awesome-Campus is distributed in the hope that it will be useful,but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 */

package cn.edu.jxnu.awesome_campus;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.edu.jxnu.awesome_campus.model.common.DrawerItem;
import cn.edu.jxnu.awesome_campus.presenter.home.HomePresenter;
import cn.edu.jxnu.awesome_campus.presenter.home.HomePresenterImpl;
import cn.edu.jxnu.awesome_campus.ui.base.BaseActivity;
import cn.edu.jxnu.awesome_campus.ui.base.BaseFragment;
import cn.edu.jxnu.awesome_campus.ui.base.TopNavigationFragment;
import cn.edu.jxnu.awesome_campus.ui.education.EducationFragment;
import cn.edu.jxnu.awesome_campus.ui.home.HomeFragment;
import cn.edu.jxnu.awesome_campus.ui.leisure.LeisureFragment;
import cn.edu.jxnu.awesome_campus.ui.library.LibraryFragment;
import cn.edu.jxnu.awesome_campus.ui.life.LifeFragment;
import cn.edu.jxnu.awesome_campus.ui.login.LoginFragment;
import cn.edu.jxnu.awesome_campus.ui.study.StudyFragment;
import cn.edu.jxnu.awesome_campus.view.home.HomeView;
import cn.edu.jxnu.awesome_campus.view.widget.ColorPickerDialog;

/**
 * Created by MummyDing on 16-1-24.
 * GitHub: https://github.com/MummyDing
 * Blog: http://blog.csdn.net/mummyding
 *
 * Amended by KevinWu on 16-1-24
 */
public class MainActivity extends BaseActivity implements HomeView{

    private Toolbar toolbar;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private HomePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new HomePresenterImpl(this);
        presenter.initlization();
        presenter.buildDrawer(this,toolbar);
        switchDrawerItem(DrawerItem.HOME.getId());
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void switchDrawerItem(int id) {
        if(id == DrawerItem.HOME.getId()){
            presenter.clearAllFragments();
            switchFragment(HomeFragment.newInstance(),DrawerItem.HOME.getItemName());
        }else if(id == DrawerItem.LEISURE.getId()){
            presenter.clearAllFragments();
            switchFragment(LeisureFragment.newInstance(),DrawerItem.LEISURE.getItemName());
        }else if(id == DrawerItem.LIFE.getId()){
            presenter.clearAllFragments();
            switchFragment(LifeFragment.newInstance(),DrawerItem.LIFE.getItemName());
        }else if(id == DrawerItem.STUDY.getId()){
            presenter.clearAllFragments();
            switchFragment(StudyFragment.newInstance(),DrawerItem.STUDY.getItemName());
        }else if(id == DrawerItem.LIBRARY.getId()){
            presenter.clearAllFragments();
            switchFragment(LibraryFragment.newInstance(),DrawerItem.LIBRARY.getItemName());
        }else if(id == DrawerItem.EDUCATION.getId()){
            presenter.clearAllFragments();
            switchFragment(EducationFragment.newInstance(),DrawerItem.EDUCATION.getItemName());
        }else if(id == DrawerItem.THEME.getId()){

            ColorPickerDialog dialog = new ColorPickerDialog(this,new int[]{Color.YELLOW,Color.BLACK,Color.BLUE,Color.GRAY,
            Color.GREEN,Color.CYAN,Color.RED,Color.DKGRAY});
            dialog.build().show();
            //setTitle(DrawerItem.THEME.getItemName());
        }else if(id == DrawerItem.SETTINGS.getId()){
            //setTitle(DrawerItem.SETTINGS.getItemName());
        }else if(id == DrawerItem.LOGOUT.getId()){
            //setTitle(DrawerItem.LOGOUT.getItemName());
        }
    }

    private void switchFragment(TopNavigationFragment fragment, String title){
        setTitle(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void switchToLogin() {
        fragmentTransaction = fragmentManager.beginTransaction();
        setTitle(getString(R.string.Login));
        fragmentTransaction.replace(R.id.framelayout, LoginFragment.newInstance());
        fragmentTransaction.commit();
    }
}
