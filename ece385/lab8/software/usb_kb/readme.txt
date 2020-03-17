Lab 8 README

Expected Demo Points: 5/5

Everything works as expected.

HEXs display the keycode of last key pressed for a split second.

LEDs display direction of ball
(LED0 == LEFT, LED1 == DOWN, LED2 == RIGHT, LED3 == UP)

Ball properly moves in every direction, and bounces off each wall.

Ball properly responses to ASDW from keyboard.

When running the Signal Tap application, it can be seen
that a rising edge is generated when a key is pressed,
showing that input is read on keypress and not release.

Currently, lab8_signal_tap.stp has data saved that shows
a rising edge on keycode[1], keycode[3], and keycode[4].

This translates to 00011010 which is x1A.

This is from pressing the W key, which is x1A.
