// a multiplier, containing nine bit adder/subtracter, control unit, and a d-flip-flop two 8-bit registers
// inputs: Clk (Clock), Reset, Run, ClearA_LoadB,
// outputs: Switches (first 8 on FPGA board), AhexU, AhexL, BhexU, BhexL (LEDS on FPGA board), Aval, Bval, X (MSB of A)
module multiplier
(
        input logic Clk,
		  input logic Reset,
		  input logic Run,
		  input logic ClearA_LoadB,
		  input logic [7:0] Switches,
		  output logic [6:0] AhexU,
		  output logic [6:0] AhexL,
		  output logic [6:0] BhexU,
		  output logic [6:0] BhexL,
		  output logic [7:0] Aval,
		  output logic [7:0] Bval,
		  output logic X
);
										 

										  
	logic [7:0] A_in; // A_in, B_in are A and B in diagram given in lab 5 manual
	logic [7:0] B_in;
	logic [7:0] A_new; // A_new is what feeds out of nine bit adder in lab 5 manual
	logic [8:0] Sum;
	logic A_data; // individual bit of outputs for A and B, respectively
	logic B_data;
	
	// control variables that will store outputs from control unit code
	logic Shift_En, ClearA, CA_LB, Addition, func_val;
	
	// creating nine_bit_adder
	nine_bit_adder nba_0(.*, .A_in, .B_in(Switches), .func_val, .S(Sum) );
	
	// the following lines invert the reset, clrA_ldB, and run variables
	// to correspond with high/low logic in control unit code
	logic reset_negate, ca_lb_negate, run_negate; 
	
	assign reset_negate = ~Reset;
	assign ca_lb_negate = ~ClearA_LoadB;
   assign run_negate = ~Run;
	
	// creating control unit
	control	ctrl_0(.*, .Reset(reset_negate), .ClearA_LoadB(ca_lb_negate), .Execute(run_negate), .M (B_data),
						.Shift_En, .ClearA, .CA_LB, .Addition, .func_val);
	
	// creating d-flip-flop					
	d_flip_flop dff_0(.*, .Load(Addition), .Reset(ClearA | CA_LB), .D(Sum[8]), .Q(X) );
	
	// holds 8 bits of sum, just a holder to be put in reg_0 below
	logic [7:0] first_seven_sum;
	assign first_seven_sum = Sum[7:0];
	
	// creating register unit - consists of registers A and B
	reg_8 reg_0( .*, .Reset(ClearA | CA_LB), .Shift_In(X), .Load(Addition),
						.D(first_seven_sum), .Shift_Out(A_data), .Data_Out(A_in) );
	reg_8 reg_1( .*, .Reset(ClearA), .Shift_In(A_data), .Load(CA_LB),
						.D(Switches), .Shift_Out(B_data), .Data_Out(B_in) );
	
	// for hex display on FPGA board - taken from lab 4 code
	HexDriver HexAL (
						.In0(A_in[3:0]),
                  .Out0(AhexL));
	HexDriver HexAU (
                  .In0(A_in[7:4]),
                  .Out0(AhexU));
	HexDriver HexBL (
                  .In0(B_in[3:0]),
                  .Out0(BhexL));
	HexDriver HexBU (
                  .In0(B_in[7:4]),
                  .Out0(BhexU));
						
endmodule