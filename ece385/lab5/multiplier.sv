module multiplier
(
        input logic Clk,
		  input logic Reset,
		  input logic Run,
		  input logic ClearA_LoadB,
		  input logic [7:0] S,
		  output logic [6:0] AhexU,
		  output logic [6:0] AhexL,
		  output logic [6:0] BhexU,
		  output logic [6:0] BhexL,
		  output logic [7:0] Aval,
		  output logic [7:0] Bval,
		  output logic X
);

logic ctrl_Reset, ctrl_Clear, ctrl_Run;
logic ctrl_Shift_En, ctrl_ClearA, ctrl_CA_LB, ctrl_Addition, ctrl_Subtraction;
assign ctrl_Reset = ~Reset;
assign ctrl_Clear = ~ClearA_LoadB;
assign ctrl_Run = ~Run;

logic [7:0] A_in;
logic [7:0] B_in;
logic [7:0] A_new;
logic [8:0] Sum;
logic A_data;
logic B_data;

control ctrl_0( .Clk(Clk), .Reset(ctrl_Reset), .ClearA_LoadB(ctrl_Clear), .Execute(ctrl_Run), .M(B_data),
					 .Shift_En(ctrl_Shift_En), .ClearA(ctrl_ClearA), .CA_LB(ctrl_CA_LB), .Addition(ctrl_Addition), .Subtraction(ctrl_Subtraction) );

nine_bit_adder nba_0( .B(S), .S(Sum) );

d_flip_flop dff_0( .Clk(Clk), .Load(ctrl_Addition), .Reset(ctrl_CA_LB | ctrl_ClearA), .D(Sum[8]), .Q(X) );
			
logic [7:0] sum_first_seven;
assign sum_first_seven = Sum[7:0];

reg_8 reg_A( .*, .Reset(ctrl_ClearA | ctrl_CA_LB), .Shift_In(X), .Load(ctrl_Addition), .Shift_En(ctrl_Shift_En),
						.D(sum_first_Seven), .Shift_Out(A_data), .Data_Out(A) );
reg_8 reg_B( .*, .Reset(ctrl_ClearA), .Shift_In(A_Data), .Load(ctrl_CA_LB), .Shift_En(ctrl_Shift_En),
						.D(S), .Shift_Out(B_Data), .Data_Out(B) );

HexDriver HexAL (.In0(A_in[3:0]), .Out0(AhexL) );
HexDriver HexBL (.In0(B_in[3:0]), .Out0(BhexL) );
								
//When you extend to 8-bits, you will need more HEX drivers to view upper nibble of registers, for now set to 0
HexDriver HexAU (.In0(A_in[7:4]), .Out0(AhexU) );	// MS 4 bits of A
HexDriver HexBU (.In0(B_in[7:4]), .Out0(BhexU) );		// MS 4 bits of B	

assign Aval = A_in;
assign Bval = B_in;


endmodule
