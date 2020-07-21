package com.example.da1.presenter;

public class HomePresenter {

    HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    public void playgame() {
        homeView.playGame();
    }

    public void hdgame() {
        homeView.hdGame();
    }

    public void exitgame() {
        homeView.exitGame();
    }
}
