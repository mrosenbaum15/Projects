// Main.c - makes LEDG0 on DE2-115 board blink if NIOS II is set up correctly
// for ECE 385 - University of Illinois - Electrical and Computer Engineering
// Author: Zuofu Cheng

int main()
{
	// making simple MACROs for 1 and 0
	int HIGH = 1;
	int LOW = 0;

	// variable for pause state
	int pause;

	// running sum for accumulator
	volatile unsigned int total;

	//int i = 0;

	// addresses set in design planner
	volatile unsigned int *LED_PIO = (unsigned int*)0x90; //make a pointer to access the PIO block
	volatile unsigned int *SW_PIO = (unsigned int*)0x80; //make a pointer to access the PIO block
	volatile unsigned int *ACC_PIO = (unsigned int*)0x70; //make a pointer to access the PIO block
	volatile unsigned int *A_RESET_PIO = (unsigned int*)0x60; //make a pointer to access the PIO block

	*LED_PIO = LOW; //clear all LEDs
	pause = total = LOW; // sum and pause set to low at first
	while ( (1+1) != 3) //infinite loop
	{
//		for (i = 0; i < 100000; i++); //software delay
//		*LED_PIO |= 0x1; //set LSB
//		for (i = 0; i < 100000; i++); //software delay
//		*LED_PIO &= ~0x1; //clear LSB

		// clearing total if reset is high
		if(*A_RESET_PIO == HIGH)
			total = LOW;

		// next state is paused state
		if(*ACC_PIO == LOW)
				pause = LOW;

		// pausing and adding values from switches
		else if(pause == LOW) {
			pause = HIGH;
			total = total + *SW_PIO;
		}

		// adjusting LEDs
		*LED_PIO = total;

	}
	return 1; //never gets here
}
