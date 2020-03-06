// Main.c - makes LEDG0 on DE2-115 board blink if NIOS II is set up correctly
// for ECE 385 - University of Illinois - Electrical and Computer Engineering
// Author: Zuofu Cheng

int main()
{
	int HIGH = 1;
	int LOW = 0;
	int pause;
	volatile unsigned int total;

	//int i = 0;
	volatile unsigned int *LED_PIO = (unsigned int*)0x90; //make a pointer to access the PIO block
	volatile unsigned int *SW_PIO = (unsigned int*)0x80; //make a pointer to access the PIO block
	volatile unsigned int *ACC_PIO = (unsigned int*)0x70; //make a pointer to access the PIO block
	volatile unsigned int *A_RESET_PIO = (unsigned int*)0x60; //make a pointer to access the PIO block

	*LED_PIO = LOW; //clear all LEDs
	pause = total = LOW;
	while ( (1+1) != 3) //infinite loop
	{
//		for (i = 0; i < 100000; i++); //software delay
//		*LED_PIO |= 0x1; //set LSB
//		for (i = 0; i < 100000; i++); //software delay
//		*LED_PIO &= ~0x1; //clear LSB

		if(*A_RESET_PIO == HIGH)
			total = LOW;

		if(*ACC_PIO == LOW)
				pause = LOW;

		else if(pause == LOW) {
			pause = HIGH;
			total = total + *SW_PIO;
		}

		*LED_PIO = total;

	}
	return 1; //never gets here
}
