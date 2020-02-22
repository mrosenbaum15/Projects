module testbench();

timeunit 10ns;	// Half clock cycle at 50 MHz
			// This is the amount of time represented by #1 
timeprecision 1ns;

// These signals are internal because the processor will be 
// instantiated as a submodule in testbench.
logic Clk = 0;
logic Reset, ClearA_LoadB, Run, X;
logic	[7:0] Aval, Bval, Switches;
logic [6:0] AhexL, AhexU, BhexL, BhexU; 

// To store expected results
logic [15:0] result;

logic ans_1a;
				
// A counter to count the instances where simulation results
// do no match with expected results
integer ErrorCnt = 0;
		
// Instantiating the DUT
// Make sure the module and signal names match with those in your design
multiplier mul_0(.*);	

// Toggle the clock
// #1 means wait for a delay of 1 timeunit
always begin : CLOCK_GENERATION
#1 Clk = ~Clk;
end

initial begin: CLOCK_INITIALIZATION
    Clk = 0;
end 

// Testing begins here
// The initial block is not synthesizable
// Everything happens sequentially inside an initial block
// as in a software program
initial begin: TEST_VECTORS
Reset = 0;		// Toggle Rest
ClearA_LoadB = 1;
Run = 1;
Switches = 8'b11000101;

#2 Reset = 1;

#2 ClearA_LoadB = 0;	// Toggle CA_LB
#2 ClearA_LoadB = 1;

#2 Switches = 8'b00000111;

#2 Run = 0;	// Toggle Execute
   
#40 Run = 1;
   
	 assign ans_1a = 8'b11111110; // Expected result of 1st cycle

    if (Aval != 8'b11111110 || Bval != 8'b01100011)
	 begin
			ErrorCnt++;
			$display("-59*7 did not work properly. FIRST");
			
	 end
	 
//    if (Bval != 8'b01100011)
//	 begin
//			ErrorCnt++;
//			$display("-59*7 did not work properly. HERE");
//	 end
	 

#2 ClearA_LoadB = 0;	// Toggle CA_LB
#2 ClearA_LoadB = 1;

#2 Switches = 8'b11000101;
#2 Run = 0;	// Toggle Execute
#40 Run = 1;

assign ans_1a = 8'b11111110;

if(Aval != 8'b11111110 || Bval != 8'b01100011)
begin
		$display("7*-59 did not work properly. FIRST");
		ErrorCnt++;

end

//if(Bval != 8'b01100011)
//begin
//		$display("7*-59 did not work properly. HERE");
//end


#2 ClearA_LoadB = 0;	// Toggle CA_LB
#2 ClearA_LoadB = 1;

#2 Switches = 8'b11111001;
#2 Run = 0;	// Toggle Execute
#40 Run = 1;

assign ans_1a = 8'b00000001;

if(Aval != ans_1a || Bval != 8'b10011101)
begin
		ErrorCnt++;
		$display("-59*-7 did not work properly.");
end

//if(Bval != 8'b10011101)
//begin
//		ErrorCnt++;
//		$display("-59*-7 did not work properly.");
//end


#2 Switches = 8'b00000111;

#2 ClearA_LoadB = 0;	// Toggle CA_LB
#2 ClearA_LoadB = 1;

#2 Switches = 8'b00111011;
#2 Run = 0;	// Toggle Execute
#40 Run = 1;

assign ans_1a = 8'b00000001;

if(Aval != ans_1a || Bval != 8'b10011101)
begin
		ErrorCnt++;
		$display("7*59 did not work properly.");
end

//if(Bval != 8'b10011101)
//begin
//		ErrorCnt++;
//		$display("7*59 did not work properly.");
//end

if (ErrorCnt == 0)
	$display("Success!");  // Command line output in ModelSim
else
	$display("%d error(s) detected. Try again!", ErrorCnt);
end
endmodule