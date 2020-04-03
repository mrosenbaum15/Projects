module testbench ();

	
    timeunit 10ns;  // how long one unit of time is

    timeprecision 1ns;

    // inputs
    logic RESET;
	 logic AES_START, AES_DONE;
	 logic [127:0] AES_KEY, AES_MSG_ENC, AES_MSG_DEC;
	 logic CLK = 0;
	 
	 AES a(.*);
	 
	 // write testbench with avalon_aes_interface!
	 
	 input logic CLK,

	// Avalon Reset Input
	//input logic RESET,

	// Avalon-MM Slave Signals
//input  logic AVL_READ,					// Avalon-MM Read - set to low
//	input  logic AVL_WRITE,					// Avalon-MM Write - set to high
	//input  logic AVL_CS,						// Avalon-MM Chip Select - set to high
	//input  logic [3:0] AVL_BYTE_EN,		// Avalon-MM Byte Enable - all high
	//input  logic [3:0] AVL_ADDR,			// Avalon-MM Address
	//input  logic [31:0] AVL_WRITEDATA,	// Avalon-MM Write Data
	//output logic [31:0] AVL_READDATA,	// Avalon-MM Read Data

	// Exported Conduit
//	output logic [31:0] EXPORT_DATA		// Exported Conduit Signal to LEDs
	 
	 always begin:
		CLOCK_GENERATION
			#1 CLK = ~CLK;
		end
		
		initial begin:
			CLOCK_INITIALIZATION
				CLK = 0;
			end
		initial begin:
			TEST_VECTORS
			AES_START = 0;
			AES_KEY = 128'h000102030405060708090a0b0c0d0e0f;
			AES_MSG_ENC = 128'hdaec3055df058e1c39e814ea76f6747e;
			RESET = 1;
			#2
			RESET = 0;
			
			#5
			AES_START = 1;
			#6
			AES_START = 0;
		end
endmodule
		