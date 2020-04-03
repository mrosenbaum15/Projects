module testbench ();

	
    timeunit 10ns;  // how long one unit of time is

    timeprecision 1ns;

    // inputs
    logic RESET;
	 logic AES_START;
	 logic AES_DONE;
	 logic [127:0] AES_KEY;
	 logic [127:0] AES_MSG_ENC;
	 logic [127:0] AES_MSG_DEC;
	 logic CLK = 0;
	 
	 logic HIGH, LOW;
	 assign HIGH = 1;
	 assign LOW = 0;
	 
	 AES my_aes(.*);
//	logic CLK;
//	logic RESET;
//	logic AVL_READ;
//	logic AVL_WRITE;
//	logic AVL_CS;
//	logic [3:0] AVL_BYTE_EN;
//	logic [3:0] AVL_ADDR;
//	logic [31:0] AVL_WRITEDATA;
//	logic [31:0] AVL_READDATA;
//	logic [31:0] EXPORT_DATA;
//	
//	logic [31:0]R14;
//	logic [31:0] R15;
//	
//	assign R14 = a.reg_sys[14];
//	assign R15 = a.reg_sys[15];
//	
//	avalon_aes_interface a(.*);


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
				CLK = LOW;
			end
		initial begin:
			TEST_VECTORS
			AES_START = LOW;
			AES_KEY = 128'h3b280014beaac269d613a16bfdc2be03;
			AES_MSG_ENC = 128'h439d619920ce415661019634f59fcf63;
			RESET = HIGH;
			#2
			RESET = LOW;
			
			#5
			AES_START = HIGH;
			#6
			AES_START = LOW;

//initial begin:
//			TEST_VECTORS
//			AVL_READ = 0;
//			AVL_WRITE = 1;
//			AVL_CS = 1;
//			AVL_BYTE_EN = 4'b1111;
//		   AVL_ADDR = 4'b0000;
//			a.reg_sys[14][0] = 0;
//			//force a.aes_0.AES_KEY = 128'h000102030405060708090a0b0c0d0e0f;
//		//	force a.aes_0.AES_MSG_ENC = 128'hdaec3055df058e1c39e814ea76f6747e;
//			a.reg_sys[0] = 32'hdaec3055;
//			a.reg_sys[1] = 32'hdf058e1c;
//			a.reg_sys[2] = 32'h39e814ea;
//			a.reg_sys[3] = 32'h76f6747e;
//			a.reg_sys[4] = 32'h00010203;
//			a.reg_sys[5] = 32'h04050607;
//			a.reg_sys[6] = 32'h08090a0b;
//			a.reg_sys[7] = 32'h0c0d0e0f;
//
//			RESET = 1;
//			#2
//			RESET = 0;
//			
//			#5
//			force a.reg_sys[14][0] = 1;
//			#6
//			force a.reg_sys[14][0] = 0;
end
endmodule
		