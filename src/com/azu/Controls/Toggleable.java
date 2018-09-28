package com.azu.Controls;

public interface Toggleable {
    default void toggleOn(){toggle();};
    default void toggleOff(){toggle();};
    void toggle();
}
