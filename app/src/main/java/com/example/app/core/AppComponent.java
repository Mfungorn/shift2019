package com.example.app.core;

import com.example.app.core.modules.ApiModule;
import com.example.app.core.modules.SharedPreferencesModule;
import com.example.app.features.MainFlowFragment;
import com.example.app.features.event_new.NewEventPresenter;
import com.example.app.features.events.EventsPresenter;
import com.example.app.features.friend_profile.FriendProfilePresenter;
import com.example.app.features.friends.FriendsPresenter;
import com.example.app.features.profile.ProfileFragment;
import dagger.Component;
import org.jetbrains.annotations.NotNull;
import com.example.app.features.signin.SignInPresenter;
import com.example.app.features.signup.SignUpPresenter;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ApiModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(SignUpPresenter signUpPresenter);

    void inject(SignInPresenter signInPresenter);

    void inject(MainFlowFragment mainFlowFragment);

    void inject(@NotNull ProfileFragment profileFragment);

    void inject(@NotNull EventsPresenter eventsPresenter);

    void inject(@NotNull NewEventPresenter newEventPresenter);

    void inject(@NotNull FriendsPresenter friendsPresenter);

    void inject(@NotNull FriendProfilePresenter friendProfilePresenter);
}

